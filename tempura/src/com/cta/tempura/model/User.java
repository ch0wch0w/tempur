package com.cta.tempura.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@ManyToMany (mappedBy="users")
	private List<ExpenseReport> reports;
	
	@OneToMany (mappedBy="claimer")
	private List<Reimbursement> reimbursements;
	
	@OneToMany (mappedBy="claimer")
	private List<Expense> expenses;
	
	@Column(name="name")
	private String userName;
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private RoleType userRole;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RoleType getUserRole() {
		return userRole;
	}

	public void setUserRole(RoleType userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userRole=" + userRole + "]\n";
	}
	
	

}
