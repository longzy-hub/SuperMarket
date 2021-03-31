package com.lzy.dto;

import lombok.Data;

@Data
public class SearchCategoryDto {

    private String category_name;

    private Integer search_status = 1;

}
