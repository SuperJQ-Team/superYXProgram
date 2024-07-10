package com.etock.java.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@TableName("sys_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "role")
    private String role;

    @TableField(value = "virtual_name")
    private String virtualName;

    @TableField(value = "summary")
    private String summary;

    @TableField(value = "del_flag")
    private Boolean delFlag;

    @TableField(value = "created_by")
    private String createdBy;

    @TableField(value = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(value = "head_image")
    private byte[] headImage;
}