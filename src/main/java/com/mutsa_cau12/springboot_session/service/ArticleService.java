package com.mutsa_cau12.springboot_session.service;

import com.mutsa_cau12.springboot_session.domain.Article;
import com.mutsa_cau12.springboot_session.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findArticlesByMemberId(Long memberId) {
        return articleRepository.findByMemberId(memberId);
    }
}
