package com.example.controller;

import java.util.List;

import javax.annotation.Resource;

import com.example.resp.StatisticResp;
import com.example.service.IEbookSnapshotService;
import com.example.utils.JsonResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 电子书快照表 前端控制器
 * </p>
 *
 * @author Administrator
 * @since 2022-05-10
 */
@RestController
@RequestMapping("/ebook-snapshot")
public class EbookSnapshotController {

    @Resource
    private IEbookSnapshotService ebookSnapshotService;

    @GetMapping("/get-statistic")
    public JsonResult<List<StatisticResp>> getStatistic() {
        List<StatisticResp> data = ebookSnapshotService.getStatistic();
        JsonResult<List<StatisticResp>> json = new JsonResult<>();
        json.setData(data);
        return json;
    }

    @GetMapping("/get-30-statistic")
    public JsonResult<List<StatisticResp>> get30Statistic() {
        List<StatisticResp> data = ebookSnapshotService.get30Statistic();
        JsonResult<List<StatisticResp>> json = new JsonResult<>();
        json.setData(data);
        return json;
    }
}
