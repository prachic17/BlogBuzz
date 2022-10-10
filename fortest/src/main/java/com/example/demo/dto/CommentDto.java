package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Comment should not be empty")
    private String body;

    @Email(message = "Email is not valid")
    private String emailId;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private Long postId;

}
