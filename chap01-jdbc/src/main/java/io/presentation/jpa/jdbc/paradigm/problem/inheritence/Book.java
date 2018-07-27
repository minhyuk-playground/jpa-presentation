package io.presentation.jpa.jdbc.paradigm.problem.inheritence;

/**
 * Created By Minhyuk Yoon on 2018. 7. 27.
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
