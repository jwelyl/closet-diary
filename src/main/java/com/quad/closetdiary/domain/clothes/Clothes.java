package com.quad.closetdiary.domain.clothes;


import com.quad.closetdiary.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor  //  기본 생성자 생성
@Entity             //  Database Table (Clothes -> clothes table)
public class Clothes extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //  Primary Key

    @Column(length = 100, nullable = false) //  DB column
    private String name;    //  옷 이름

    @Column(length = 100, nullable = false)
    private String category;    //  옷 종류

    @Column(length = 100, nullable = false)
    private String brand;       //  옷 브랜드

    private LocalDate purchaseDate;    //  옷 구매 일자

    private Integer price;  //  옷 가격

    @Column(length = 100, nullable = false)
    private String originPicName;

    @Column(length = 100, nullable = false)
    private String picPath;

    @Builder
    public Clothes(String name, String category, String brand, LocalDate purchaseDate, Integer price, String originPicName, String picPath) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.originPicName = originPicName;
        this.picPath = picPath;
    }

    public void update(String name, String category, String brand, LocalDate purchaseDate, Integer price, String originPicName, String picPath) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.originPicName = originPicName;
        this.picPath = picPath;
    }
}
