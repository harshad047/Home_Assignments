package com.tss.test;

import com.tss.model.Book;
import com.tss.model.BookNameComparator;
import com.tss.model.BookTitleComparator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookTest {
    static final Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "books.dat";

    public static void main(String[] args) {
        List<Book> books = loadBooks();
        boolean loop = true;
        while (loop) {
            int choice = displayMenu();
            switch (choice) {
                case 1:
                    addBook(books);
                    break;
                case 2:
                    issueBook(books);
                    break;
                case 3:
                    displayAvailableBooks(books);
                    break;
                case 4:
                    displayIssuedBooks(books);
                    break;
                case 5:
                    returnBook(books);
                    break;
                case 6:
                    displayBooks(books);
                    break;
                case 7:
                    saveBooks(books);
                    System.out.println("Thank You For Visiting !!");
                    loop = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Enter Valid Choice");
            }
        }
    }

    private static int displayMenu() {
        System.out.println("+--------------------------------------+");
        System.out.println("|         Welcome To The Library       |");
        System.out.println("+--------------------------------------+");
        System.out.println("| 1. Add a Book                        |");
        System.out.println("| 2. Issue a Book                      |");
        System.out.println("| 3. Display All Available Books       |");
        System.out.println("| 4. Display All Issued Books          |");
        System.out.println("| 5. Return a Book                     |");
        System.out.println("| 6. Display All Books                 |");
        System.out.println("| 7. Exit                              |");
        System.out.println("+--------------------------------------+");
        System.out.print("Enter Your Choice: ");
        return scanner.nextInt();
    }

    private static void addBook(List<Book> books) {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        for (Book b : books) {
            if (b.getBookId() == id) {
                System.out.println("Book ID already exists! Try again.");
                return;
            }
        }
        scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private static void issueBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Nothing To issue because Book List is Empty !!");
            return;
        }
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getBookId() == id) {
                if (!book.checkisIssued()) {
                    book.setisIssued(true);
                    System.out.println("Book issued SuccessFully!!!");
                    return;
                }
                System.out.println("Book Already issued By Someone!!");
                return;
            }
        }
        System.out.println("Book is not Available!!");
    }

    private static void displayAvailableBooks(List<Book> books) {
        boolean found = false;

        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-20s | %-6s |\n", "ID", "Title", "Author", "Issued");
        System.out.println("+------------------------------------------------------------+");

        for (Book book : books) {
            if (!book.checkisIssued()) {
                System.out.printf("| %-5d | %-20s | %-20s | %-6s |\n",
                        book.getBookId(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
                        "No");
                found = true;
            }
        }

        if (!found) {
            System.out.println("| No available books found!                                 |");
        }

        System.out.println("+------------------------------------------------------------+");
    }

    private static void displayIssuedBooks(List<Book> books) {
        boolean found = false;

        System.out.println("+------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-20s | %-6s |\n", "ID", "Title", "Author", "Issued");
        System.out.println("+------------------------------------------------------------+");

        for (Book book : books) {
            if (book.checkisIssued()) {
                System.out.printf("| %-5d | %-20s | %-20s | %-6s |\n",
                        book.getBookId(),
                        book.getBookTitle(),
                        book.getBookAuthor(),
                        "Yes");
                found = true;
            }
        }

        if (!found) {
            System.out.println("| No issued books found!                                     |");
        }

        System.out.println("+------------------------------------------------------------+");
    }


    private static void returnBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No Book To Return Because Book list is empty !!");
            return;
        }
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        for (Book b : books) {
            if (b.getBookId() == id && b.checkisIssued()) {
                b.setisIssued(false);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Book List is Empty !!");
            return;
        }

        System.out.println("1. Sort by Author (Ascending)");
        System.out.println("2. Sort by Title (Descending)");
        int opt = scanner.nextInt();
        if (opt == 1) {
            Collections.sort(books, new BookNameComparator());
        } else if (opt == 2) {
            Collections.sort(books, new BookTitleComparator());
        }

        System.out.println("+----------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-20s | %-6s   |\n", "ID", "Title", "Author", "Issued");
        System.out.println("+----------------------------------------------------------------------+");

        for (Book book : books) {
            System.out.printf("| %-5d | %-20s | %-20s | %-6s |\n",
                    book.getBookId(),
                    book.getBookTitle(),
                    book.getBookAuthor(),
                    book.checkisIssued() ? "Yes" : "No");
        }

        System.out.println("+---------------------------------------------------------------+");
    }


    private static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(books);
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            books = (List<Book>) ois.readObject();
            System.out.println("Books loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved books found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }
}
