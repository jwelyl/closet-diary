package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.clothes.ClothesResponseDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesSaveRequestDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesUpdateRequestDto;
import com.quad.closetdiary.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ClothesApiController {
    private final ClothesService clothesService;

    //  Clothes POST API (등록, register)
    @PostMapping("/api/v1/clothes")
    public Long save(@RequestBody ClothesSaveRequestDto requestDto) {
        return clothesService.save(requestDto);
    }

    //  Clothes PUT API (수정, update)
    @PutMapping("/api/v1/clothes/{id}")
    public Long update(@PathVariable Long id, @RequestBody ClothesUpdateRequestDto requestDto) {
        return clothesService.update(id, requestDto);
    }

    //  Clothes GET API (조회, get)
    @GetMapping("/api/v1/clothes/{id}")
    public ClothesResponseDto findById(@PathVariable Long id) {
        return clothesService.findById(id);
    }

    //  Clothes DELETE API (삭제, delete)
    @DeleteMapping("/api/v1/clothes/{id}")
    public Long delete(@PathVariable Long id) {
        clothesService.delete(id);
        return id;
    }
}
