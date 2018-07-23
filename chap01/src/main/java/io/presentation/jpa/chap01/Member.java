package io.presentation.jpa.chap01;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author Minhyuk Yoon
 **/
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

    /**
     * JPA에서 Default 생성지는 필수 (public or protected)
     **/
    protected Member() {
    }

    public Member(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}
