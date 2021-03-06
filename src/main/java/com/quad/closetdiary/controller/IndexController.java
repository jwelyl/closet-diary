package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.clothes.ClothesResponseDto;
import com.quad.closetdiary.controller.dto.member.MemberResponseDto;
import com.quad.closetdiary.controller.dto.picture.PictureResponseDto;
import com.quad.closetdiary.service.ClothesService;
import com.quad.closetdiary.service.MemberService;
import com.quad.closetdiary.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PictureService pictureService;
    private final ClothesService clothesService;
    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("picture", pictureService.findAll());
        model.addAttribute("clothes", clothesService.findAll());
        model.addAttribute("member", memberService.findAll());

        return "index";
    }

    @GetMapping("/picture/save")
    public String pictureSave() { return "picture-save";}

    @GetMapping("/picture/update/{id}")
    public String pictureUpdate(@PathVariable Long id, Model model) {
        PictureResponseDto dto = pictureService.findById(id);
        model.addAttribute("picture", dto);

        return "picture-update";
    }

    @GetMapping("/clothes/save")
    public String clothesSave() { return "clothes-save";}

    @GetMapping("/clothes/update/{id}")
    public String clothesUpdate(@PathVariable Long id, Model model) {
        ClothesResponseDto dto = clothesService.findById(id);
        model.addAttribute("clothes", dto);

        return "clothes-update";
    }

    @GetMapping("/member/save")
    public String memberSave() {return "member-save";}

    @GetMapping("/member/update/{id}")
    public String memberUpdate(@PathVariable Long id, Model model) {
        MemberResponseDto dto = memberService.findById(id);
        model.addAttribute("member", dto);

        return "member-update";
    }
}
