package com.quad.closetdiary.controller.dto.picture;

import com.quad.closetdiary.domain.picture.Picture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PictureListResponseDto {
    private Long id;
    private String originFileName;
    private String fileName;
    private String filePath;
    private Integer fileSize;
//    private LocalDateTime modifiedDate;

    public PictureListResponseDto(Picture entity) {
        this.id = entity.getId();
        this.originFileName = entity.getOriginFileName();
        this.fileName = entity.getFileName();
        this.filePath = entity.getFilePath();
        this.fileSize = entity.getFileSize();
//        this.modifiedDate = entity.getModifiedDate();
    }
}
