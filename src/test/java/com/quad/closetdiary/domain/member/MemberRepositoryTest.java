package com.quad.closetdiary.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void get_member() {
        //given
        String name1 = "Zzit";
        Integer age1 = 58;
        String address1 = "Andong";

        String name2 = "Hang";
        Integer age2 = 61;
        String address2 = "Seoul";

        memberRepository.save(Member.builder()
                .name(name1)
                .age(age1)
                .address(address1)
                .build());
        memberRepository.save(Member.builder()
                .name(name2)
                .age(age2)
                .address(address2)
                .build());

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member1 = memberList.get(0);
        Member member2 = memberList.get(1);

        assertThat(member1.getName()).isEqualTo(name1);
        assertThat(member1.getAge()).isEqualTo(age1);
        assertThat(member1.getAddress()).isEqualTo(address1);

        assertThat(member2.getName()).isEqualTo(name2);
        assertThat(member2.getAge()).isEqualTo(age2);
        assertThat(member2.getAddress()).isEqualTo(address2);
    }
}
