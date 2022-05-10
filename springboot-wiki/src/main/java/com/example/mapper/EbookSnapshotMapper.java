package com.example.mapper;

import com.example.entity.EbookSnapshot;
import com.example.resp.StatisticResp;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 电子书快照表 Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2022-05-10
 */
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {
    public void genSnapshot();

    public List<StatisticResp> getStatistic();

    public List<StatisticResp> get30Statistic();
}
