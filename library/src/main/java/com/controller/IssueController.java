package com.controller;

import com.entity.Book;
import com.entity.BookReader;
import com.entity.Reader;
import com.service.BookReaderService;
import com.service.BookService;
import com.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/issueBook/*")
public class IssueController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookReaderService bookReaderService;

    @RequestMapping(value = "/issue/{id}")
    public ModelAndView issueBook(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("issue_book");
        List<Reader> readers = readerService.getReaderList();
        Book book = bookService.get(id);
        List<Book> books = bookService.getBooks();
        modelAndView.addObject("allReaders", readers);
        modelAndView.addObject("currentBook", book);
        modelAndView.addObject("allBooks", books);
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam Long idBook, @RequestParam Long idReader){
        bookReaderService.delete(idBook, idReader);
        return "redirect:/";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("bookReader") BookReader bookReader) {
        ModelAndView modelAndView = new ModelAndView();
        bookReaderService.save(bookReader);
        return "redirect:/";
    }

    @RequestMapping(value = "/issuedList")
    public ModelAndView issuedBooks(@RequestParam(defaultValue = "0") int page){
        ModelAndView modelAndView = new ModelAndView("issued_list");
        Page<BookReader> bookReaders = bookReaderService.findAll(page, 9);
        modelAndView.addObject("bookReaders", bookReaders);
        modelAndView.addObject("activePage", page);
        return modelAndView;
    }
}
