package com.app.rang.project.util;

import java.util.HashMap;
import java.util.Map;

public class CategoryUtil {
    // 카테고리 숫자로 선택, 문자열로 출력
    public static Map<Integer, String> categoryNames ;

    static {
        categoryNames = new HashMap<>();
        categoryNames.put(1, "생활 용품");
        categoryNames.put(2, "패션/잡화");
        categoryNames.put(3, "전자 제품");
        categoryNames.put(4, "도서");
        categoryNames.put(5, "반려동물 용품");
        categoryNames.put(6, "기타");
    }
}
