package com.korea.basic1.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.korea.basic1.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


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

    public void create(String subject, List<MultipartFile> files, String content){
        Post p = new Post();
        p.setSubject(subject);
        p.setContent(content);
        p.setCreateDate(LocalDateTime.now());

        this.postRepository.save(p);
    }











    private final FileHandler fileHandler;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.fileHandler = new FileHandler();
    }

    public Post addPost(
            Post post,
            List<MultipartFile> files
    ) throws Exception {
        // 파일을 저장하고 그 Post 에 대한 list 를 가지고 있는다
        List<Post> list = fileHandler.parseFileInfo(post.getId(), files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<Post> pictureBeans = new ArrayList<>();
            for (Post posts : list) {
                pictureBeans.add(postRepository.save(posts));
            }
        }

        return postRepository.save(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPost(int id) {
        return postRepository.findById(id);
    }

}