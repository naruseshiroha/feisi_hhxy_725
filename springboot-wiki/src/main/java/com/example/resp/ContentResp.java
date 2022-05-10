package com.example.resp;

import java.io.Serializable;
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
public class ContentResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文档id
     */
    private Long id;

    /**
     * 内容
     */
    private String content;


}
