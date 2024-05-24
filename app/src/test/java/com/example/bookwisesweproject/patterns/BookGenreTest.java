package com.example.bookwisesweproject.patterns;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookGenreTest {

    @Test
    public void testValidateGenreNameThrowsExceptionWhenNull() {
        BookGenre bookGenre = new BookGenre(null);

        assertThrows(IllegalArgumentException.class, bookGenre::validateGenreName);
    }

    @Test
    public void testValidateGenreNameThrowsExceptionWhenEmpty() {
        BookGenre bookGenre = new BookGenre("");

        assertThrows(IllegalArgumentException.class, bookGenre::validateGenreName);
    }

    @Test
    public void testValidateGenreNameDoesNotThrowExceptionWhenValid() {
        BookGenre bookGenre = new BookGenre("Science Fiction");

        // No exception should be thrown, so we just call the method
        bookGenre.validateGenreName();
    }
}
