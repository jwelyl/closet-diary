package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.picture.PictureSaveRequestDto;
import com.quad.closetdiary.controller.dto.picture.PictureUpdateRequestDto;
import com.quad.closetdiary.domain.picture.Picture;
import com.quad.closetdiary.domain.picture.PictureRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PictureApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PictureRepository pictureRepository;

    @AfterEach
    public void cleanup() throws Exception {
        pictureRepository.deleteAll();
    }
    @Test
    public void register_picture() throws Exception {
        //given
        String originFileName = "originFileName";
        String fileName = "fileName";
        String filePath = "filePath";
        Integer fileSize = 1024;

        PictureSaveRequestDto requestDto = PictureSaveRequestDto.builder()
                .originFileName(originFileName)
                .fileName(fileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();

        String url = "http://localhost:" + port + "/api/v1/picture";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Picture> all = pictureRepository.findAll();
        assertThat(all.get(0).getOriginFileName()).isEqualTo(originFileName);
        assertThat(all.get(0).getFileName()).isEqualTo(fileName);
        assertThat(all.get(0).getFilePath()).isEqualTo(filePath);
        assertThat(all.get(0).getFileSize()).isEqualTo(fileSize);
    }

    @Test
    public void update_picture() throws Exception {
        //given
        Picture savedPicture = pictureRepository.save(Picture.builder()
                .originFileName("originFileName0")
                .fileName("fileName0")
                .filePath("filePath0")
                .fileSize(1024)
                .build());

        Long updateId = savedPicture.getId();
        String expectedOriginFileName = "originFileName0";
        String expectedFileName = "fileName1";
        String expectedFilePath = "filePath1";
        Integer expectedFileSize = 1024;

        PictureUpdateRequestDto requestDto = PictureUpdateRequestDto.builder()
                .fileName(expectedFileName)
                .filePath(expectedFilePath)
                .build();

        String url = "http://localhost:" + port + "/api/v1/picture/" + updateId;

        HttpEntity<PictureUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Picture> all = pictureRepository.findAll();
        assertThat(all.get(0).getOriginFileName()).isEqualTo(expectedOriginFileName);
        assertThat(all.get(0).getFileName()).isEqualTo(expectedFileName);
        assertThat(all.get(0).getFilePath()).isEqualTo(expectedFilePath);
        assertThat(all.get(0).getFileSize()).isEqualTo(expectedFileSize);
    }
}
