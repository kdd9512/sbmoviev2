package com.sbmoviev2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    // orphanRemoval 속성 : 참조가 없는 하위 Entity 객체는 삭제할 것인가? T/F
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poster> posterList = new ArrayList<>();

    public void addPoster(Poster poster){
        poster.setIdx(this.posterList.size());
        poster.setMovie(this);
        posterList.add(poster);
    }


    public void removePoster(Long ino) {

        Optional<Poster> result = posterList.stream().filter(p -> p.getIno() == ino).findFirst();

        result.ifPresent(p -> {
            p.setMovie(null); // 하위 Entity 삭제.
            posterList.remove(p); // 해당 번호의 Poster 찾아서 삭제.
        });

        // Poster 중 하나를 삭제했으므로, 기존 Poster 객체의 idx 값을 변경.
        changeIdx();

    }

    private void changeIdx(){
        for (int i = 0; i < posterList.size(); i++) {
            posterList.get(i).setIdx(i);;
        }

    }

}
