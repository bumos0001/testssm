package org.example.ssmtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.ssmtest.model.entity.Faq;

import java.util.List;

public interface FaqService extends IService<Faq> {

    boolean addFaq(Faq faq);

    Faq getFaqById(Long id);

    List<Faq> getAllFaqs();

    boolean updateFaq(Faq faq);

    boolean deleteFaq(Long id);
}
