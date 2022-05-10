package com.example.req;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author Administrator
 * @since 2022-05-09
 */
@Getter
@Setter
public class UserSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 昵称
     */
    @NotNull(message = "【昵称】不能为空")
    private String name;

    /**
     * 密码
     */
    @NotNull(message = "【昵称】不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "密码至少包含数字和英文，长度6-32")
    private String password;

}
