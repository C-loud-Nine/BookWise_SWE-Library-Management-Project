package com.example.bookwisesweproject.patterns;

import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryItemTest {

    @Test
    public void testLibraryItemInitializationWithParameters() {
        // Arrange
        String barcode = "1234567890";
        Book book = new Book("asif", "Harry Potter", "978-3-16-148410-0", "Fantasy");
        String avail = "checked out";
        LibraryItem libraryItem = new LibraryItem(barcode, book, avail);

        // Assert
        assertEquals("Barcode should be 1234567890", barcode, libraryItem.barcode);
        assertEquals("Book author should be asif", book, libraryItem.book);
        assertEquals("Availability should be checked out", avail, libraryItem.avail);
    }

    @Test
    public void testLibraryItemInitializationWithoutParameters() {
        // Act
        LibraryItem libraryItem = new LibraryItem();

        // Assert
        assertNull("Barcode should be null", libraryItem.barcode);
        assertNull("Book should be null", libraryItem.book);
        assertEquals("Availability should be available", "available", libraryItem.avail);
    }
}
