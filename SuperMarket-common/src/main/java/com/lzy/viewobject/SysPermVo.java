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
public class SysPermVo {

    // 权限id
    private Integer id;

    // 角色id
    private Integer roleId;

    // 用户名
    @NotBlank(message = "权限名称不能为空")
    @Length(max = 30, min = 2, message = "用户名应在2-30个字之间")
    private String name;

    // 状态
    private Integer permStatus;

    // 描述
    private String describes;

}
