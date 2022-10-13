package org.app1.controllers;

import jakarta.validation.Valid;
import org.app1.dao.BookDAO;
import org.app1.models.Book;
import org.app1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDao) { this.bookDAO = bookDao; }

    //страница со списком всех книг
    @GetMapping()
    public String allBooksPage(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/all-books";
    }
    //страница с определенной книгой
    @GetMapping("/{id}")
    public String bookPage(@PathVariable String id, Model model) {
        return "books/book";
    }
    //страница добавления книги
    @GetMapping("/new")
    public String newBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "books/new-book";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new-book";

        bookDAO.save(book);
        return "redirect:/books";
    }
    //страница изменения книги
    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable String id, Model model) {
        return "books/edit-book";
    }
}
