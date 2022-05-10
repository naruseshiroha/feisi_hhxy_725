package com.example.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.example.req.CategoryReq;
import com.example.resp.CategoryResp;
import com.example.service.ICategoryService;
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
 * 分类 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private ICategoryService categoryService;

    @GetMapping("/all")
    public JsonResult<List<CategoryResp>> findAllCategory() {
        JsonResult<List<CategoryResp>> json = new JsonResult<>();
        // json.setData(PageHelper.startPage(categoryReq.getPageNum(),categoryReq.getPageSize())
        // .doSelectPageInfo(() -> categoryService.findAllCategory(categoryReq)));
        json.setData(categoryService.all());
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @GetMapping("/findCategory")
    public JsonResult<List<CategoryResp>> findCategory(@Valid CategoryReq categoryReq) {
        JsonResult<List<CategoryResp>> json = new JsonResult<>();
        // json.setData(PageHelper.startPage(categoryReq.getPageNum(),categoryReq.getPageSize())
        // .doSelectPageInfo(() -> categoryService.findAllCategory(categoryReq)));
        json.setData(categoryService.findCategory(categoryReq));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody CategoryResp categoryResp) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(categoryService.save(categoryResp));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult<Boolean> delete(@PathVariable Long id) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(categoryService.deleteById(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

}
