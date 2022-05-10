package com.example.service;

import com.example.entity.EbookSnapshot;
import com.example.resp.StatisticResp;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 电子书快照表 服务类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-10
 */
public interface IEbookSnapshotService extends IService<EbookSnapshot> {
    public void genSnapshot();

    public List<StatisticResp> getStatistic();
    
    public List<StatisticResp> get30Statistic();
}
