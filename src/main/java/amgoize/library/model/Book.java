package amgoize.library.model;

import javax.validation.constraints.*;

public class Book {
    private int id;
    @NotEmpty(message = "Book`s name should be not empty")
    @Size(min = 1, max = 50, message = "Book`s name should be between 1")
    private String nameBook;
    @NotEmpty(message = "Author`s name should be not empty")
    @Size(min = 2, max = 30, message = "Author`s name should be between 2")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Author`s name should be in this format 'Name Surname'")
    private String author;
    @Min(value = 1400, message = "Year of birth should be greater than 1400")
    @Max(value = 2025, message = "Year of birth should be less than 2025")
    private int yearOfBook;

    private Integer own;

    public Book() {
    }

    public Book(int id, String nameBook, String author, int yearOfBook, Integer own) {
        this.id = id;
        this.nameBook = nameBook;
        this.author = author;
        this.yearOfBook = yearOfBook;
        this.own = own;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfBook() {
        return yearOfBook;
    }

    public void setYearOfBook(int yearOfBook) {
        this.yearOfBook = yearOfBook;
    }

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }
}
