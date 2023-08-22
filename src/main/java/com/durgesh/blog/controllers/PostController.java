package com.durgesh.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.blog.config.AppConstants;
import com.durgesh.blog.payloads.APIResponse;
import com.durgesh.blog.payloads.PostDTO;
import com.durgesh.blog.payloads.PostResponse;
import com.durgesh.blog.services.FileService;
import com.durgesh.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService ps;
	
	@Autowired
	private FileService fs;
	
	@Value("${project.image}")
	private String path;
	
//	CREATE
	@PostMapping("/user/{userId}/category/{catId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDto, @PathVariable Integer userId,
			@PathVariable Integer catId) {
		PostDTO post = this.ps.createPost(postDto, userId, catId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.CREATED);
	}

//	GET POST BY POST ID
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		PostDTO post = this.ps.getPostById(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}

//	GET ALL POSTS
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam (value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir) {

		PostResponse allPosts = this.ps.getAllPosts(pageNo, pageSize,sortBy,sortDir);

		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}

//	GET BY CATEGORY
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByCategoryID(@PathVariable Integer catId) {
		List<PostDTO> posts = this.ps.getPostByCategory(catId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

//	GET BY USER
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostByUserID(@PathVariable Integer userId) {
		List<PostDTO> posts = this.ps.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}

//	DELETE POST BY ID
	@DeleteMapping("/posts/{postId}")
	public APIResponse deletePostById(@PathVariable Integer postId) {
		this.ps.deletePost(postId);
		return new APIResponse("Post is successfully Deleted !!", true);
	}

//	UPDATE POST 
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable Integer postId, @RequestBody PostDTO postDto) {
		PostDTO updatePost = this.ps.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
	
//	SEARCH
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable String keywords){
		List<PostDTO> searchPosts = this.ps.searchPosts(keywords);
		return new ResponseEntity<List<PostDTO>>(searchPosts,HttpStatus.OK);
	}
	
//	POST IMAGE UPLOAD
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam ("image") MultipartFile image,@PathVariable Integer postId) throws IOException
	{
		String fileName = this.fs.uploadImage(path, image);
		PostDTO postDto = this.ps.getPostById(postId);
		postDto.setImageName(fileName);
		PostDTO updatePost = this.ps.updatePost(postDto, postId);
		return new ResponseEntity<PostDTO> (updatePost,HttpStatus.OK);
	}

}
