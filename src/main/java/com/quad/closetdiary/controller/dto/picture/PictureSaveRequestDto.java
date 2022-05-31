package com.quad.closetdiary.controller.dto.picture;

import com.quad.closetdiary.domain.picture.Picture;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureSaveRequestDto {
    private String originFileName;
    private String fileName;
    private String filePath;
    private Integer fileSize;

    @Builder
    public PictureSaveRequestDto(String originFileName, String fileName, String filePath, Integer fileSize) {
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public Picture toEntity() {
        return Picture.builder()
                .originFileName(originFileName)
                .fileName(fileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();
    }
}
