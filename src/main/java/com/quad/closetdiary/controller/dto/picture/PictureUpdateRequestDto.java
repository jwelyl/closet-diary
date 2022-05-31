package com.quad.closetdiary.controller.dto.picture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureUpdateRequestDto {
    private String fileName;
    private String filePath;

    @Builder
    public PictureUpdateRequestDto(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
