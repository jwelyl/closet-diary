package com.quad.closetdiary.controller;

import com.quad.closetdiary.controller.dto.clothes.ClothesSaveRequestDto;
import com.quad.closetdiary.controller.dto.clothes.ClothesUpdateRequestDto;
import com.quad.closetdiary.domain.clothes.Clothes;
import com.quad.closetdiary.domain.clothes.ClothesRepository;
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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClothesApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClothesRepository clothesRepository;

    @AfterEach
    public void cleanup() throws Exception {
        clothesRepository.deleteAll();
    }

    @Test
    public void register_clothes() throws Exception {
        //given
        String name = "name";
        String category = "category";
        String brand = "brand";
        LocalDate purchaseDate = LocalDate.of(2022, 05, 17);
        Integer price = 123512;
        String originPicName = "originPicName";
        String picPath = "picPath";

        ClothesSaveRequestDto requestDto = ClothesSaveRequestDto.builder()
                .name(name)
                .category(category)
                .brand(brand)
                .purchaseDate(purchaseDate)
                .price(price)
                .originPicName(originPicName)
                .picPath(picPath)
                .build();

        String url = "http://localhost:" + port + "/api/v1/clothes";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Clothes> all = clothesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getCategory()).isEqualTo(category);
        assertThat(all.get(0).getBrand()).isEqualTo(brand);
        assertThat(all.get(0).getPurchaseDate()).isEqualTo(purchaseDate);
        assertThat(all.get(0).getPrice()).isEqualTo(price);
        assertThat(all.get(0).getOriginPicName()).isEqualTo(originPicName);
        assertThat(all.get(0).getPicPath()).isEqualTo(picPath);
    }

    @Test
    public void update_clothes() throws Exception {
        //given
        Clothes savedClothes = clothesRepository.save(Clothes.builder()
                .name("name0")
                .category("category0")
                .brand("brand0")
                .purchaseDate(LocalDate.of(2022, 05, 17))
                .price(123456)
                .originPicName("originPicName0")
                .picPath("picPath0")
                .build());

        Long updateId = savedClothes.getId();
        String expectedName = "name1";
        String expectedCategory = "category1";
        String expectedBrand = "brand1";
        LocalDate expectedPurchaseDate = LocalDate.of(2019, 05, 29);
        Integer expectedPrice = 654321;
        String expectedOriginPicName = "originPicName1";
        String expectedPicPath = "picPath1";

        ClothesUpdateRequestDto requestDto = ClothesUpdateRequestDto.builder()
                .name(expectedName)
                .category(expectedCategory)
                .brand(expectedBrand)
                .purchaseDate(expectedPurchaseDate)
                .price(expectedPrice)
                .originPicName(expectedOriginPicName)
                .picPath(expectedPicPath)
                .build();

        String url = "http://localhost:" + port + "/api/v1/clothes/" + updateId;

        HttpEntity<ClothesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Clothes> all = clothesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);
        assertThat(all.get(0).getCategory()).isEqualTo(expectedCategory);
        assertThat(all.get(0).getBrand()).isEqualTo(expectedBrand);
        assertThat(all.get(0).getPurchaseDate()).isEqualTo(expectedPurchaseDate);
        assertThat(all.get(0).getPrice()).isEqualTo(expectedPrice);
        assertThat(all.get(0).getOriginPicName()).isEqualTo(expectedOriginPicName);
        assertThat(all.get(0).getPicPath()).isEqualTo(expectedPicPath);
    }
}
