package com.lzy.viewobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartVo {

    private Double price;

    private String cartAddress;

    private String remark;

    private int[] cartIds;

    private int[] cartProductIds;

    private int[] cartNumbers;

}
