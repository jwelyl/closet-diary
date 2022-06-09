package com.quad.closetdiary.controller.dto.member;

import com.quad.closetdiary.domain.member.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberListResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String address;
//    private LocalDateTime modifiedDate;

    private final String STR = "\nMemberListResponseDto class\n";

    public MemberListResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.address = entity.getAddress();
//        this.modifiedDate = entity.getModifiedDate();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return STR + "id : " + id + "\nname : " + name + "\nage : " + age + "\naddress : " + address + "\n"/*"\nmodifiedDate : " + modifiedDate + "\n"*/;
    }
}
