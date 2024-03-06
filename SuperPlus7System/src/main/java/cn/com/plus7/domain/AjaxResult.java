package cn.com.plus7.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AjaxResult {

    private String msg;

    private Integer code;

    private Object data;

    public static AjaxResult success() {
        return success("操作成功");
    }

    public static AjaxResult success(String msg) {
        return AjaxResult
                .builder()
                .code(200)
                .msg(msg)
                .build();
    }

    public static AjaxResult success(Object data) {
        return AjaxResult
                .builder()
                .code(200)
                .data(data)
                .msg("操作成功")
                .build();
    }

    public static AjaxResult error() {
        return error("操作失败");
    }

    public static AjaxResult error(String msg) {
        return AjaxResult
                .builder()
                .code(500)
                .msg(msg)
                .build();
    }

    public static AjaxResult error(Object data) {
        return AjaxResult
                .builder()
                .code(500)
                .data(data)
                .msg("操作失败")
                .build();
    }
}



