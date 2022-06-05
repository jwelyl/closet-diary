package com.quad.closetdiary.controller.dto.member;

import com.quad.closetdiary.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String address;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.address = entity.getAddress();
    }
}
