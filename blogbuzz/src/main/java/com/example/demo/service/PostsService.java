package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostResponse;
import com.example.demo.model.Posts;

import java.util.List;
import java.util.Optional;

public interface PostsService {
    PostDto createPosts(PostDto postDto);

    PostResponse getAllPosts(Integer pageNo , Integer pageSize,String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto , Long id);

    void deletePost(Long id);
}
