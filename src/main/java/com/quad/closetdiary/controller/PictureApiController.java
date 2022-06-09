package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.picture.PictureResponseDto;
import com.quad.closetdiary.controller.dto.picture.PictureSaveRequestDto;
import com.quad.closetdiary.controller.dto.picture.PictureUpdateRequestDto;
import com.quad.closetdiary.domain.clothes.Clothes;
import com.quad.closetdiary.domain.picture.Picture;
import com.quad.closetdiary.service.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    /* 추가 API */
    @PostMapping("/picture/insert")  //  insert one
    public Picture insert(@RequestBody Map<String, String> map) {
        Picture p = Picture.builder()
                .originFileName(map.get("originFileName"))
                .fileName(map.get("fileName"))
                .filePath(map.get("filePath"))
                .fileSize(stringToInt(map.get("fileSize")))
                .build();

        return pictureService.getPictureRepository().save(p);
    }

    @GetMapping("/picture/select")   //  select all
    public List<Picture> selectAll() {
        return pictureService.getPictureRepository().findAll();
    }

    @GetMapping("/picture/select/{id}")  //  select one
    public Picture selectOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Picture p = pictureService.getPictureRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 picture가 없습니다. id=" + id));
        return p;
    }

    @PutMapping("/picture/modify/{id}")  //  update
    public Picture updateOne(@PathVariable("id") Long id, @RequestBody Map<String, String> map) {
        Picture p = pictureService.getPictureRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 picture가 없습니다. id=" + id));

        p.update(map.get("fileName"), map.get("filePath"));
        return pictureService.getPictureRepository().save(p);
    }

    @DeleteMapping("/picture/remove/{id}")   //  delete
    public Long deleteOne(@PathVariable("id") Long id) {
        pictureService.getPictureRepository().deleteById(id);
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
