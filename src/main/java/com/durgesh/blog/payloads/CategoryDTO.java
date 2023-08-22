package com.durgesh.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDTO {
	
	private Integer categoryId;
	@NotBlank
	@Size(min = 4,message = "Minimum Size of Category Title is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10,message = "Minimum Size of Category Description is 10")
	private String categoryDescription;
}
