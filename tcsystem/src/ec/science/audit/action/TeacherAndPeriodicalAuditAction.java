package ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndperiodical;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndPeriodicalAuditAction implements RequestAware {
	public void doCheckOutTask() {
		this.getResearchLabList();
		String[] ids = this.checkOutIDs.split(",");
		List<TeacherAndperiodical> checkoutList = new ArrayList<TeacherAndperiodical>();
		TeacherAndperiodical TAPeriodical = null;
		for (int i = 0; i < ids.length; i++) {
			TAPeriodical = this.TAPeriodialDAO.findById(Integer
					.parseInt(ids[i]));
			// 修改checkout 标志
			TAPeriodical.setCheckOut("1");
			checkoutList.add(TAPeriodical);
		}
		// 将待审核的项目传向后台
		try {
			if (TAPeriodialDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this.getResearchLabList();
	}

	public String getTAPeriodicalListAfterDivided() {
		Transaction tx = null;
		try {
			this.request.put("TAPUnionPPModelList", this.TAPeriodialDAO
					.getTAPeriodicalListsAfterDivided(pageIndex,
							(Integer) session.get("pageSize_TAPA"),
							(String) session.get("foredate_TAPA"),
							(String) session.get("afterdate_TAPA"),
							(ResearchLab) session
									.get("selectedResearchLab_TAPA"),
							(String) session.get("checkOutStatus_TAPA")));
			tx = this.TAPeriodialDAO.getSession().beginTransaction();
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

	public String getTAPeriodicalList() {
		Transaction tx = null;
		if ((ResearchLab) session.get("selectedResearchLab_TAPA") == null) {
			session.put("selectedResearchLab_TAPA", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAPA") == null) {
			session.put("pageSize_TAPA", 1);
		}
		try {
			this.request.put("TAPUnionPPModelList", this.TAPeriodialDAO
					.findAllWithCondition(pageIndex, (Integer) session
							.get("pageSize_TAPA"), (String) session
							.get("foredate_TAPA"), (String) session
							.get("afterdate_TAPA"), (ResearchLab) session
							.get("selectedResearchLab_TAPA"), (String) session
							.get("checkOutStatus_TAPA")));
			tx = this.TAPeriodialDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			tx.rollback();
			ex.printStackTrace();
		}
		this.getResearchLabList();
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	public void getResearchLabList() {
		Transaction tx = null;
		/*
		 * if (researchLab == null) { researchLab = new ResearchLab(); }
		 */
		/* session.put("selectedReserchLab_TARR", researchLab); */
		try {
			this.request.put("researchLabList_TAPA", researchLabDAO.findAll());
			tx = this.researchLabDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
	}

	private String foredate_TAPA;
	private String afterdate_TAPA;
	private int pageIndex = 1;
	private int pageSize_TAPA;
	private ResearchLab researchLab_TAPA;
	private String checkOutStatus_TAPA;
	private String checkOutIDs;
	private ResearchLabDAO researchLabDAO = new ResearchLabDAO();
	private TeacherAndperiodicalDAO TAPeriodialDAO = new TeacherAndperiodicalDAO();
	private Map<String, Object> request;
	private int operstatus;

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public String getForedate_TAPA() {
		return foredate_TAPA;
	}

	public void setForedate_TAPA(String foredate_TAPA) {
		this.foredate_TAPA = foredate_TAPA;
		session.put("foredate_TAPA", foredate_TAPA);
	}

	public String getAfterdate_TAPA() {
		return afterdate_TAPA;
	}

	public void setAfterdate_TAPA(String afterdate_TAPA) {
		this.afterdate_TAPA = afterdate_TAPA;
		session.put("afterdate_TAPA", afterdate_TAPA);
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_TAPA() {
		return pageSize_TAPA;
	}

	public void setPageSize_TAPA(int pageSize_TAPA) {
		this.pageSize_TAPA = pageSize_TAPA;
		session.put("pageSize_TAPA", pageSize_TAPA);
	}

	public ResearchLab getResearchLab_TAPA() {
		return researchLab_TAPA;
	}

	public void setResearchLab_TAPA(ResearchLab researchLab_TAPA) {
		this.researchLab_TAPA = researchLab_TAPA;
		session.put("selectedResearchLab_TAPA", researchLab_TAPA);
	}

	public String getCheckOutStatus_TAPA() {
		return checkOutStatus_TAPA;
	}

	public void setCheckOutStatus_TAPA(String checkOutStatus_TAPA) {
		this.checkOutStatus_TAPA = checkOutStatus_TAPA;
		session.put("checkOutStatus_TAPA", checkOutStatus_TAPA);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
}
