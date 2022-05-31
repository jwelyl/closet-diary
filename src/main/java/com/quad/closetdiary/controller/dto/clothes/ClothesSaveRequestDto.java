package com.quad.closetdiary.controller.dto.clothes;

import com.quad.closetdiary.domain.clothes.Clothes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ClothesSaveRequestDto {
    private String name;
    private String category;
    private String brand;
    private LocalDate purchaseDate;
    private Integer price;
    private String originPicName;
    private String picPath;

    @Builder
    public ClothesSaveRequestDto(String name, String category, String brand, LocalDate purchaseDate,
                                 Integer price, String originPicName, String picPath) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.originPicName = originPicName;
        this.picPath = picPath;
    }

    public Clothes toEntity() {
        return Clothes.builder()
                .name(name)
                .category(category)
                .brand(brand)
                .purchaseDate(purchaseDate)
                .price(price)
                .originPicName(originPicName)
                .picPath(picPath)
                .build();
    }
}
