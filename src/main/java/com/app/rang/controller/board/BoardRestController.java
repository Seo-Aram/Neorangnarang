package com.app.rang.controller.board;

import com.app.rang.util.CategoryUtil;
import com.app.rang.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/board")
public class BoardRestController {

    @GetMapping
    public ModelAndView writeItemViewPage(
            HttpServletRequest request
    ) {
        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("itemCategory", CategoryUtil.categoryNames);
        mav.setViewName("view/board/write");
        return mav;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, List<String>>> writeItem(
            MultipartHttpServletRequest multipartRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Map<String, List<String>> nameList = null;

        try {
            multipartRequest.setCharacterEncoding("utf-8");
            // 파일을 업로드한 후 반환된 파일 이름이 저장되는 fileList를 다시 map에 저장할 것이다
            nameList = fileProcess(multipartRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(nameList, new HttpHeaders(), HttpStatus.OK);
    }

    private Map<String, List<String>> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
        List<String> fileList= new ArrayList<String>();
        List<String> thumbnailName = null;

        String absolutePath = new File("").getAbsolutePath() + "\\upload";
        log.info("absolutePath ----> " + absolutePath);

        // 첨부된 파일 이름을 가져올 것이다
        Iterator<String> fileNames = multipartRequest.getFileNames();
        int count = 0;

        while(fileNames.hasNext()){
            String fileName = fileNames.next();

            log.info("fileName -----> " + fileName);

            // 파일 이름에 대한 MultipartFile 객체 가져오기
            MultipartFile mFile = multipartRequest.getFile(fileName);
            // 실제 파일 이름 가져오기
            String originalFileName = "T"+ Util.getCurrentTimestamp() + "." +mFile.getContentType().split("/")[1];
            // 파일 이름을 하나씩 fileList에 저장하기
            fileList.add(originalFileName);

            File saveFile = new File( absolutePath, originalFileName);
            log.info("saveFile -----> " + saveFile.getPath());

            // 첨부된 파일이 존재하는지 체크
            if(mFile.getSize()!=0 && !saveFile.exists()){
                //경로에 해당하는 디렉토리를 만들어라
                if(saveFile.getParentFile().mkdirs()){
                    // 그 후에 파일을 생성해라
                    saveFile.createNewFile();
                }

                // 임시로 저장된 mutilpartFile을 실제 파일로 저장
                mFile.transferTo(saveFile); //임시로 저장된 multipartFile을 실제 파일로 전송
                log.info("saveFile.getPath() ----> " + saveFile.getPath());
                if(count++ == 0){
                    thumbnailName = thumbnailFile(absolutePath, originalFileName);
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
    ) throws Exception {
        List<String> list = new ArrayList<>();
        File oFile = new File(originalFilePath, fileName);
        log.info("oPath ---> " + oFile.getPath());

        int index = fileName.lastIndexOf(".");
        String ext = fileName.substring(index + 1); // 파일 확장자

        String thumbnailPath = originalFilePath + "\\thumbnail";
        log.info("tPath ---> " + thumbnailPath);

        File thumbnailFile = new File(thumbnailPath, fileName);

        if(!thumbnailFile.exists()){
            if(thumbnailFile.getParentFile().mkdirs()){
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

        ImageIO.write(thumbnailImg, ext, thumbnailFile);
        list.add(fileName);
        log.info(list);
        return list;
    }
}
