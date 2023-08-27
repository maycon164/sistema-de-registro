package com.fatec.utils;

import org.springframework.data.domain.PageRequest;

public class PaginationUtils {

    private static final Integer PAGE_SIZE = 10;

    public static PageRequest getPageRequest(Integer pageNumber){
        return PageRequest.of(getPageNumber(pageNumber), PAGE_SIZE);
    }

    private static Integer getPageNumber(Integer pageNumber){
        return pageNumber - 1;
    }

}
