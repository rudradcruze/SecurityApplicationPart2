package org.rudradcruze.securityapp.securityapplicationpart2.controllers;

import lombok.RequiredArgsConstructor;
import org.rudradcruze.securityapp.securityapplicationpart2.dto.PostDto;
import org.rudradcruze.securityapp.securityapplicationpart2.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostDto> updatePartialPost(
            @PathVariable Long postId,
            @RequestBody Map<String, Object> update) {
        return ResponseEntity.ok(postService.updatePartial(postId, update));
    }
}
