package com.lzy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;


    private String describes;


    private Integer status;


    private String creator;


    private Date createTime;

    private Date updateTime;

}