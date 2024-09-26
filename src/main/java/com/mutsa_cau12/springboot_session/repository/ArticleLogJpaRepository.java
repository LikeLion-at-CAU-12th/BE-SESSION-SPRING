package com.mutsa_cau12.springboot_session.repository;

import com.mutsa_cau12.springboot_session.domain.Article;
import com.mutsa_cau12.springboot_session.domain.ArticleLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleLogJpaRepository extends JpaRepository<ArticleLog,Long> {
    Optional<ArticleLog> findByArticle(Article article);
}
