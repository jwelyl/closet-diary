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

    @Builder
    public MemberUpdateRequestDto(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
