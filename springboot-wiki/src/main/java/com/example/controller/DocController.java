package com.example.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.example.resp.DocResp;
import com.example.service.IDocService;
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
 * 文档 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private IDocService docService;

    @GetMapping("/all")
    public JsonResult<List<DocResp>> all() {
        JsonResult<List<DocResp>> json = new JsonResult<>();
        json.setData(docService.all());
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    /**
     * 根据电子书id查询文档的方法,结果已经排好序
     */
    @GetMapping("/all/{ebookId}")
    public JsonResult<List<DocResp>> all(@PathVariable Long ebookId) {
        JsonResult<List<DocResp>> json = new JsonResult<>();
        json.setData(docService.all(ebookId));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @GetMapping("/find-content/{id}")
    public JsonResult<String> findContent(@PathVariable Long id) {
        JsonResult<String> json = new JsonResult<>();
        json.setData(docService.findContent(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody DocResp docResp) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(docService.save(docResp));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    // @DeleteMapping("/delete/{id}")
    // public JsonResult<Boolean> delete(@PathVariable Long id) {
    // JsonResult<Boolean> json = new JsonResult<>();
    // json.setData(docService.deleteById(id));
    // json.setCode(200);
    // json.setMsg("操作成功");
    // return json;
    // }

    @DeleteMapping("/delete/{idsStr}")
    public JsonResult<Boolean> delete(@PathVariable String idsStr) {
        // List<String> ids = Stream.of(idsStr).collect(Collectors.toList());
        List<String> ids = Arrays.asList(idsStr.split(","));
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(docService.delete(ids));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @GetMapping("/vote/{id}")
    public JsonResult<Boolean> vote(@PathVariable Long id) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(true);
        docService.increaseVetoCount(id);
        return json;
    }



}
