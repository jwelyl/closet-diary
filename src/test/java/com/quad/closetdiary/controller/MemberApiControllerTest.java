package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.member.MemberSaveRequestDto;
import com.quad.closetdiary.controller.dto.member.MemberUpdateRequestDto;
import com.quad.closetdiary.domain.member.Member;
import com.quad.closetdiary.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void tearDown() throws Exception {
        memberRepository.deleteAll();
    }

    @Test
    public void Member_registered() throws Exception {
        //given
        String name = "name";
        Integer age = 123;
        String address = "address";
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .name(name)
                .age(age)
                .address(address)
                .build();

        String url = "http://localhost:" + port + "/api/v1/member";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getAge()).isEqualTo(age);
        assertThat(all.get(0).getAddress()).isEqualTo(address);
    }

    @Test
    public void Member_updated() throws Exception {
        //given
        Member savedMember = memberRepository.save(Member.builder()
                .name("name0")
                .age(123)
                .address("address0")
                .build());

        Long updateId = savedMember.getId();
        String expectedName = "name1";
        Integer expectedAge = 456;
        String expectedAddress = "address1";

        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder()
                .name(expectedName)
                .age(expectedAge)
                .address(expectedAddress)
                .build();

        String url = "http://localhost:" + port + "/api/v1/member/" + updateId;
        HttpEntity<MemberUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Member> all = memberRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getAge()).isEqualTo(expectedAge);
        assertThat(all.get(0).getAddress()).isEqualTo(expectedAddress);
    }
}
