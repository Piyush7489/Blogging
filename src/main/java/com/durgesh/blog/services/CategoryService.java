package com.durgesh.blog.services;

import java.util.List;

import com.durgesh.blog.payloads.CategoryDTO;

public interface CategoryService {
//	CREATE
	public CategoryDTO createCategory(CategoryDTO categoryDto);

//	UPDATE 
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId);

//	DELETE
	void deleteCategory(Integer categoryId);

//	GET
	CategoryDTO getCategory(Integer categoryId);

//	GET ALL
	List<CategoryDTO> getAllCategory();
}
