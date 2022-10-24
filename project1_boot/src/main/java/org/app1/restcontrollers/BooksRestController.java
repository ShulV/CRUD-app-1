package org.app1.restcontrollers;

import org.app1.dao.BookDAO;
import org.app1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/books")
public class BooksRestController {
    private final BookDAO bookDAO;

    @Autowired
    public BooksRestController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public List<Book> allBooks() {
        return bookDAO.index();
    }

    //запрос на получение страницы со списком книг, у которых есть подстрока word
    @GetMapping("/{word}")
    public List<Book> selectedBooksPage(@PathVariable String word) {
        return bookDAO.getBooksByWordMatching(word);
    }
}
