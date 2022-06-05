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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false) //  DB column
    private String name;

    private Integer age;

    @Column(length = 100)
    private String address;

    @Builder
    public Member(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void update(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
