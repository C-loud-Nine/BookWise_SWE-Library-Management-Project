package com.example.bookwisesweproject.patterns;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testBookInitialization() {
        // Arrange
        String author = "asif";
        String name = "Harry Potter";
        String isbn = "978-3-16-148410-0";
        String genre = "Fantasy";

        // Act
        Book book = new Book(author, name, isbn, genre);

        // Assert
        assertEquals(author, book.author);
        assertEquals(name, book.name);
        assertEquals(isbn, book.isbn);
        assertEquals(genre, book.genre);
    }
}
