package com.quad.closetdiary.domain.clothes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClothesRepositoryTest {
    @Autowired
    ClothesRepository repository;

    @AfterEach
    public void cleanup() {
        repository.deleteAll();
    }

    @Test
    public void get_clothes() {
        //given
        String name0 = "clothes0";
        String category0 = "category0";
        String brand0 = "brand0";
        LocalDate purchaseDate0 = LocalDate.of(2022, 5,17);
        Integer price0 = 103450;
        String originPicName0 = "originPicName0";
        String picPath0 = "path0";

        String name1 = "clothes1";
        String category1 = "category1";
        String brand1 = "brand1";
        LocalDate purchaseDate1 = LocalDate.of(2037, 9,29);
        Integer price1 = 423560;
        String originPicName1 = "originPicName1";
        String picPath1 = "path1";

        repository.save(Clothes.builder()
                .name(name0)
                .category(category0)
                .brand(brand0)
                .purchaseDate(purchaseDate0)
                .price(price0)
                .originPicName(originPicName0)
                .picPath(picPath0)
                .build());
        repository.save(Clothes.builder()
                .name(name1)
                .category(category1)
                .brand(brand1)
                .purchaseDate(purchaseDate1)
                .price(price1)
                .originPicName(originPicName1)
                .picPath(picPath1)
                .build());

        //when
        List<Clothes> clothesList = repository.findAll();

        //then
        Clothes clothes0 = clothesList.get(0);
        Clothes clothes1 = clothesList.get(1);

        assertThat(clothes0.getName()).isEqualTo(name0);
        assertThat(clothes0.getCategory()).isEqualTo(category0);
        assertThat(clothes0.getBrand()).isEqualTo(brand0);
        assertThat(clothes0.getPurchaseDate()).isEqualTo(purchaseDate0);
        assertThat(clothes0.getPrice()).isEqualTo(price0);
        assertThat(clothes0.getOriginPicName()).isEqualTo(originPicName0);
        assertThat(clothes0.getPicPath()).isEqualTo(picPath0);

        assertThat(clothes1.getName()).isEqualTo(name1);
        assertThat(clothes1.getCategory()).isEqualTo(category1);
        assertThat(clothes1.getBrand()).isEqualTo(brand1);
        assertThat(clothes1.getPurchaseDate()).isEqualTo(purchaseDate1);
        assertThat(clothes1.getPrice()).isEqualTo(price1);
        assertThat(clothes1.getOriginPicName()).isEqualTo(originPicName1);
        assertThat(clothes1.getPicPath()).isEqualTo(picPath1);
    }
}
