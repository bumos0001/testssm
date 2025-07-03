package org.example.ssmtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.ssmtest.model.entity.Faq;

@Mapper
public interface FaqMapper extends BaseMapper<Faq> {
}
