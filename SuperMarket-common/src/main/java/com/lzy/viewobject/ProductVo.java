package com.lzy.viewobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVo {

    private Integer id;

    private Integer categoryId;

    @NotBlank(message = "商品名称不能为空")
    @Length(max = 30, min = 1, message = "商品名称应在1-30个字之间")
    private String productName;

    private Double price;

    private MultipartFile productImg;

    private Integer productStatus;

    @Length(max = 150, message = "描述应在150个字之内")
    private String describes;
}
