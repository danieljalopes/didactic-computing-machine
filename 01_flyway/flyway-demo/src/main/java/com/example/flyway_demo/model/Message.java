package com.example.flyway_demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "messages", schema = "myschema")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "i_message")
	private Integer messageId;

	private String text;

	@ManyToOne
	@JoinColumn(name = "e_user")
	private User user;
}
