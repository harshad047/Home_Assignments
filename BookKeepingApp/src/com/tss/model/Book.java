package com.tss.model;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private boolean isIssued;

    public Book(int bookId, String bookTitle, String bookAuthor) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.isIssued = false;
    }

    public int getBookId()
    {
        return bookId;
    }
    public String getBookTitle()
    {
        return bookTitle;
    }
    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public boolean checkisIssued()
    {
        return isIssued;
    }

    public void setisIssued(boolean value)
    {
        this.isIssued = value;
    }
    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: " + bookTitle + ", Author: " + bookAuthor + ", Issued: " + isIssued;
    }
}
