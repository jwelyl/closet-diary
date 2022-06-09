package com.quad.closetdiary.service;

import com.quad.closetdiary.controller.dto.member.MemberListResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberSaveRequestDto;
import com.quad.closetdiary.controller.dto.member.MemberUpdateRequestDto;
import com.quad.closetdiary.domain.member.Member;
import com.quad.closetdiary.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final String STR = "\nMemberService class\n";

    @Transactional
    public Long save(MemberSaveRequestDto requestDto) {
        System.out.println(STR + "save");
        System.out.println(requestDto);
        System.out.println(requestDto.toEntity());

        return memberRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        System.out.println(STR + "update");
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id=" + id));

        System.out.println(requestDto);
        member.update(requestDto.getName(), requestDto.getAge(), requestDto.getAddress());

        return id;
    }

    public MemberResponseDto findById(Long id) {
        System.out.println(STR + "findById");
        Member entity = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id=" + id));

        System.out.println(entity);
        return new MemberResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<MemberListResponseDto> findAll() {
        System.out.println(STR + "findAll");
        return memberRepository.findAll().stream()
                .map(MemberListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        System.out.println(STR + "delete");
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 없습니다. id=" + id));

        System.out.println(member);
        memberRepository.delete(member);
    }

    @Transactional(readOnly = true)
    public MemberRepository all() {
        return memberRepository;
    }
}
