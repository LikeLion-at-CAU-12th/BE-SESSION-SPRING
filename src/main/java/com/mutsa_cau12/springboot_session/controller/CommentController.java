package com.mutsa_cau12.springboot_session.controller;

import com.mutsa_cau12.springboot_session.dto.request.CommentRequestDto;
import com.mutsa_cau12.springboot_session.dto.response.CommentResponseDto;
import com.mutsa_cau12.springboot_session.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<Long> createComment(@RequestBody CommentRequestDto requestDto) {
        Long commentId = commentService.createComment(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        commentService.updateComment(id, requestDto);
        return ResponseEntity.noContent().build();
    }

    // 게시글의 댓글 조회
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<CommentResponseDto> comments = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}