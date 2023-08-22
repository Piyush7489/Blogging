package com.durgesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.blog.entity.Category;
import com.durgesh.blog.entity.Post;
import com.durgesh.blog.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
}
