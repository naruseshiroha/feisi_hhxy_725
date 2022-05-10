package com.example.service.impl;

import com.example.entity.EbookSnapshot;
import com.example.mapper.EbookSnapshotMapper;
import com.example.resp.StatisticResp;
import com.example.service.IEbookSnapshotService;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电子书快照表 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-05-10
 */
@Service
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot>
        implements IEbookSnapshotService {

    @Resource
    private EbookSnapshotMapper ebookSnapshotMapper;

    @Override
    public void genSnapshot() {
        ebookSnapshotMapper.genSnapshot();
    }

    @Override
    public List<StatisticResp> getStatistic() {
        return ebookSnapshotMapper.getStatistic();
    }

    @Override
    public List<StatisticResp> get30Statistic() {
        return ebookSnapshotMapper.get30Statistic();
    }

}
