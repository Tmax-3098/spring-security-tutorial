package com.sketch.springSecurity.services;

import com.sketch.springSecurity.dto.PostDto;
import com.sketch.springSecurity.entities.PostEntity;
import com.sketch.springSecurity.exception.ResourceNotFoundException;
import com.sketch.springSecurity.repositories.PostRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final ModelMapper mapper;
    private final PostRepo postRepo;

    @Override
    public PostDto getPostById(Long id) {
        PostEntity post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("post does not exist for id " +id));
        return mapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        return postRepo.findAll().stream().map(post -> mapper.map(post, PostDto.class)).toList();
    }

    @Override
    public PostDto createPost(PostDto post) {
        return mapper.map(postRepo.save(mapper.map(post, PostEntity.class)), PostDto.class);
    }
}
