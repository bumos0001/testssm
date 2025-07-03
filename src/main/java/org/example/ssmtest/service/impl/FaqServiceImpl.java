package org.example.ssmtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.ssmtest.mapper.FaqMapper;
import org.example.ssmtest.model.entity.Faq;
import org.example.ssmtest.service.FaqService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl extends ServiceImpl<FaqMapper, Faq> implements FaqService {

    // 🔹 新增 FAQ
    @Override
    public boolean addFaq(Faq faq) {
        return this.save(faq); // 使用 MyBatis-Plus save() 方法
    }

    // 🔹 根據 ID 查詢 FAQ
    @Override
    public Faq getFaqById(Long id) {
        return this.getById(id);
    }

    // 🔹 查詢所有 FAQ（可加排序）
    @Override
    public List<Faq> getAllFaqs() {
        return this.lambdaQuery()
                .orderByDesc(Faq::getUpdatedAt)
                .list();
    }

    // 🔹 更新 FAQ
    @Override
    public boolean updateFaq(Faq faq) {
        return this.updateById(faq);
    }

    // 🔹 刪除 FAQ
    @Override
    public boolean deleteFaq(Long id) {
        return this.removeById(id);
    }
}

