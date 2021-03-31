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
public class UserVo {

    // 用户id
    private Integer id;

    // 用户名
    @NotBlank(message = "用户名不能为空")
    @Length(max = 15, min = 2, message = "用户名应在2-15个字之间")
    private String username;

    // 手机号
    @NotBlank(message = "手机号不能为空")
    private String telephone;

    // 邮箱
    @NotBlank(message = "邮箱不能为空")
    private String mail;

    // 密码
    @NotBlank(message = "密码不能为空")
    private String password;


}
