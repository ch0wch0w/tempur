package com.cta.tempura.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "expense")
	private Integer id;

	@Lob
	private Blob image;

	@OneToOne
	@JoinColumn(name = "expense")
	private Expense expense;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}
}