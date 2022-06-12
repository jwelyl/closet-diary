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
import java.util.Map;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    private final String STR = "MemberApiController class\n";

    @PostMapping("/api/v1/member")
    public Long save(@RequestBody MemberSaveRequestDto requestDto) {
//        System.out.println(STR + "저장된 내용\n" + requestDto);
        return memberService.save(requestDto);
    }

    @PutMapping("/api/v1/member/{id}")
    public Long update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto requestDto) {
//        System.out.println(STR + "업데이트된 id = " + id + "\n" + requestDto.toString());
        return memberService.update(id, requestDto);
    }

    @GetMapping("/api/v1/member/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
//        System.out.println(STR + "검색된 id = " + id + "\n");
        return memberService.findById(id);
    }

    @DeleteMapping("/api/v1/member/{id}")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
//        System.out.println(STR + "제거된 id = " + id);
        return id;
    }

//    @GetMapping("/api/v1/member")
//    List<Member> all() {
//        return memberService.all().findAll();
//    }
    @GetMapping("/api/v1/member")
    public List<MemberListResponseDto> all() {
//        System.out.println(STR + "모두 조회" );
        return memberService.findAll();
    }

    /* 추가 API */
    @PostMapping("/member/insert")  //  insert one
    public Member insert(@RequestBody Map<String, String> map) {
        Member m = Member.builder()
                .name(map.get("name"))
                .age(stringToInt(map.get("age")))
                .address(map.get("address"))
                .build();

        return memberService.getMemberRepository().save(m);
    }

    @GetMapping("/member/select")   //  select all
    public List<Member> selectAll() {
        return memberService.getMemberRepository().findAll();
    }

    @GetMapping("/member/select/{id}")  //  select one
    public Member selectOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Member m = memberService.getMemberRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id=" + id));
        return m;
    }

    @PutMapping("/member/modify/{id}")  //  update
    public Member updateOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Member m = memberService.getMemberRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id=" + id));

        m.update(map.get("name"), stringToInt(map.get("age")), map.get("address"));
        return memberService.getMemberRepository().save(m);
    }

    @DeleteMapping("/member/remove/{id}")   //  delete
    public Long deleteOne(@PathVariable("id") Long id) {
        memberService.getMemberRepository().deleteById(id);
        return id;
    }

    private Integer stringToInt(String age) {
        try {
            return Integer.parseInt(age);
        } catch(ClassCastException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
