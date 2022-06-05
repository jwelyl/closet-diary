package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.member.MemberResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberSaveRequestDto;
import com.quad.closetdiary.controller.dto.member.MemberUpdateRequestDto;
import com.quad.closetdiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto) {
        return memberService.save(requestDto);
    }

    @PutMapping("/api/v1/member/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @DeleteMapping("/api/v1/member/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }
}
