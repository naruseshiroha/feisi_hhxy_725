package com.example.service.impl;

import com.example.entity.Content;
import com.example.entity.Doc;
import com.example.exception.BusinessException;
import com.example.exception.BusinessExceptionCode;
import com.example.mapper.ContentMapper;
import com.example.mapper.DocMapper;
import com.example.service.IDocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.resp.DocResp;
import com.example.utils.RedisUtil;
import com.example.utils.RequestContext;
import com.example.utils.SnowFlake;
import com.example.websocket.WebSocketServer;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 文档 服务实现类
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private WebSocketServer webSocketServer;

    @Override
    public List<DocResp> all() {
        List<DocResp> docResps = baseMapper.selectList(null).stream().map(doc -> {
            DocResp docResp = new DocResp();
            BeanUtils.copyProperties(doc, docResp);
            return docResp;
        }).collect(Collectors.toList());

        return docResps;
    }

    @Override
    public List<DocResp> all(Long ebookId) {
        LambdaQueryWrapper<Doc> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Doc::getEbookId, ebookId)
                .orderByAsc(Doc::getSort);

        List<DocResp> docResps = baseMapper.selectList(lqw).stream().map(doc -> {
            DocResp docResp = new DocResp();
            BeanUtils.copyProperties(doc, docResp);
            return docResp;
        }).collect(Collectors.toList());

        return docResps;
    }

    public String findContent(Long id) {
        LambdaQueryWrapper<Content> lqw = new LambdaQueryWrapper<>();
        lqw.eq(!ObjectUtils.isNull(id), Content::getId, id);
        increaseViewCount(id);
        Content content = contentMapper.selectById(id);
        return content.getContent();
    }

    @Transactional
    public boolean save(DocResp docResp) {
        boolean flag = false;
        int a = 0, b = 0;
        Doc doc = new Doc();
        Content content = new Content();
        BeanUtils.copyProperties(docResp, doc);
        BeanUtils.copyProperties(docResp, content);
        if (ObjectUtils.isEmpty(docResp.getId())) {
            // 新增
            doc.setId(snowFlake.nextId());
            doc.setViewCount(0);
            doc.setVoteCount(0);
            a = baseMapper.insert(doc);
            content.setId(doc.getId());
            b = contentMapper.insert(content);
            flag = (a > 0 && b > 0) ? true : false;
        } else {
            // 更新
            a = baseMapper.updateById(doc);
            b = contentMapper.updateById(content);
            if (b == 0) {
                contentMapper.insert(content);
            }
            flag = (a > 0 && b > 0) ? true : false;
        }
        return flag;
    }

    public boolean delete(List<String> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    public boolean deleteById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public void increaseViewCount(Long id) {
        LambdaUpdateWrapper<Doc> luw = new LambdaUpdateWrapper<>();
        // LambdaQueryWrapper<Doc> lqw = new LambdaQueryWrapper<>();
        Doc doc = baseMapper.selectById(id);
        System.err.println("doc is " + doc);
        if (doc.getViewCount() == null)
            doc.setViewCount(0);
        luw.set(Doc::getViewCount, doc.getViewCount() + 1).eq(Doc::getId, id);
        this.update(luw);
    }

    @Override
    public void increaseVetoCount(Long id) {
        LambdaUpdateWrapper<Doc> luw = new LambdaUpdateWrapper<>();
        // LambdaQueryWrapper<Doc> lqw = new LambdaQueryWrapper<>();
        Doc doc = baseMapper.selectById(id);
        luw.set(Doc::getVoteCount, doc.getVoteCount() + 1).eq(Doc::getId, id);

        if (doc.getVoteCount() == null)
            doc.setVoteCount(0);
        String ip = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + ip, 5000)) {
            this.update(luw);
            // 1.
            String docName = doc.getName();
            String message = "【" + docName + "】" + "被点赞！";
            // 2. sendInfo 推送消息
            webSocketServer.sendInfo(message);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }


    }

    /**
     * 统计阅读数，点赞数
     */
    @Override
    public void updateEbookInfo() {
       docMapper.updateEbookInfo();
    }
}
