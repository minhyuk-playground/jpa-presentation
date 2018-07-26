package io.presentation.jpa.paradigm.problem.inheritance;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public class Movie extends Item {

    private String director;
    private String actor;

    public Movie(long id, String name, int price, String director, String actor) {
        super(id, name, price);
        this.director = director;
        this.actor = actor;
    }
}
