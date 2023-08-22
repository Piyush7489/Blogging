package com.durgesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.blog.payloads.APIResponse;
import com.durgesh.blog.payloads.CategoryDTO;
import com.durgesh.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService cs;

// 	CREATE
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categotyDto) {
		CategoryDTO createCategory = this.cs.createCategory(categotyDto);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}

//	UPDATE
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categotyDto,
			@PathVariable Integer catId) {

		CategoryDTO updatedCategory = this.cs.updateCategory(categotyDto, catId);
		return new ResponseEntity<CategoryDTO>(updatedCategory, HttpStatus.OK);
	}

//	DELETE
	@DeleteMapping("/{catId}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer catId) {
		this.cs.deleteCategory(catId);
		return new ResponseEntity<APIResponse>(new APIResponse("Category is Deleted Successfully !!", true),
				HttpStatus.OK);
	}

//	GET ALL
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		List<CategoryDTO> category = this.cs.getAllCategory();
		return ResponseEntity.ok(category);
	}

//	GET 
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId) {
		CategoryDTO category = this.cs.getCategory(catId);
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
	}
}
