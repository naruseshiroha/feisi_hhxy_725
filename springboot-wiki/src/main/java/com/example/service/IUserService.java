package com.example.service;

import com.example.entity.User;
import com.example.req.UserLoginReq;
import com.example.req.UserReq;
import com.example.req.UserResetPasswordReq;
import com.example.req.UserSaveReq;
import com.example.resp.UserLoginResp;
import com.example.resp.UserResp;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-09
 */
public interface IUserService extends IService<User> {
    List<UserResp> all();

    List<UserResp> findUser(UserReq userReq);

    boolean save(UserSaveReq req);

    boolean deleteById(Long id);

    User selectByLoginName(String loginName);

    void resetPassword(UserResetPasswordReq req);

    UserLoginResp login(UserLoginReq req);
}
