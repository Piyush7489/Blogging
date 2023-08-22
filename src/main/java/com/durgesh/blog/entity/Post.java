package com.durgesh.blog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	@Column( nullable = false ,length = 255)
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;
	@ManyToOne
	@JsonIgnoreProperties(value = {"posts"})
	private Category category;
	@ManyToOne
	@JsonIgnoreProperties(value = {"posts"})
	private User user;
}
