package com.durgesh.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Integer postId;
	private String title;
	@NotBlank
	@Size(min = 4,message = "Minimum Size of Category Title is 4",max = 50)
	private String content;
	@NotBlank
	private String imageName ;
	
	private CategoryDTO category;
	
	private UserDTO user;
}
