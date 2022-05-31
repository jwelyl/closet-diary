package com.quad.closetdiary.service;

import com.quad.closetdiary.controller.dto.picture.PictureListResponseDto;
import com.quad.closetdiary.controller.dto.picture.PictureResponseDto;
import com.quad.closetdiary.controller.dto.picture.PictureSaveRequestDto;
import com.quad.closetdiary.controller.dto.picture.PictureUpdateRequestDto;
import com.quad.closetdiary.domain.picture.Picture;
import com.quad.closetdiary.domain.picture.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Transactional
    public Long save(PictureSaveRequestDto requestDto) {
        return pictureRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PictureUpdateRequestDto requestDto) {
        Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다. id=" + id));
        picture.update(requestDto.getFileName(), requestDto.getFilePath());

        return id;
    }

    public PictureResponseDto findById(Long id) {
        Picture entity = pictureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다. id=" + id));

        return new PictureResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PictureListResponseDto> findAll() {
        return pictureRepository.findAll().stream().map(PictureListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Picture picture = pictureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사진이 없습니다. id=" + id));
        pictureRepository.delete(picture);
    }
}
