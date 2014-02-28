package com.cta.tempura.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="expense_events")
@NamedQuery(name="ExpenseEvent.findAll", query="SELECT e FROM ExpenseEvent e")
public class ExpenseEvent implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	
	
	@OneToMany (mappedBy="event", fetch = FetchType.EAGER)
	private List<ExpenseReport> expenseReports;
	
	
	
	@Column(name="name")
	private String eventName;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private EventType eventType;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public List<ExpenseReport> getExpenseReports() {
		return expenseReports;
	}

	public void setExpenseReports(List<ExpenseReport> expenseReports) {
		this.expenseReports = expenseReports;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "ExpenseEvent [eventId=" + eventId + ", expenseReports="
				+ expenseReports + ", eventName=" + eventName + ", eventType="
				+ eventType + "]";
	}
	
	
}
