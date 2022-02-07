package com.sbmoviev2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "tbl_movie")
public class Movie extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    // 양방향 관계에서 주체가 되는 쪽을 설정하는 mappedBy.
    // 주체는 movie 이다.
    @Builder.Default
    // cascade 속성을 지정하여 movie 속성과 이하에서 저장하는 addPoster 의 poster 의 데이터를 함께 삽입한다.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Poster> posterList = new ArrayList<>();

    public void addPoster(Poster poster){
        poster.setIdx(this.posterList.size());
        poster.setMovie(this);
        posterList.add(poster);
    }

}
