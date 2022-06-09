package com.quad.closetdiary.controller.dto.member;

import com.quad.closetdiary.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String name;
    private Integer age;
    private String address;

    private final String STR = "\nMemberSaveRequestDto class\n";

    @Builder
    public MemberSaveRequestDto(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println(this);
        System.out.println("생성자");
    }

    public Member toEntity() {
//        return Member.builder()
//                .name(name)
//                .age(age)
//                .address(address)
//                .build();

        Member m = Member.builder()
                .name(name)
                .age(age)
                .address(address)
                .build();

        System.out.println(STR);
        System.out.println("toEntity()");
        System.out.println(m);
        return m;
    }

    @Override
    public String toString() {
        return STR + "name : " + name + "\nage : " + age + "\naddress : " + address + "\n";
    }
}
