package com.lzy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_perm")
public class SysPerm  {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String describes;

    private Integer roleId;

    private Integer permStatus;

    private String creator;

    private Date createTime;

    private Date updateTime;

}