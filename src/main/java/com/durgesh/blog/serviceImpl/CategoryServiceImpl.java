package com.durgesh.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.blog.entity.Category;
import com.durgesh.blog.exceptions.ResourceNotFoundException;
import com.durgesh.blog.payloads.CategoryDTO;
import com.durgesh.blog.repository.CategoryRepo;
import com.durgesh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo repo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDto) {
		Category cat = this.mapper.map(categoryDto, Category.class);
		Category savedCategory = this.repo.save(cat);
		return this.mapper.map(savedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.repo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.repo.save(cat);
		return this.mapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.repo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Ctegory Id", categoryId));
		this.repo.delete(cat);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.repo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("CATEGORY", "Categoty Id", categoryId));
		return this.mapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> list = this.repo.findAll();
		List<CategoryDTO> listOfDto = list.stream().map((cat) -> this.mapper.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());
		return listOfDto;
	}

}
