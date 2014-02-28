package com.cta.tempura.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expenses")
@NamedQuery(name="Expense.findAll", query="SELECT e FROM Expense e")
public class Expense implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal amount;

	private String name;

	private Boolean reimbursable;

	@ManyToOne
	@JoinColumn(name = "claimer")
	private User claimer;

	@ManyToOne
	@JoinColumn(name = "report")
	private ExpenseReport report;

	@OneToOne(mappedBy = "expense")
	private Note note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReimbursable() {
		return reimbursable;
	}

	public void setReimbursable(Boolean reimbursable) {
		this.reimbursable = reimbursable;
	}

	public User getClaimer() {
		return claimer;
	}

	public void setClaimer(User claimer) {
		this.claimer = claimer;
	}

	public ExpenseReport getExpenseReport() {
		return report;
	}

	public void setExpenseReport(ExpenseReport report) {
		this.report = report;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}