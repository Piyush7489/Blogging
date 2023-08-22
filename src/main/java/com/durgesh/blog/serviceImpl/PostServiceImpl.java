package com.durgesh.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.durgesh.blog.entity.Category;
import com.durgesh.blog.entity.Post;
import com.durgesh.blog.entity.User;
import com.durgesh.blog.exceptions.ResourceNotFoundException;
import com.durgesh.blog.payloads.PostDTO;
import com.durgesh.blog.payloads.PostResponse;
import com.durgesh.blog.repository.CategoryRepo;
import com.durgesh.blog.repository.PostRepo;
import com.durgesh.blog.repository.UserRepo;
import com.durgesh.blog.services.PostService;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo prepo;
	@Autowired
	private UserRepo urepo;
	@Autowired
	private CategoryRepo crepo;
	@Autowired
	private ModelMapper mapper;

//	CREATE POST
	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer catId) {

		User user = this.urepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.crepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", catId));

		Post post = this.mapper.map(postDto, Post.class);
		post.setImageName("piyush.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost = this.prepo.save(post);
		return this.mapper.map(newPost, PostDTO.class);
	}

//	UPDATE POST BY ID
	@Override
	public PostDTO updatePost(PostDTO postDto, Integer postId) {
		Post post = this.prepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id :", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.prepo.save(post);
		return this.mapper.map(save, PostDTO.class);
	}

//	DELETE POST BY ID
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.prepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id :", postId));
		this.prepo.delete(post);
	}

//	GET ALL POSTS
	@Override
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
//		if(sortDir.equalsIgnoreCase("asc"))
//		{
//			sort = Sort.by(sortBy).ascending();
//		}
//		else
//		{
//			sort = Sort.by(sortBy).descending();
//		}
		Pageable p = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> pagePost = this.prepo.findAll(p);
		List<Post> list = pagePost.getContent();
		List<PostDTO> postDtos = list.stream().map((post) -> this.mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		PostResponse response = new PostResponse();
		response.setContent(postDtos);
		response.setPageNo(pagePost.getNumber());
		response.setPageSize(pagePost.getSize());
		response.setTotalElements(pagePost.getTotalElements());
		response.setTotalPages(pagePost.getTotalPages());
		response.setLastPage(pagePost.isLast());
		return response;

	}

//	GET POST BY ID
	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.prepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id :", postId));

		return this.mapper.map(post, PostDTO.class);
	}

//	GET POST BY CATEGORY
	@Override
	public List<PostDTO> getPostByCategory(Integer catId) {
		Category cat = this.crepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id :", catId));
		List<Post> posts = this.prepo.findByCategory(cat);

		List<PostDTO> postDtos = posts.stream().map((post) -> this.mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return postDtos;
	}

//	GET POST BY USER
	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.urepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id :", userId));
		List<Post> userPosts = this.prepo.findByUser(user);
		List<PostDTO> postDtos = userPosts.stream().map((post) -> this.mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return postDtos;
	}

//	SEARCH POST
	@Override
	public List<PostDTO> searchPosts(String keyWord) {
		// TODO Auto-generated method stub
		List<Post> posts = this.prepo.findByTitleContaining(keyWord);
		List<PostDTO> postDtos = posts.stream().map((post)->this.mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDtos;
	}

}
