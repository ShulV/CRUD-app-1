package org.app1.controllers;



import org.app1.dao.PersonDAO;
import org.app1.models.Book;
import org.app1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //запрос на получение страницы со списком всех людей
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    //запрос на получение страницы с определенным человеком
    @GetMapping("/{id}")
    public String personPage(@PathVariable int id, Model model) {
        Optional<Person> person = personDAO.getPerson(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            model.addAttribute("books", personDAO.getBooksByPerson(id));
            return "people/person";
        }
        else {
            return "redirect:/people";
        }
    }

    //запрос на получение страницы добавления человека
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new-person";
    }

    //запрос на добавление человека
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new-person";

        personDAO.save(person);
        return "redirect:/people";
    }

    //запрос на получение страницы изменения человека
    @GetMapping("/{id}/edit")
    public String editPersonPage(@PathVariable int id, Model model) {
        return "people/edit-person";
    }

    //запрос на удаление человека
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
