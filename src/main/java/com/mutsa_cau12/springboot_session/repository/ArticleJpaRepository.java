package com.mutsa_cau12.springboot_session.repository;

import com.mutsa_cau12.springboot_session.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleJpaRepository extends JpaRepository<Article, Long> {
    List<Article> findByMemberId(Long memberId);
}
