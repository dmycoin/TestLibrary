package com.controller;

import com.entity.*;
import com.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reader/*")
public class ReaderController {
    @Autowired
    public ReaderService readerService;

    @RequestMapping("/readerList")
    public String viewReaderListPage(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Reader> readers = readerService.getReaderList(page, 9);
        model.addAttribute("readers", readers);
        model.addAttribute("activePage", page);
        return "readers";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("reader") Reader reader) {
        readerService.save(reader);
        return "redirect:/reader/getReaderList";
    }

    @RequestMapping(value = "/newReader", method = RequestMethod.GET)
    public String addNewReader() {
        return "new_reader";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id){
        readerService.delete(id);
        return "redirect:/reader/getReaderList";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editReader(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_reader");
        Reader reader = readerService.findById(id);
        mav.addObject("reader", reader);
        return mav;
    }
}
