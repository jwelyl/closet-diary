package com.quad.closetdiary.domain.picture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PictureRepositoryTest {
    @Autowired
    PictureRepository repository;

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void get_picture() {
        //given
        String originFileName0 = "originFileName0";
        String fileName0 = "fileName0";
        String filePath0 = "filePath0";
        Integer fileSize0 = 1024;

        repository.save(Picture.builder()
                .originFileName(originFileName0)
                .fileName(fileName0)
                .filePath(filePath0)
                .fileSize(fileSize0)
                .build());

        //when
        List<Picture> pictureList = repository.findAll();

        //then
        Picture picture = pictureList.get(0);
        assertThat(picture.getOriginFileName()).isEqualTo(originFileName0);
        assertThat(picture.getFileName()).isEqualTo(fileName0);
        assertThat(picture.getFilePath()).isEqualTo(filePath0);
        assertThat(picture.getFileSize()).isEqualTo(fileSize0);
    }
}
