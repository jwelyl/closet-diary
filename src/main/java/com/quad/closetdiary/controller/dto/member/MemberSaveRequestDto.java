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
    @Builder
    public MemberSaveRequestDto(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .address(address)
                .build();
    }
}
