package com.example.mapper;

import com.example.entity.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 文档 Mapper 接口
 * </p>
 *
 * @author Administrator
 * @since 2022-03-29
 */
public interface DocMapper extends BaseMapper<Doc> {

    void updateEbookInfo();
}
