package org.app1.controllers;

import org.app1.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String newBookPage() {
        return "books/new-book";
    }
    //страница изменения книги
    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable String id, Model model) {
        return "books/edit-book";
    }
}
