package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.picture.PictureResponseDto;
import com.quad.closetdiary.controller.dto.picture.PictureSaveRequestDto;
import com.quad.closetdiary.controller.dto.picture.PictureUpdateRequestDto;
import com.quad.closetdiary.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PictureApiController {
    private final PictureService pictureService;

    @PostMapping("/api/v1/picture")
    public Long save(@RequestBody PictureSaveRequestDto requestDto) {
        return pictureService.save(requestDto);
    }

    @PutMapping("/api/v1/picture/{id}")
    public Long update(@PathVariable Long id, @RequestBody PictureUpdateRequestDto requestDto) {
        return pictureService.update(id, requestDto);
    }

    @GetMapping("/api/v1/picture/{id}")
    public PictureResponseDto findById(@PathVariable Long id) {
        return pictureService.findById(id);
    }

    @DeleteMapping("/api/v1/picture/{id}")
    public Long delete(@PathVariable Long id) {
        pictureService.delete(id);
        return id;
    }
}
