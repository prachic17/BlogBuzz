package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comments;
import com.example.demo.sdpi.CommentsSdpi;

import java.util.List;
import java.util.Optional;

public interface CommentsService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentsSdpi> getCommentsByPostId(Long postId);

    CommentDto getCommentById(Long id , Long postId);

    void deleteComment(Long id , Long postId);

    CommentDto updateComment(CommentDto commentDto);

}
