package com.blog.services;

import com.blog.dto.PostDto;
import com.blog.entities.Post;
import com.blog.mapper.PostMapper;
import com.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(PostMapper :: mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepo.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Post post = postRepo.findById(postId).get();
        return PostMapper.mapToPostDto(post);

    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepo.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepo.findByUrl(postUrl).get();
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepo.searchPosts(query);
        return posts.stream().map(PostMapper :: mapToPostDto).collect(Collectors.toList());
    }
}
