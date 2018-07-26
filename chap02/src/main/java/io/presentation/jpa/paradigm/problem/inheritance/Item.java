package io.presentation.jpa.paradigm.problem.inheritance;

/**
 * Created By Minhyuk Yoon on 2018. 7. 26.
 */
public abstract class Item {

    private long id;
    private String name;
    private int price;

    protected Item(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
