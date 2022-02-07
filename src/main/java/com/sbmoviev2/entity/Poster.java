package com.sbmoviev2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie") // 자동으로 override 되는 부분을 제외. 이걸 제외안하면 서로 연관관계에 있는 컬럼을 계속 불러와 무한루프가 발생한다.
@Table(name = "tbl_poster")
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String fname;

    private int idx; // poster 순서번호

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


}
