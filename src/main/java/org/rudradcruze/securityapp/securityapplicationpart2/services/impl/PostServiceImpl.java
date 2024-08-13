package org.rudradcruze.securityapp.securityapplicationpart2.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.PostDto;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.PostEntity;
import org.rudradcruze.securityapp.securityapplicationpart2.entities.User;
import org.rudradcruze.securityapp.securityapplicationpart2.exceptions.ResourceNotFoundException;
import org.rudradcruze.securityapp.securityapplicationpart2.repositories.PostRepository;
import org.rudradcruze.securityapp.securityapplicationpart2.services.PostService;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        return modelMapper.map(
                postRepository.save(
                        modelMapper.map(postDto, PostEntity.class)
                ), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("User: {}", user);
        return postRepository
                .findById(id)
                .map(postEntity -> modelMapper
                        .map(postEntity, PostDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
    }

    @Override
    public List<PostDto> getPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        PostEntity oldPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));
        postDto.setId(id);
        modelMapper.map(postDto, oldPost);
        return modelMapper.map(postRepository.save(oldPost), PostDto.class);
    }

    @Override
    public void deletePost(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        postRepository.delete(post);
    }

    @Override
    public PostDto updatePartial(Long id, Map<String, Object> updates) {

        PostEntity post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + id));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(PostEntity.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, value);
        });
        return modelMapper.map(postRepository.save(post), PostDto.class);
    }
}
