package com.mutsa_cau12.springboot_session.service;

import com.mutsa_cau12.springboot_session.domain.*;
import com.mutsa_cau12.springboot_session.dto.request.ArticleCreateRequestDto;
import com.mutsa_cau12.springboot_session.dto.request.ArticleUpdateRequestDto;
import com.mutsa_cau12.springboot_session.dto.response.ArticleResponseDto;
import com.mutsa_cau12.springboot_session.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private MemberJpaRepository memberRepository;
    @Autowired
    private ArticleJpaRepository articleRepository;
    @Autowired
    private CategoryArticleJpaRepository categoryArticleRepository;
    @Autowired
    private ArticleLogJpaRepository articleLogRepository;
    @Autowired
    private CategoryJpaRepository categoryRepository;

    @Transactional
    public Long createArticle(ArticleCreateRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 아이디를 가진 회원이 존재하지 않습니다."));
        Article article = Article.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .member(member)
                .comments(new ArrayList<>())
                .build();
        articleRepository.save(article);

        ArticleLog articleLog = ArticleLog.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .article(article)
                .build();
        articleLogRepository.save(articleLog);


        List<Long> categoryIds = requestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (Long categoryId : categoryIds) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("해당 ID를 가진 카테고리가 존재하지 않습니다."));

                CategoryArticle categoryArticle = CategoryArticle.builder()
                        .category(category)
                        .article(article)
                        .build();

                categoryArticleRepository.save(categoryArticle);
            }
        }
        return article.getId();
    }

    public List<ArticleResponseDto> findArticlesByMemberId(Long memberId) {
        List<Article> articles = articleRepository.findByMemberId(memberId);
        return articles.stream()
                .map(article -> new ArticleResponseDto(article.getId(), article.getTitle(), article.getContent()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void deleteArticle(Long id) {
        System.out.println(id);
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id를 가진 게시글이 없습니다."));
        articleRepository.delete(article);
    }
    @Transactional
    public void updateArticle(Long id, ArticleUpdateRequestDto requestDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 id를 가진 게시글이 없습니다."));

//        ArticleLog articleLog = ArticleLog.builder()
//                .title(requestDto.getTitle())
//                .content(requestDto.getContent())
//                .article(article)
//                .build();
//        articleLogRepository.save(articleLog);
        // ArticleLog 수정
        ArticleLog articleLog = articleLogRepository.findByArticle(article)
                .orElseThrow(() -> new RuntimeException("해당 게시글에 대한 로그가 존재하지 않습니다."));

        articleLog.setTitle(requestDto.getTitle());
        articleLog.setContent(requestDto.getContent());
        articleLogRepository.save(articleLog); // 수정된 로그 저장

        // 게시글 수정
        article.setTitle(requestDto.getTitle());
        article.setContent(requestDto.getContent());

        // 기존 카테고리 연결 제거
//        List<CategoryArticle> existingCategories = categoryArticleRepository.findByArticle(article);
//        for (CategoryArticle categoryArticle : existingCategories) {
//            categoryArticleRepository.delete(categoryArticle);
//        }
        List<CategoryArticle> existingCategories = categoryArticleRepository.findByArticle(article);
        categoryArticleRepository.deleteAll(existingCategories); // 삭제 메소드를 한번에 호출하여 성능 개선


        List<Long> categoryIds = requestDto.getCategoryIds();
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (Long categoryId : categoryIds) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("해당 ID를 가진 카테고리가 존재하지 않습니다."));

                CategoryArticle categoryArticle = CategoryArticle.builder()
                        .category(category)
                        .article(article)
                        .build();

                categoryArticleRepository.save(categoryArticle);
            }
        }

        articleRepository.save(article); // 수정된 게시글 저장
    }

}
