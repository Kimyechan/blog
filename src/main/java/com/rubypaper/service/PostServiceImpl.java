package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.post.Post;
import com.rubypaper.repository.CategoryRepository;
import com.rubypaper.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    // 게시글 목록
    @Override
    public List<Post> getpostList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        //pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC,"id");
                //new Sort(Sort.Direction.DESC, "id")); // Sort 추가

        List<Post> postList = (List<Post>) postRepository.findAll(pageable);
        List<Post> getPostList = new ArrayList<>();


        // int totalPostCnt // 게시글 수

        for(Post post : postList) {
            Post postBuilder = Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
//                    .commentList(post.getCommentList())
                    .regDate(post.getRegDate())
                    .build();
            getPostList.add(postBuilder);
        }
        return getPostList;
    }

    // 게시글 상세보기
    @Override
    public Post readPost(Long id){
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = postOptional.get();

        Post postBuilder = Post.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .commentList(post.getCommentList())
                .blog(post.getBlog())
                .regDate(post.getRegDate())
                .build();

        return postBuilder;
    }

    @Override
    public Post savePost(Post post, Long categoryId) {
        Optional<Category> categoryTemp = categoryRepository.findWithBlogByCategoryId(categoryId);
        Category category = categoryTemp.get();
        Blog blog = category.getBlog();

        if (categoryTemp.isEmpty()) {
            return null;
        }

        post.setRegDate(LocalDateTime.now());

        Post addPost = Post.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .regDate(post.getRegDate())
                .category(category)
                .blog(blog)
                .build();

        return postRepository.save(addPost);
    }

    @Override
    public Post updatePost(Post post, Long categoryId) {
        Optional<Post> oldPost = postRepository.findById(post.getId());
        Post newPost = oldPost.get();

        Optional<Category> categoryTemp = categoryRepository.findWithBlogByCategoryId(categoryId);
        Category category = categoryTemp.get();
        Blog blog = category.getBlog();


        if (categoryTemp.isEmpty()) {
            return null;
        }

        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setCategory(category);
        return postRepository.save(newPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
        /*Post oldPost = postRepository.findById(id);
        // 게시글 수정에 따른 카테고리 내 게시글 카운트
        // Category category = oldPost.getCategory();
        // categoryRepository.updatePostCnt(category.getId(), category.getPostCnt() -1);

        if(oldPost == null){
            try {
                throw new NotFoundException(id + " not found");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }*/
    }

}
