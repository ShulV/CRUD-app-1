package org.app1.controllers;


import org.app1.dao.BookDAO;
import org.app1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public String bookPage(@PathVariable int id, Model model) {
        Optional<Book> book = bookDAO.getBook(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "books/book";
        }
        else {
            return "redirect:/books";
        }

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
