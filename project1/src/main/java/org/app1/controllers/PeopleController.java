package org.app1.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {
    //страница со списком всех людей
    @GetMapping()
    public String allPeoplePage(Model model) {
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
