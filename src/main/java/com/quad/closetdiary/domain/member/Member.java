package com.quad.closetdiary.domain.member;

import com.quad.closetdiary.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor  //  기본 생성자 생성
@Entity
public class Member extends BaseTimeEntity {
    @Transient
    private final String STR = "\nMember class\n";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false) //  DB column
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(length = 100)
    private String address;

    @Builder
    public Member(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
//        System.out.println(STR + "constructor " + this);
    }

    public void update(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
//        System.out.println(STR + "update " + this);
    }

    @Override
    public String toString() {
        return STR + "\nMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
