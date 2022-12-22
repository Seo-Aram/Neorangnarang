package com.app.rang.project.service.board;

import com.app.rang.project.entity.Board;
import com.app.rang.project.repository.BoardRepository;
import com.app.rang.project.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

@Log4j2
@Service
public class BoardWriteService {

    @Autowired
    private BoardRepository boardRepository;

    public int writeBoard(Board board){

        int result = 0;
        try {
            result = boardRepository.save(board) != null ? 1 : 0;
        } catch (Exception e) {

        }

        return result;
    }

    public Map<String, List<String>> fileUpload(MultipartHttpServletRequest multipartRequest) {
        List<String> fileList = new ArrayList<String>();
        List<String> thumbnailName = null;

        String absolutePath = new File("").getAbsolutePath() + "\\upload";
        log.info("absolutePath ----> " + absolutePath);

        // 첨부된 파일 이름을 가져올 것이다
        Iterator<String> fileNames = multipartRequest.getFileNames();
        int count = 0;

        try{
            while (fileNames.hasNext()) {
                String fileName = fileNames.next();

                log.info("fileName -----> " + fileName);

                // 파일 이름에 대한 MultipartFile 객체 가져오기
                MultipartFile mFile = multipartRequest.getFile(fileName);
                // 실제 파일 이름 가져오기
                String originalFileName = "T" + Util.getCurrentTimestamp() + "." + mFile.getContentType().split("/")[1];
                // 파일 이름을 하나씩 fileList에 저장하기
                fileList.add(originalFileName);

                File saveFile = new File(absolutePath, originalFileName);
                log.info("saveFile -----> " + saveFile.getPath());

                // 첨부된 파일이 존재하는지 체크
                if (mFile.getSize() != 0 && !saveFile.exists()) {
                    //경로에 해당하는 디렉토리를 만들어라
                    if (saveFile.getParentFile().mkdirs()) {
                        // 그 후에 파일을 생성해라
                        saveFile.createNewFile();
                    }

                    // 임시로 저장된 mutilpartFile을 실제 파일로 저장
                    mFile.transferTo(saveFile); //임시로 저장된 multipartFile을 실제 파일로 전송
                    log.info("saveFile.getPath() ----> " + saveFile.getPath());
                    if (count++ == 0) {
                        thumbnailName = thumbnailFile(absolutePath, originalFileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            // -> 에러 발생시 저장된 파일 모두 제거
            for(String fileName : fileList) {
                File delFile = new File(absolutePath, fileName);
                if (delFile.exists()) {
                    // 파일 삭제
                    delFile.delete();
                }
            }
        }

        // 첨부한 파일 이름이 저장된 fileList 반환해줘라
        Map<String, List<String>> map = new HashMap<>();
        map.put("fileList", fileList);
        map.put("thumbnail", thumbnailName);
        return map;
    }

    private List<String> thumbnailFile(
            String originalFilePath, // 원본 패스
            String fileName
    ) {
        List<String> list = new ArrayList<>();
        File oFile = new File(originalFilePath, fileName);
        log.info("oPath ---> " + oFile.getPath());

        int index = fileName.lastIndexOf(".");
        String thumbnailName = fileName.substring(0, index) + ".jpg";

        String thumbnailPath = originalFilePath + "\\thumbnail";
        log.info("tPath ---> " + thumbnailPath);

        File thumbnailFile = new File(thumbnailPath, thumbnailName);

        try {
            if (!thumbnailFile.exists()) {
                if (thumbnailFile.getParentFile().mkdirs()) {
                    // 그 후에 파일을 생성해라
                    thumbnailFile.createNewFile();
                }
            }
            log.info("thumbnailFile ---> " + thumbnailFile.getPath());

            BufferedImage originalImg = ImageIO.read(oFile); // 원본이미지
            int tWidth = 128; // 생성할 썸네일이미지의 너비
            int tHeight = 128; // 생성할 썸네일이미지의 높이

            BufferedImage thumbnailImg = new BufferedImage(tWidth, tHeight, BufferedImage.TYPE_3BYTE_BGR); // 썸네일이미지
            Graphics2D graphic = thumbnailImg.createGraphics();
            Image image = originalImg.getScaledInstance(tWidth, tHeight, Image.SCALE_SMOOTH);
            graphic.drawImage(image, 0, 0, tWidth, tHeight, null);
            graphic.dispose(); // 리소스를 모두 해제

            ImageIO.write(thumbnailImg, "jpg", thumbnailFile);
            list.add(thumbnailName);
            log.info(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            // -> 에러 발생시 저장된 파일 모두 제거
            File delFile = new File(thumbnailPath, thumbnailName);
            if(delFile.exists()){
                // 파일 삭제
                delFile.delete();
            }
        }
        return list;
    }
}
