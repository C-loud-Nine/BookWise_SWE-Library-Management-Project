package com.example.bookwisesweproject.patterns;

// Occurrence
public class LibraryItem {
    public String barcode;

    public Book book;

    public String avail;

    public LibraryItem(String barcode, Book book, String avail) {
        this.barcode = barcode;
        this.book = book;
        this.avail = avail;
    }

    public LibraryItem() {
        this.avail = "available";
    }
}
