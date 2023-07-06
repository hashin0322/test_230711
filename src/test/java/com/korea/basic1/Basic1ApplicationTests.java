package com.korea.basic1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import com.korea.basic1.comment.Comment;
import com.korea.basic1.comment.CommentRepository;
import com.korea.basic1.post.Post;
import com.korea.basic1.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	void testJpa() {
		Post p1 = new Post();
		p1.setSubject("첫번째 게시물");
		p1.setContent("안녕하세요~");
		p1.setCreateDate(LocalDateTime.now());
		this.postRepository.save(p1);	// 첫번째 게시물 저장

		Post p2 = new Post();
		p2.setSubject("두번째 게시물");
		p2.setContent("안녕하신가요?");
		p2.setCreateDate(LocalDateTime.now());
		this.postRepository.save(p2);	// 두번째 게시물 저장
	}
}