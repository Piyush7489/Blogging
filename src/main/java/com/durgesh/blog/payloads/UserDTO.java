package com.durgesh.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO {
	
	
	private Integer id;
	@NotEmpty
	@Size(min = 4,message = "Username must be minimum of 4 characters")
	private String name;
	
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min = 4,message = "password must be minimum of 4 characters and max of 10 characters",max = 10)
//	@Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8, 20}$")
	private String password;
	@NotEmpty
	private String about;
}
