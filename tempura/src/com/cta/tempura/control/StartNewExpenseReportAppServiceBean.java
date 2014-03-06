package com.cta.tempura.control;

import java.util.List;

import javax.ejb.Stateless;

import com.cta.tempura.appservice.StartNewExpenseReportAppService;
import com.cta.tempura.model.Expense;
import com.cta.tempura.model.ExpenseReport;
import com.cta.tempura.model.User;

/**
 * Session Bean implementation class StartNewExpenseReportAppServiceBean
 */
@Stateless(mappedName = "java:global/tempura/NewExpenseReport")
public class StartNewExpenseReportAppServiceBean implements StartNewExpenseReportAppServiceBeanRemote {

    /**
     * Default constructor. 
     */
    public StartNewExpenseReportAppServiceBean() {
        // TODO Auto-generated constructor stub
    }
    
	public ExpenseReport startNewExpenseReport(Integer managerId,
			Integer claimerId, Integer eventId, ExpenseReport report,
			List<Expense> receipts) {
		
		StartNewExpenseReportAppService ctl = StartNewExpenseReportAppService.getController();
		return ctl.startNewExpenseReport(managerId, claimerId, eventId, report, receipts);
	}
	
	public Integer startNewExpenseReport(User manager,
			ExpenseReport expenseReport) {
				return null;
	}
	
	public List<User> getStaff() {
		return null;
	}
	
	public void addClaimers(Integer expenseReportId, List<User> claimers) {
	}

	

}
