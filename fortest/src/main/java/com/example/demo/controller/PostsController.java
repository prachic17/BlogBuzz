package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostResponse;
import com.example.demo.model.Posts;
import com.example.demo.service.PostsService;
import com.example.demo.utils.AppConstants;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/api/posts"})
public class PostsController {

    @Autowired
    private PostsService postsService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/create"})
    public ResponseEntity<PostDto> createPosts(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postsService.createPosts(postDto), HttpStatus.CREATED);

    }

    @GetMapping({"/get-all-posts"})
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        return postsService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }


    @GetMapping({"get-post-by-id"})
    public ResponseEntity<PostDto> getPostById(@RequestParam long id) {
        return ResponseEntity.ok(postsService.getPostById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/update"})
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @RequestParam Long id) {

        return new ResponseEntity<>(postsService.updatePost(postDto, id), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"delete-post-by-id"})
    public ResponseEntity<String> deletePost(@RequestParam Long id) {
        postsService.deletePost(id);
        return new ResponseEntity<>("Post has been deleted", HttpStatus.OK);

    }

}
