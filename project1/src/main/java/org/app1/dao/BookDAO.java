package org.app1.dao;

import org.app1.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BookRowMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO public.books(name, author, date) " +
                        "VALUES (?, ?, ?);",
                book.getName(),
                book.getAuthor(),
                book.getDate());
    }
}
