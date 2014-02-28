package com.cta.tempura.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reimbursements")
@NamedQuery(name="Reimbursement.findAll", query="SELECT r FROM Reimbursement r")
public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="amount")
	private double amount;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "claimer")
	private User claimer;

	@ManyToOne
	@JoinColumn(name = "report")
	private ExpenseReport report;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public ExpenseReport getReport() {
		return report;
	}
	public void setReport(ExpenseReport report) {
		this.report = report;
	}
	public User getClaimer() {
		return claimer;
	}
	public void setClaimer(User claimer) {
		this.claimer = claimer;
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
	@Override
	public String toString() {
		return "Reimbursements [id=" + id + ", amount=" + amount + ", report="
				+ report + ", claimer=" + claimer + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
	
}
