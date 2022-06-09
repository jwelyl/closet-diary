package com.quad.closetdiary.controller.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String name;
    private Integer age;
    private String address;

    private final String STR = "\nMemberUpdateRequestDto class\n";

    @Builder
    public MemberUpdateRequestDto(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;

        System.out.println(this);
        System.out.println("생성자");
    }

    @Override
    public String toString() {
        return STR + "name : " + name + "\nage : " + age + "\naddress : " + address + "\n";
    }
}
