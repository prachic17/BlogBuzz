package com.example.demo.repo;

import com.example.demo.model.Comments;
import com.example.demo.sdpi.CommentsSdpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    @Query(nativeQuery = true, value = "select ct.id as id, ct.name as name , ct.body as body , ct.emailid as emailId , ct.post_id as postId from comments ct where ct.post_id=?1")
    List<CommentsSdpi> findAll(Long postId);


}
