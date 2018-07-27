package io.presentation.jpa.jdbc.paradigm.problem.inheritence;

/**
 * Created By Minhyuk Yoon on 2018. 7. 27.
 */
public class Album extends Item{

    private String artist;

    public Album(long id, String name, int price, String artist) {
        super(id, name, price);
        this.artist = artist;
    }
}
