package org.example.ssmtest.controller;

import org.example.ssmtest.model.entity.Faq;
import org.example.ssmtest.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/faq")
public class FaqController {

    @Resource
    private FaqService faqService;

    @PreAuthorize("hasAuthority('faq:save')")
    @PostMapping
    public boolean add(@RequestBody Faq faq) {
        return faqService.addFaq(faq);
    }

    @PreAuthorize("hasAuthority('faq:getById')")
    @GetMapping("/{id}")
    public Faq getById(@PathVariable Long id) {
        return faqService.getFaqById(id);
    }

    @PreAuthorize("hasAuthority('faq:list')")
    @GetMapping("/list")
    public List<Faq> list() {
        return faqService.getAllFaqs();
    }

    @PreAuthorize("hasAuthority('faq:update')")
    @PutMapping
    public boolean update(@RequestBody Faq faq) {
        return faqService.updateFaq(faq);
    }

    @PreAuthorize("hasAuthority('faq:delete')")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return faqService.deleteFaq(id);
    }
}
