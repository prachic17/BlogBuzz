package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> data;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElement;
    private Integer totalPages;
    private Boolean last;
}
