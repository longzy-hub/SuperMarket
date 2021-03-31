package com.lzy.dto;

import lombok.Data;

@Data
public class SearchProductDto {

    private String keyword;

    private Integer search_status = 1;
}
