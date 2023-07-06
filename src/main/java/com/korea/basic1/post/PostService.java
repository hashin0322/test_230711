package com.korea.basic1.post;

import java.util.List;
import java.util.Optional;


import com.korea.basic1.DataNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    public Post getPost(Integer id){
        Optional<Post> post = this.postRepository.findById(id);
        if( post.isPresent()){  // 해당 데이터가 존재하는지 검사
            return post.get();
        }
        else {
             throw new DataNotFoundException("post not found");
        }
    }
}