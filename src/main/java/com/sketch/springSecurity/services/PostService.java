package com.sketch.springSecurity.services;

import com.sketch.springSecurity.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto getPostById(Long id);

    List<PostDto> getAllPost();

    PostDto createPost(PostDto post);
}
