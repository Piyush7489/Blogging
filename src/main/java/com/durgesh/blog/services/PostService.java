package com.durgesh.blog.services;

import java.util.List;

import com.durgesh.blog.payloads.PostDTO;
import com.durgesh.blog.payloads.PostResponse;

public interface PostService {
//	CREATE
	PostDTO createPost(PostDTO postDto,Integer userId , Integer catId);

//	UPDATE
	PostDTO updatePost(PostDTO postDto, Integer postId);

//	DELETE
	void deletePost(Integer postId);

//	GET ALL POST
	PostResponse getAllPosts(Integer pageNo , Integer pageSize ,String sortBy,String sortDir);

//	GET SINGLE POST
	PostDTO getPostById(Integer postId);

//	GET ALL POST BY CATEGORY
	List<PostDTO>  getPostByCategory(Integer catId );

//	GET ALL POST BY USER
	List<PostDTO> getPostByUser(Integer userId);

//	SEARCH POST
	List<PostDTO> searchPosts(String keyWord);
}
