package com.mutsa_cau12.springboot_session.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryArticle {
    @Id @GeneratedValue
    @Column(name = "category_article_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public CategoryArticle(Category category, Article article) {
        this.category = category;
        this.article = article;
    }
}
