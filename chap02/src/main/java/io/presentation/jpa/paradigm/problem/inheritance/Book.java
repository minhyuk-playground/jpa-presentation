package io.presentation.jpa.paradigm.problem.inheritance;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public class Book extends Item{

    private String author;
    private String isbn;

    public Book(long id, String name, int price, String author, String isbn) {
        super(id, name, price);
        this.author = author;
        this.isbn = isbn;
    }
}
