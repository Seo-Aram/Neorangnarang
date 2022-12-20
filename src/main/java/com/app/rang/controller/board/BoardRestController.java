package com.app.rang.controller.board;

import com.app.rang.util.CategoryUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
}
