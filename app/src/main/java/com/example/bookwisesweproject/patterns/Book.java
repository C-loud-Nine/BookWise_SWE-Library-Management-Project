package com.example.bookwisesweproject.patterns;

// Abstraction
public class Book {
    public String author;
    public String name;
    public String isbn;
    public String genre;

    public Book(String author, String name, String isbn, String genre) {
        this.author = author;
        this.name = name;
        this.isbn = isbn;
        this.genre = genre;
    }
}
