package com.fatec.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationUtils {

    private static final Integer PAGE_SIZE = 10;

    public static PageRequest getPageRequest(Integer pageNumber){
        return PageRequest.of(getPageNumber(pageNumber), PAGE_SIZE, Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    private static Integer getPageNumber(Integer pageNumber){
        return pageNumber - 1;
    }

}
