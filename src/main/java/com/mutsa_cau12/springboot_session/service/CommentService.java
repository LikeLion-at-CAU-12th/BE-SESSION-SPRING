package com.mutsa_cau12.springboot_session.service;

import com.mutsa_cau12.springboot_session.domain.Article;
import com.mutsa_cau12.springboot_session.domain.ArticleLog;
import com.mutsa_cau12.springboot_session.domain.Comment;
import com.mutsa_cau12.springboot_session.dto.request.CommentRequestDto;
import com.mutsa_cau12.springboot_session.dto.response.ArticleResponseDto;
import com.mutsa_cau12.springboot_session.dto.response.CommentResponseDto;
import com.mutsa_cau12.springboot_session.repository.ArticleJpaRepository;
import com.mutsa_cau12.springboot_session.repository.CommentJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentJpaRepository commentRepository;

    @Autowired
    private ArticleJpaRepository articleRepository;

    // 댓글 생성
    @Transactional
    public Long createComment(CommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId())
                .orElseThrow(() -> new RuntimeException("해당 ID의 게시글이 존재하지 않습니다."));

        Comment comment = Comment.builder()
                        .content(requestDto.getContent())
                                .article(article)
                                        .build();

        commentRepository.save(comment);
        return comment.getId(); // 생성된 댓글 ID 반환
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 댓글이 존재하지 않습니다."));

        comment.setContent(requestDto.getContent());
        commentRepository.save(comment);
    }

    // 게시글의 댓글 조회
    public List<CommentResponseDto> getCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(comment.getId(),comment.getContent()))
                .collect(Collectors.toList());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id); // 댓글 삭제
    }
}
