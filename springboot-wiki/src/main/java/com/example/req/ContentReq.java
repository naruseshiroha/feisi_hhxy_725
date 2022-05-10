package com.example.req;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文档内容
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@Getter
@Setter
public class ContentReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档id
     */
    @NotNull(message = "【id】不能为空")
    private Long id;

    /**
     * 内容
     */
    private String content;

}
