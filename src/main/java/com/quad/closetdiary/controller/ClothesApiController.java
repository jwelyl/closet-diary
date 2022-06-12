package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.clothes.ClothesResponseDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesSaveRequestDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesUpdateRequestDto;
import com.quad.closetdiary.domain.clothes.Clothes;
import com.quad.closetdiary.domain.member.Member;
import com.quad.closetdiary.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /* 추가 API */
    @PostMapping("/clothes/insert")  //  insert one
    public Clothes insert(@RequestBody Map<String, String> map) {
        Clothes c = Clothes.builder()
                .name(map.get("name"))
                .category(map.get("category"))
                .brand(map.get("brand"))
                .purchaseDate(stringToLocalDate(map.get("purchaseDate")))
                .price(stringToInt(map.get("price")))
                .originPicName(map.get("originPicName"))
                .picPath(map.get("picPath"))
                .build();

        return clothesService.getClothesRepository().save(c);
    }

    @GetMapping("/clothes/select")   //  select all
    public List<Clothes> selectAll() {
        return clothesService.getClothesRepository().findAll();
    }

    @GetMapping("/clothes/select/top")
    public List<Clothes> selectTop() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "상의")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/bottom")
    public List<Clothes> selectBottom() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "하의")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/coat")
    public List<Clothes> selectCoat() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "외투")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/shoes")
    public List<Clothes> selectShoes() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "신발")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/bag")
    public List<Clothes> selectBag() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "가방")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/accessory")
    public List<Clothes> selectAccesory() {
        return clothesService.getClothesRepository()
                .findAll().stream().filter(a -> a.getCategory() == "악세서리")
                .collect(Collectors.toList());
    }

    @GetMapping("/clothes/select/{id}")  //  select one
    public Clothes selectOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Clothes c = clothesService.getClothesRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 clothes가 없습니다. id=" + id));
        return c;
    }

    @PutMapping("/clothes/modify/{id}")  //  update
    public Clothes updateOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Clothes c = clothesService.getClothesRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 clothes가 없습니다. id=" + id));

        c.update(map.get("name"), map.get("category"), map.get("brand"), stringToLocalDate(map.get("purchaseDate")), stringToInt(map.get("price")), map.get("originPicName"), map.get("picPath"));
        return clothesService.getClothesRepository().save(c);
    }

    @DeleteMapping("/clothes/remove/{id}")   //  delete
    public Long deleteOne(@PathVariable("id") Long id) {
        clothesService.getClothesRepository().deleteById(id);
        return id;
    }

    private LocalDate stringToLocalDate(String localDate) {
        try {
            return LocalDate.parse(localDate);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
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
