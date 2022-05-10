package com.example.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.example.req.EbookReq;
import com.example.resp.EbookResp;
import com.example.service.IEbookService;
import com.example.utils.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 电子书 前端控制器
 * </p>
 *
 * @author lixun
 * @since 2022-02-23
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {

    Logger logger = LoggerFactory.getLogger(EbookController.class);

    @Resource
    private IEbookService ebookService;

    @GetMapping("/findAllEbook")
    public JsonResult<PageInfo<EbookResp>> findAllEbook(@Valid EbookReq ebookReq) {
        JsonResult<PageInfo<EbookResp>> json = new JsonResult<>();
        json.setData(PageHelper.startPage(ebookReq.getPageNum(), ebookReq.getPageSize())
                .doSelectPageInfo(() -> ebookService.findAllEbook(ebookReq)));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody EbookResp ebookResp) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(ebookService.save(ebookResp));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }

    @DeleteMapping("/delete/{id}")
    public JsonResult<Boolean> delete(@PathVariable Long id) {
        JsonResult<Boolean> json = new JsonResult<>();
        json.setData(ebookService.deleteById(id));
        json.setCode(200);
        json.setMsg("操作成功");
        return json;
    }


}
