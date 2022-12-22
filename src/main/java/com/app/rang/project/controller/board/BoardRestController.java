package com.app.rang.project.controller.board;

import com.app.rang.project.entity.Board;
import com.app.rang.project.model.AuthUserDTO;
import com.app.rang.project.model.board.BoardListModel;
import com.app.rang.project.model.board.BoardViewModel;
import com.app.rang.project.model.board.BoardWriteRequest;
import com.app.rang.project.service.board.*;
import com.app.rang.project.service.comment.CommentListService;
import com.app.rang.project.util.CategoryUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/board")
public class BoardRestController {
    @Autowired
    BoardWriteService boardWriteService;

    @Autowired
    BoardListService boardListService;

    @Autowired
    BoardViewService boardViewService;

    @Autowired
    BoardDeleteService boardDeleteService;

    @Autowired
    BoardUpdateService boardUpdateService;

    @Autowired
    private CommentListService commentListService;

    @GetMapping
    public ModelAndView getWritePage() {
        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("itemCategory", CategoryUtil.categoryNames);
        mav.setViewName("view/board/write");
        return mav;
    }

    @GetMapping("/list")
    public ModelAndView getBoardList(){
        ModelAndView mav = new ModelAndView();
        List<BoardListModel> list = boardListService.selectBoardListByPage();
        log.info("list -----> " + list);
        mav.addObject("itemList", list);
        mav.addObject("last", list.size() > 0 ? list.get(list.size()-1).getBoardidx() : 0);

        mav.setViewName("view/board/list");

        return mav;
    }

    @GetMapping("/list/{idx}")
    public ResponseEntity<List<BoardListModel>> getBoardList(
            @PathVariable("idx") long boardIdx
    ) {
        List<BoardListModel> itemList = boardListService.selectBoardListByListIdx(boardIdx);

        log.info("list -----> " + itemList);

        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(itemList, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/{page}/{idx}")
    public ModelAndView getViewPage(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @PathVariable("page") int page,
            @PathVariable("idx") long boardIdx
    ) {
        BoardViewModel board = boardViewService.getBoard(boardIdx);

        log.info("board -----> " + board);

        ModelAndView mav = new ModelAndView();
        mav.clear();
        mav.addObject("board", board);
        if(page == 1) {
            mav.addObject("commentList", commentListService.selectBoardCommentLimit(boardIdx, 0));
            mav.addObject("isMine", board.getUseridx() == userDTO.getUseridx());
            mav.setViewName("view/board/view");
        } else if(page == 2) {
            if(userDTO.getUseridx() == board.getUseridx()) {
                mav.addObject("itemCategory", CategoryUtil.categoryNames);
                mav.setViewName("view/board/update");
            } else {
                mav.addObject("msg", "게시글은 본인만 수정할 수 있습니다.");
                mav.setViewName("view/board/view");
            }
        }

        return mav;
    }

    @PostMapping
    public ResponseEntity<String> postWritePage(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @RequestBody BoardWriteRequest writeRequest
    ) {
        log.info(writeRequest);

        HttpHeaders httpHeaders = new HttpHeaders();

        if((writeRequest.getTitle() == null  || writeRequest.getTitle().isEmpty())
                || (writeRequest.getContent() == null  || writeRequest.getContent().isEmpty())
                || (writeRequest.getPrice() <= 0)) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }
        log.info(writeRequest);

        Board board = writeRequest.toBoardEntity();
        board.setUseridx(userDTO.getUseridx());
        int result = boardWriteService.writeBoard(board);

        return new ResponseEntity<>("/", httpHeaders, HttpStatus.OK);
    }
    @PostMapping("/{idx}")
    public ResponseEntity<Map<String, String>> updateItem(
            @PathVariable("idx") long boardIdx,
            @RequestBody Board board
    ) {
        log.info(board);

        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> result = new HashMap<>();

        if((board.getTitle() == null  || board.getTitle().isEmpty())
                || (board.getContent() == null  || board.getContent().isEmpty())
                || (board.getPrice() <= 0)) {
            result.put("msg", "입력 데이터를 다시 확인해주세요.");
            return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
        }

        log.info("board ------> " + board);
        int r = boardUpdateService.updateBoard(board);
        log.info("r ------> " + r);

        result.put("url","/board/1/"+boardIdx);
        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, List<String>>> writeItem(
            MultipartHttpServletRequest multipartRequest
    ) {
        Map<String, List<String>> nameList = null;

        try {
            multipartRequest.setCharacterEncoding("utf-8");
            // 파일을 업로드한 후 반환된 파일 이름이 저장되는 fileList를 다시 map에 저장할 것이다
            nameList = boardWriteService.fileUpload(multipartRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(nameList, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<String> deleteItem(
            @AuthenticationPrincipal AuthUserDTO userDTO,
            @PathVariable("idx") long boardIdx
    ){
        BoardViewModel board = boardViewService.getBoard(boardIdx);
        String body = "";
        if(board.getUseridx() == userDTO.getUseridx()) {
            int result = boardDeleteService.deleteBoard(boardIdx);
            if(result > 0) {
                body = "/board/list";
            }
        }
        
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }
}
