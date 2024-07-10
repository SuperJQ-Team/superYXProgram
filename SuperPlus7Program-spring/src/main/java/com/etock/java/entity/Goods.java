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
import java.util.Date;
@TableName(value = "goods")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("summary")
    private String summary;

    @TableField("image_path")
    private String imagePath;

    @TableField("price")
    private Integer price;

    @TableField("count")
    private Integer count;

    @TableField("del_flag")
    private Boolean delFlag;

    @TableField("created_by")
    private String createdBy;

    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}