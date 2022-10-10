package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Posts;
import com.example.demo.repo.PostsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPosts(PostDto postDto){
        Posts posts = new Posts();
        posts.setId(postDto.getId());
        posts.setTitle(postDto.getTitle());
        posts.setContent(postDto.getContent());
        posts.setDescription(postDto.getDescription());
        Posts newPost =   postsRepository.save(posts);
        PostDto postResponse =new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setContent(newPost.getContent());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setTitle(newPost.getTitle());
        return  postResponse;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNo , Integer pageSize, String sortBy , String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pg = PageRequest.of(pageNo , pageSize , sort);
        Page<Posts> posts = postsRepository.findAll(pg);
        List<Posts> postsList = posts.getContent();
        List<PostDto> data =    postsList.stream().map(this::mapToDTO).collect(Collectors.toList());
      PostResponse postResponse = new PostResponse();
      postResponse.setData(data);
      postResponse.setPageNo(posts.getNumber());
      postResponse.setPageSize(posts.getSize());
      postResponse.setTotalElement(posts.getTotalElements());
      postResponse.setLast(posts.isLast());
      postResponse.setTotalPages(posts.getTotalPages());
      return  postResponse;

    }

    @Override
    public PostDto getPostById(Long id){
        Posts post = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto , Long id){
        Posts posts = new Posts();
        posts.setId(id);
        posts.setTitle(postDto.getTitle());
        posts.setContent(postDto.getContent());
        posts.setDescription(postDto.getDescription());
        Posts newPost =   postsRepository.save(posts);
        PostDto postResponse =new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setContent(newPost.getContent());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setTitle(newPost.getTitle());
        return  postResponse;
    }
    @Override
    public void deletePost(Long id){
        Posts post = postsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postsRepository.delete(post);
    }
    private PostDto mapToDTO(Posts post){
        PostDto postDto = modelMapper.map(post , PostDto.class);
        //     PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
      return postDto;
    }



}
