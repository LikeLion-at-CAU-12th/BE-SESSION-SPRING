package com.mutsa_cau12.springboot_session.controller;

import com.mutsa_cau12.springboot_session.domain.Article;
import com.mutsa_cau12.springboot_session.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/member/{memberId}")
    public List<Article> getArticlesByMemberId(@PathVariable("memberId") Long memberId) {
        return articleService.findArticlesByMemberId(memberId);
    }
}