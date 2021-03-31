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
public class SysRoleVo {

    private Integer id;

    // 角色名称
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 15, min = 2, message = "角色名称应在2-15个字之间")
    private String name;

    // 角色状态
    private Integer status;

    // 描述
    @Length(max = 150, message = "描述应在150个字之内")
    private String describes;
}
