package com.mutsa_cau12.springboot_session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}
