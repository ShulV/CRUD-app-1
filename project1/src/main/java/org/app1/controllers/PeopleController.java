package org.app1.controllers;


import org.app1.dao.PersonDAO;
import org.app1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //страница со списком всех людей
    @GetMapping()
    public String allPeoplePage(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    //страница с определенным человеком
    @GetMapping("/{id}")
    public String personPage(@PathVariable String id, Model model) {
        return "people/person";
    }
    //страница добавления человека
    @GetMapping("/new")
    public String newPersonPage() {
        return "people/new-person";
    }
    //страница изменения человека
    @GetMapping("/{id}/edit")
    public String editPersonPage(@PathVariable String id, Model model) {
        return "people/edit-person";
    }
}
