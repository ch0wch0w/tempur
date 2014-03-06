package com.cta.tempura.control;

import java.util.List;

import javax.ejb.Remote;

import com.cta.tempura.model.Expense;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.User;

@Remote
public interface StartNewExpenseReportAppServiceBeanRemote {

	public ExpenseReport startNewExpenseReport(Integer managerId,
			Integer claimerId, Integer eventId, ExpenseReport report,
			List<Expense> receipts);
	
	public Integer startNewExpenseReport(User manager,
			ExpenseReport expenseReport);
	
	public List<User> getStaff();
	
	public void addClaimers(Integer expenseReportId, List<User> claimers);}
