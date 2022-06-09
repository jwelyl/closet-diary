package com.quad.closetdiary.service;

import com.quad.closetdiary.controller.dto.clothes.ClothesListResponseDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesResponseDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesSaveRequestDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesUpdateRequestDto;
import com.quad.closetdiary.domain.clothes.Clothes;
import com.quad.closetdiary.domain.clothes.ClothesRepository;
import com.quad.closetdiary.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClothesService {
    private final ClothesRepository clothesRepository;

    @Transactional
    public Long save(ClothesSaveRequestDto requestDto) {
        return clothesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ClothesUpdateRequestDto requestDto) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 옷 게시물이 없습니다. id=" + id));
        clothes.update(requestDto.getName(), requestDto.getCategory(), requestDto.getBrand(), requestDto.getPurchaseDate(),
                requestDto.getPrice(), requestDto.getOriginPicName(), requestDto.getPicPath());

        return id;
    }

    public ClothesResponseDto findById(Long id) {
        Clothes entity = clothesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 옷 게시물이 없습니다. id=" + id));

        return new ClothesResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ClothesListResponseDto> findAll() {
        return clothesRepository.findAll().stream().map(ClothesListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Clothes clothes = clothesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        clothesRepository.delete(clothes);
    }

    public ClothesRepository getClothesRepository() {
        return clothesRepository;
    }
}
