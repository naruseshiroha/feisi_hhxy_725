package com.example.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.example.req.ContentReq;
import com.example.resp.ContentResp;
import com.example.service.IContentService;
import com.example.utils.JsonResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文档内容 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Resource
    private IContentService contentService;


    @GetMapping("/findContent")
    public JsonResult<List<ContentResp>> findContent(@Valid ContentReq contentReq) {
        JsonResult<List<ContentResp>> json = new JsonResult<>();
        // json.setData(PageHelper.startPage(contentReq.getPageNum(),contentReq.getPageSize())
        // .doSelectPageInfo(() -> contentService.findAllContent(contentReq)));
        json.setData(contentService.findContent(contentReq));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @GetMapping("/select")
    public JsonResult<ContentResp> selectById(@Valid Long id) {
        JsonResult<ContentResp> json = new JsonResult<>();
        json.setData(contentService.selectById(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody ContentResp contentResp) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(contentService.save(contentResp));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult<Boolean> delete(@PathVariable Long id) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(contentService.deleteById(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

}
