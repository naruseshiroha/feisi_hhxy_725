package com.example.service.impl;

import com.example.entity.User;
import com.example.exception.BusinessException;
import com.example.exception.BusinessExceptionCode;
import com.example.mapper.UserMapper;
import com.example.req.UserLoginReq;
import com.example.req.UserReq;
import com.example.req.UserResetPasswordReq;
import com.example.req.UserSaveReq;
import com.example.resp.UserLoginResp;
import com.example.resp.UserResp;
import com.example.service.IUserService;
import com.example.utils.SnowFlake;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private SnowFlake snowFlake;

    @Override
    public List<UserResp> all() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        List<UserResp> userResps = baseMapper.selectList(lqw).stream()
                .map(user -> {
                    UserResp userResp = new UserResp();
                    BeanUtils.copyProperties(user, userResp);
                    return userResp;
                }).collect(Collectors.toList());

        return userResps;
    }

    public List<UserResp> findUser(UserReq userReq) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        // lqw.eq(!StringUtils.isEmpty(userReq.getLoginName()), User::getLoginName,
        // userReq.getLoginName());

        List<UserResp> userResps = baseMapper.selectList(lqw).stream().map(user -> {
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(user, userResp);
            return userResp;
        }).collect(Collectors.toList());

        return userResps;
    }

    public boolean save(UserSaveReq req) {
        User user = new User();
        boolean flag = false;
        BeanUtils.copyProperties(req, user);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            if (ObjectUtils.isEmpty(selectByLoginName(req.getLoginName()))) {
                user.setId(snowFlake.nextId());
                flag = baseMapper.insert(user) > 0 ? true : false;
            } else {
                // throw 用户已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新
            user.setLoginName(null); // 把登录名置为空，执行更新语句的时候就不会更新登录名字段，就达到了不可以修改登录名的功能
            user.setPassword(null);
            flag = baseMapper.updateById(user) > 0 ? true : false;
        }
        return flag;
    }

    public boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    /**
     * 校验用户名是否重复
     */
    public User selectByLoginName(String loginName) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        return baseMapper.selectOne(lqw.eq(User::getLoginName, loginName));
    }

    public void resetPassword(UserResetPasswordReq req) {
        User user = new User();
        BeanUtils.copyProperties(req, user);
        baseMapper.updateById(user);
    }

    @Override
    public UserLoginResp login(UserLoginReq req) {
        // 思考：拿用户名+密码去数据库查吗？
        // 还是先拿用户名查，然后去比对密码？
        User userDB = selectByLoginName(req.getLoginName());
        // 提示给自己看越详细越好，提示给别人看越笼统越好，防止拿数据撞库
        if (ObjectUtils.isEmpty(userDB)) {
            // 用户名不存在
            LOG.info("用户名不存在，{}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (userDB.getPassword().equals(req.getPassword())) {
                // 登录成功
                // UserLoginResq userLoginResq = CopyUtil.copy(userDB,UserLoginResq.class);
                // return userLoginResq;
                UserLoginResp userLoginResp = new UserLoginResp();
                BeanUtils.copyProperties(userDB, userLoginResp);
                return userLoginResp;
            } else {
                // 密码不正确
                LOG.info("密码不正确，输入的密码是：{}，数据库密码是：{}", req.getPassword(), userDB.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
        // return null;
    }

}
