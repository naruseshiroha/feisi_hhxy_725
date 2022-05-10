package com.example.req;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文档
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@Getter
@Setter
public class DocReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 电子书id
     */
    @NotNull(message = "【电子书id】不能为空")
    private Long ebookId;

    /**
     * 父id
     */
    @NotNull(message = "【父文档】不能为空")
    private Long parent;

    /**
     * 名称
     */
    @NotNull(message = "【名称】不能为空")
    private String name;

    /**
     * 顺序
     */
    @NotNull(message = "【顺序】不能为空")
    private Integer sort;

    /**
     * 内容
     */
    @NotNull(message = "【内容】不能为空")
    private String content;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer voteCount;

}
