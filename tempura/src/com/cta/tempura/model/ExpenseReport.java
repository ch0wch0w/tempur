package com.cta.tempura.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="expense_reports")
@NamedQuery(name="ExpenseReport.findAll", query="SELECT e FROM ExpenseReport e")
public class ExpenseReport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="report")
	private List<Expense> expenses;
	
	@OneToMany(mappedBy="report")
	private List<Reimbursement> reimbursements;
	
	
	@ManyToMany
	@JoinTable(
			name="expense_reports_users", 
			joinColumns={ 
					@JoinColumn(name="expense_report") 
					}, 
			inverseJoinColumns= { 
					@JoinColumn(name="user") 
					})
	private List<User> users;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="manager")
	private User manager;

	@ManyToOne
	@JoinColumn(name="event")
	private ExpenseEvent event;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public ExpenseEvent getEvent() {
		return event;
	}

	public void setEvent(ExpenseEvent event) {
		this.event = event;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ExpenseReport [id=" + id + ", name=" + name + ", manager="
				+ manager.getUserId() + ", event=" + event.getEventId() + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	

	

	

	
	
	
	
   
}

