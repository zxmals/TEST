package com.nuaa.ec.science.rt_audit.action;

import org.hibernate.Transaction;

public class GTScientificResearchProjectAuditAction_person {
	/**
	 * 取出团队成员
	 */
	public String getAllRecord_person() {
		Transaction tx=null;
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	/**
	 * 审核团队中的成员
	 */
	public void doCheckOut_person() {
		
	}
}
