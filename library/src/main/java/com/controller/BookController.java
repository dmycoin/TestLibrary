package com.controller;


import com.dao.BookRepository;
import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PublisherService publisherService;

    @RequestMapping("/")
    public String viewHomePage(Model model,@RequestParam(defaultValue = "0") int page) {
        Page<Book> books = bookService.getBooks(page, 9);
        model.addAttribute("bookList", books);
        model.addAttribute("activePage", page);
        return "index";
    }

    @RequestMapping("/newBook")
    public String showNewBookForm(Model model) {
        List<Genre> genres = genreService.genreList();
        List<Author> authors = authorService.authorsList();
        List<Publisher> publishers = publisherService.publisherList();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);
        return "new_book";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("book") Book book){
        bookService.save(book);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id){
        ModelAndView mav = new ModelAndView("edit_book");
        List<Genre> genres = genreService.genreList();
        List<Author> authors = authorService.authorsList();
        List<Publisher> publishers = publisherService.publisherList();
        Book book = bookService.get(id);
        mav.addObject("book", book);
        mav.addObject("genres", genres);
        mav.addObject("authors", authors);
        mav.addObject("publishers", publishers);
        return mav;
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id){
        bookService.delete(id);
        return "redirect:/";
    }
}
