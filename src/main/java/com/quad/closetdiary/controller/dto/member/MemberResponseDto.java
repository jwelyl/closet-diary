package com.quad.closetdiary.controller.dto.member;

import com.quad.closetdiary.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String address;

    private final String STR = "\nMemberResponseDto class\n";

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.address = entity.getAddress();

//        System.out.println(STR + "생성자\n");
//        System.out.println(entity);
//        System.out.println(this);
    }

    @Override
    public String toString() {
        return STR + "id : " + id + "\nname : " + name + "\nage : " + age + "\naddress : " + address + "\n";
    }
}
