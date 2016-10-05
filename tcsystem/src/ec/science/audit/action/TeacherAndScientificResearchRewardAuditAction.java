package ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchRewardDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.model.TeacherAndscientificResearchReward;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndScientificResearchRewardAuditAction implements RequestAware {
	public String getTARRewardWithDividedPage() {
		Transaction tx = null;
		System.out.println("pageSize:"+(Integer) session.get("pageSize_TARR"));
		System.out.println("pageIndex:"+pageIndex_TARR);
		try {
			this.request.put("TAR_RewardList", this.TARRewardDAO
					.getTASRProjectListsAfterDivided(pageIndex_TARR,
							(Integer) session.get("pageSize_TARR"),
							(String) session.get("foredate_TARR"),
							(String) session.get("afterdate_TARR"),
							(ResearchLab)session.get("selectedReserchLab_TARR"),
							(String) session.get("checkoutStatus_TARR")));
			tx = this.TARRewardDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
		this.getResearchLabList();
		return "success";
	}

	public void doCheckOutTask() {
		String[] ids = this.checkOutIDs.split(",");
		List<TeacherAndscientificResearchReward> checkoutList = new ArrayList<TeacherAndscientificResearchReward>();
		TeacherAndscientificResearchReward TARReward = null;
		for (int i = 0; i < ids.length; i++) {
			TARReward = this.TARRewardDAO.findById(Integer.parseInt(ids[i]));
			// 修改checkout 标志
			TARReward.setCheckOut("1");
			checkoutList.add(TARReward);
		}
		// 将待审核的项目传向后台
		try {
			if (TARRewardDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getResearchLabList();
	}

	public String getRewardInfo() {
		Transaction tx = null;
		if ((ResearchLab) session.get("selectedReserchLab_TARR") == null) {
			session.put("selectedReserchLab_TARR", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TARR") == null) {
			session.put("pageSize_TARR", 1);
		}
		try {
			this.request.put("TAR_RewardList",TARRewardDAO
					.findAllWithCondition(pageIndex_TARR, (Integer) session
							.get("pageSize_TARR"), (String) session
							.get("foredate_TARR"), (String) session
							.get("afterdate_TARR"), (ResearchLab) session
							.get("selectedReserchLab_TARR"), (String) session
							.get("checkoutStatus_TARR")));
			tx = this.TARRewardDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
		this.getResearchLabList();
		return "success";
	}

	public void getResearchLabList() {
		Transaction tx = null;
		/*
		 * if (researchLab == null) { researchLab = new ResearchLab(); }
		 */
		/* session.put("selectedReserchLab_TARR", researchLab); */
		try {
			this.request.put("researchLabList_TARR", researchDAO.findAll());
			tx = this.researchDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
	}

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex_TARR = 1;
	private int pageSize = 1;
	private TeacherAndscientificResearchReward TARReward;
	private TeacherAndscientificResearchRewardDAO TARRewardDAO = new TeacherAndscientificResearchRewardDAO();
	private ResearchLab researchLab;
	private ResearchLabDAO researchDAO = new ResearchLabDAO();
	private int operstatus;
	private String foredate;
	private String afterdate;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private String checkout = "0";
	private Map<String, Object> request = null;
	private String checkOutIDs;

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		/**
		 * 在这里对前台传来的checkout状态进行拦截存到session里面
		 */
		session.put("checkoutStatus_TARR", checkout);
		this.checkout = checkout;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		/**
		 * 要改变日期肯定要经过这里 ，所以在这里加上拦截，将日期存到session里面
		 */
		this.foredate = foredate;
		session.put("foredate_TARR", foredate);
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
		session.put("afterdate_TARR", afterdate);
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}


	public int getPageIndex_TARR() {
		return pageIndex_TARR;
	}

	public void setPageIndex_TARR(int pageIndex_TARR) {
		this.pageIndex_TARR = pageIndex_TARR;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		session.put("pageSize_TARR", pageSize);
		this.pageSize = pageSize;
	}

	public TeacherAndscientificResearchReward getTARReward() {
		return TARReward;
	}

	public void setTARReward(TeacherAndscientificResearchReward tARReward) {
		TARReward = tARReward;
	}

	public ResearchLab getResearchLab() {
		return researchLab;
	}

	public void setResearchLab(ResearchLab researchLab) {
		/*
		 * 更换研究所要运行到这里， 用session存储将要查看对应的reward信息的研究所
		 */
		session.put("selectedReserchLab_TARR", researchLab);
		this.researchLab = researchLab;
	}
}
