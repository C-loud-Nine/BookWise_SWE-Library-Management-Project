package com.example.bookwisesweproject.patterns;

// Abstraction
public class BookGenre {
    public String genreName;

    public BookGenre(String genreName) {
        this.genreName = genreName;
    }
    //asif
    public void validateGenreName() {
        if (genreName == null || genreName.isEmpty()) {
            throw new IllegalArgumentException("Genre name cannot be null or empty");
        }
    }
}

