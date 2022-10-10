package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comments;
import com.example.demo.sdpi.CommentsSdpi;
import com.example.demo.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/comment"})
public class CommentController {
   @Autowired
   private CommentsService commentsService;
    @PostMapping("/create-comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentsService.createComment(commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-comments-by-postid")
    public List<CommentsSdpi> getCommentsByPostId(@RequestParam Long postId){
       List<CommentsSdpi> commentsList = new ArrayList<>();
       commentsList = commentsService.getCommentsByPostId(postId);
        return(commentsList);
    }

    @GetMapping("/get-comments-by-id")
    public CommentDto getCommentById(@RequestParam Long id , Long postId){
        CommentDto comments =   commentsService.getCommentById(id , postId);
        return comments;
    }

    @DeleteMapping({"/delete"})
    public ResponseEntity<String> deleteComment(@RequestParam Long id , Long postId){
        commentsService.deleteComment(id , postId);
        return new ResponseEntity<>("Comment has been deleted" , HttpStatus.OK);
    }

    @PutMapping({"/update"})
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto){
        CommentDto newComment =   commentsService.updateComment(commentDto);
        return  new ResponseEntity<>(newComment , HttpStatus.OK );
    }



}
