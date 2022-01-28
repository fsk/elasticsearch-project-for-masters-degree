package com.fsk.elasticsearch.search;

import lombok.Data;

@Data
public class PagedRequestDTO {

    private static final int DEFAULT_SIZE = 10;
    private int page;
    private int size;

    public int getSize() {
        return size != 0 ? size : DEFAULT_SIZE;
    }
}
