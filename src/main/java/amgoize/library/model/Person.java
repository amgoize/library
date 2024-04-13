package amgoize.library.model;

import javax.validation.constraints.*;

public class Person {
    private int  id;
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2")
    @Pattern(regexp = "[A-Z]\\w+", message = "Name should be in this format 'Name'")
    private String name;
    @NotEmpty(message = "Surname should be not empty")
    @Size(min = 2, max = 30, message = "Surname should be between 2")
    @Pattern(regexp = "[A-Z]\\w+", message = "Surname should be in this format 'Surname'")
    private String surname;
    @NotEmpty(message = "Patronymic should be not empty")
    @Pattern(regexp = "([A-Z]\\w+|-)", message = "Patronymic should be in this format 'Patronymic' or " +
            "if you don't have a patronymic, then put '-'")
    private String patronymic;
    @Min(value = 1900, message = "Year of birth should be greater than 1900")
    @Max(value = 2024, message = "Year of birth should be less than 2024")
    private int yearOfBirth;

    public Person() {
    }
    public Person(int id, String name, String surname, String patronymic, int year_of_birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.yearOfBirth = year_of_birth;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){this.id = id;}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
