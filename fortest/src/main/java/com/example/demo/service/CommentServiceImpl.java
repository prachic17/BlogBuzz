package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.exception.BlogAPIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Comments;
import com.example.demo.model.Posts;
import com.example.demo.repo.CommentRepository;
import com.example.demo.repo.PostsRepository;
import com.example.demo.sdpi.CommentsSdpi;
import org.aspectj.weaver.AjAttribute;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentsService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostsRepository postsRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto){
        Comments comment = new Comments();
         comment.setId(commentDto.getId());
         comment.setBody(commentDto.getBody());
         comment.setEmailId(commentDto.getEmailId());
         comment.setName(commentDto.getName());
         // retrieve post entity by id
        Posts post = postsRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", commentDto.getPostId()));
        // set post to comment entity
        comment.setPosts(post);
        // comment entity to DB
        Comments newComment =  commentRepository.save(comment);
        return mapToDTO(newComment);

    }


    private CommentDto mapToDTO(Comments comments){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comments.getId());
        commentDto.setBody(comments.getBody());
        commentDto.setEmailId(comments.getEmailId());
        commentDto.setName(comments.getName());
        commentDto.setPostId(comments.getPosts().getId());
        return commentDto;
    }

    @Override
    public List<CommentsSdpi> getCommentsByPostId(Long postId){
        List<CommentsSdpi> comments = commentRepository.findAll(postId);
        return comments;
    }
@Override
    public CommentDto getCommentById(Long id , Long postId){
    Posts post = postsRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", postId));
    Comments comment = commentRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Comment", "id", id));
    if(!comment.getPosts().getId().equals(post.getId())){
        throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
    }
    return mapToDTO(comment);

    }

    @Override
    public void deleteComment(Long id , Long postId){
        Posts post = postsRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comments comment = commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", id));

        if(!comment.getPosts().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);

    }
    @Override
    public  CommentDto updateComment( CommentDto commentDto){
        Posts post = postsRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", commentDto.getPostId()));

        Comments comment = commentRepository.findById(commentDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentDto.getId()));

        if(!comment.getPosts().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setId(comment.getId());
        comment.setPosts(post);
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmailId(commentDto.getEmailId());
        commentRepository.save(comment);
        return mapToDTO(comment);
    }






}