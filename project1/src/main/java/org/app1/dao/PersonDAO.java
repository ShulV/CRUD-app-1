package org.app1.dao;

import org.app1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM People", new PersonRowMapper());
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO public.people(name, patronymic, surname, birthday)" +
                        "VALUES (?, ?, ?, ?);",
                person.getName(),
                person.getPatronymic(),
                person.getSurname(),
                person.getBirthday());
    }
}
