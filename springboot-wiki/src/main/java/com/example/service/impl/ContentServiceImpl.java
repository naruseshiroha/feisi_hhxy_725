package com.example.service.impl;

import com.example.entity.Content;
import com.example.mapper.ContentMapper;
import com.example.req.ContentReq;
import com.example.resp.ContentResp;
import com.example.service.IContentService;
import com.example.utils.SnowFlake;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 文档内容 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    @Resource
    private SnowFlake snowFlake;

    @Override
    public List<ContentResp> findContent(ContentReq contentReq) {
        LambdaQueryWrapper<Content> lqw = new LambdaQueryWrapper<>();
        lqw.eq(!StringUtils.isEmpty(contentReq.getContent()), Content::getContent, contentReq.getContent());

        List<ContentResp> contentResps = baseMapper.selectList(lqw).stream().map(content -> {
            ContentResp contentResp = new ContentResp();
            BeanUtils.copyProperties(content, contentResp);
            return contentResp;
        }).collect(Collectors.toList());

        return contentResps;
    }

    @Override
    public ContentResp selectById(Long id) {
        ContentResp contentResp = new ContentResp();
        Content content = baseMapper.selectById(id);
        BeanUtils.copyProperties(content, contentResp);
        return contentResp;
    }

    @Override
    public boolean save(ContentResp contentResp) {
        Content content = new Content();
        boolean flag = false;
        BeanUtils.copyProperties(contentResp, content);
        if (ObjectUtils.isEmpty(contentResp.getId())) {
            // 新增
            content.setId(snowFlake.nextId());
            flag = baseMapper.insert(content) > 0 ? true : false;
        } else {
            // 更新
            flag = baseMapper.updateById(content) > 0 ? true : false;
        }
        return flag;
    }

    @Override
    public boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

}
