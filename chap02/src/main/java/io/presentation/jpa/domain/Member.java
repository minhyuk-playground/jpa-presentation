package io.presentation.jpa.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Minhyuk Yoon on 2018. 7. 24.
 */
@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Embedded
    @Column(name = "address")
    private Address address;

    protected Member() {
    }

    public Member(String id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void changeName(String name) {
        if (name.trim().isEmpty() || name == null) {
            return;
        }
        this.name = name;
    }

    public void changeAge(int age) {
        if (age <= 0) {
            return;
        }
        this.age = age;
    }

    public void changeAddress(Address address) {
        this.address.changeAddress(address);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
