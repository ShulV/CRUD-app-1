package org.app1.models;

import java.time.LocalDate;

public class Person {
    private int id;
    private String name;
    private String patronymic;
    private String surname;
    private LocalDate birthday;
//    public Person(int id, String name, String patronymic, String surname, LocalDate birthday) {
//        this.id = id;
//        this.name = name;
//        this.patronymic = patronymic;
//        this.surname = surname;
//        this.birthday = birthday;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getBirthdayYear() {
        return birthday.getYear();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
