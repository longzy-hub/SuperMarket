package com.lzy.viewobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BackVo {

    private Integer id;

    @NotBlank(message = "分类名称不能为空")
    @Length(max = 15, min = 2, message = "分类名称应在2-15个字之间")
    private String address;

    private Integer backStatus;

}
