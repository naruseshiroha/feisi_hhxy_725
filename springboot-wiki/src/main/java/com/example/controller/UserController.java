package com.example.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.example.req.UserLoginReq;
import com.example.req.UserReq;
import com.example.req.UserResetPasswordReq;
import com.example.req.UserSaveReq;
import com.example.resp.UserLoginResp;
import com.example.resp.UserResp;
import com.example.service.IUserService;
import com.example.utils.JsonResult;
import com.example.utils.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2022-05-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    // @GetMapping("/findAllUser")
    // public JsonResult<List<UserResp>> findAllUser(@Valid UserReq userReq) {
    // JsonResult<List<UserResp>> json = new JsonResult<>();
    // json.setData(PageHelper.startPage(userReq.getPageNum(),
    // userReq.getPageSize())
    // .doSelectPageInfo(() -> userService.all()));
    // json.setData(userService.all());
    // json.setCode(200);
    // json.setMsg("操作成功");
    // return json;
    // }

    @GetMapping("/findAllUser")
    public JsonResult<PageInfo<UserResp>> findUser(@Valid UserReq userReq) {
        JsonResult<PageInfo<UserResp>> json = new JsonResult<>();
        json.setData(PageHelper.startPage(userReq.getPageNum(), userReq.getPageSize())
                .doSelectPageInfo(() -> userService.findUser(userReq)));
        // json.setData(userService.findUser(userReq));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody UserSaveReq req) {
        // md5 加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));

        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(userService.save(req));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult<Boolean> delete(@PathVariable Long id) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(userService.deleteById(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/reset-password")
    public JsonResult<Boolean> resetPassword(@RequestBody UserResetPasswordReq req) {
        JsonResult<Boolean> json = new JsonResult<>();
        // System.out.println("before password -》 " + req.getPassword());
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        // System.out.println("after password -》" + req.getPassword());
        userService.resetPassword(req);

        json.setCode(200);
        json.setData(true);
        json.setMsg("重置成功");
        return json;
    }

    @PostMapping("/login")
    public JsonResult<UserLoginResp> login(@RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        JsonResult<UserLoginResp> json = new JsonResult<>();
        UserLoginResp userLoginResp = userService.login(req);
        Long token = snowFlake.nextId(); // token
        userLoginResp.setToken(token);
        // 序列化 UserLoginResp
        redisTemplate.opsForValue().set(token.toString(), userLoginResp, 3600 * 24, TimeUnit.SECONDS);
        json.setCode(200);
        json.setData(userLoginResp);
        json.setMsg("登录成功");
        return json;
    }

    @GetMapping("/logout/{token}")
    public JsonResult<Boolean> logout(@PathVariable String token) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setCode(200);
        json.setData(redisTemplate.delete(token));
        json.setMsg("退出登录");
        LOG.info("从redis中移除token：{}", token);
        return json;
    }

}
