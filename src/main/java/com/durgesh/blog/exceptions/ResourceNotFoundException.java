package com.durgesh.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	Integer fieldValue;

	public ResourceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResourceNotFoundException(String resourceName,String fieldName,Integer userId)
	{
		super(String .format("%s not found with %s : %s", resourceName,fieldName,userId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = userId;
	}
}
