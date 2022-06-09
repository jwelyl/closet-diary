package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.member.MemberListResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberSaveRequestDto;
import com.quad.closetdiary.controller.dto.member.MemberUpdateRequestDto;
import com.quad.closetdiary.domain.member.Member;
import com.quad.closetdiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    private final String STR = "MemberApiController class\n";

    @PostMapping("/api/v1/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto) {
        System.out.println(STR + "저장된 내용\n" + requestDto);
        return memberService.save(requestDto);
    }

    @PutMapping("/api/v1/member/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        System.out.println(STR + "업데이트된 id = " + id + "\n" + requestDto.toString());
        return memberService.update(id, requestDto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
        System.out.println(STR + "검색된 id = " + id + "\n");
        return memberService.findById(id);
    }

    @DeleteMapping("/api/v1/member/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        System.out.println(STR + "제거된 id = " + id);
        return id;
    }

//    @GetMapping("/api/v1/member")
//    List<Member> all() {
//        return memberService.all().findAll();
//    }
    @GetMapping("/api/v1/member")
    public List<MemberListResponseDto> all() {
        System.out.println(STR + "모두 조회" );
        return memberService.findAll();
    }
}
