package com.quad.closetdiary.domain.picture;

import com.quad.closetdiary.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //  기본 생성자 생성
@Entity
public class Picture extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String originFileName;

    @Column(length = 500, nullable = false)
    private String fileName;

    @Column(length = 500, nullable = false)
    private String filePath;

    @Column(nullable = false)
    private Integer fileSize;

    @Builder    //  빌더 패턴 클래스 생성
    public Picture(String originFileName, String fileName, String filePath, Integer fileSize) {
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void update(String fileName, String filePpath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
