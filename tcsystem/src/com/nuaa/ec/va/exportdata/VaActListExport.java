package com.nuaa.ec.va.exportdata;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.VateacherAndCollectiveActId;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.stringstore;

public class VaActListExport implements SessionAware, RequestAware {

	private String afterdate;
	private String foredate;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private Department department;
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private VaunJoinRecordDAO vaunJoinRecordDAO = new VaunJoinRecordDAO();
	private VacollectiveActivitiesPublishDAO vacollectiveActivitiesPublishDAO = new VacollectiveActivitiesPublishDAO();
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private String vaacttype;
	private String actPubId;
	private String departmentId;

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public VacollectiveActivitiesPublishDAO getVacollectiveActivitiesPublishDAO() {
		return vacollectiveActivitiesPublishDAO;
	}

	public void setVacollectiveActivitiesPublishDAO(VacollectiveActivitiesPublishDAO vacollectiveActivitiesPublishDAO) {
		this.vacollectiveActivitiesPublishDAO = vacollectiveActivitiesPublishDAO;
	}

	public VateacherAndCollectiveActDAO getVateacherAndCollectiveActDAO() {
		return vateacherAndCollectiveActDAO;
	}

	public void setVateacherAndCollectiveActDAO(VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO) {
		this.vateacherAndCollectiveActDAO = vateacherAndCollectiveActDAO;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String execute() {
		return "success";
	}

	public void generateExportDataList() throws Exception {
		try {
			ByteArrayOutputStream baos = null;
			baos = vacollectiveActivitiesPublishDAO.findwithexport(department, vaacttype,
					(departmentDAO.findById(department.getDepartmentId())).getDepartmentName(), foredate, afterdate);
			if (baos != null) {
				HttpServletResponse resp = ServletActionContext.getResponse();
				OutputStream outStream = resp.getOutputStream();
				// System.out.println("1");
				resp.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(foredate + "至" + afterdate + "活动信息", "utf-8")
								+ ".xls");
				byte[] bt = baos.toByteArray();
				outStream.write(bt, 0, bt.length);
				outStream.flush();
				outStream.close();
			} else {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该时段区间没有数据");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public String lookateveryAct() throws Exception {
		Transaction tx = null;
		try {
			department = departmentDAO.findById(departmentId);
			this.request.put("vaactList", this.vacollectiveActivitiesPublishDAO.getNewActPublishAct(department,
					vaacttype, foredate, afterdate));
			tx = this.vacollectiveActivitiesPublishDAO.getSession().beginTransaction();
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	public void oneActExport() throws Exception {
		// String actName = vateacherAndCollectiveActDAO.findById(new
		// VateacherAndCollectiveActId(
		// new VacollectiveActivitiesPublishDAO().findById(actPubId),
		// new TeacherDAO().findById(new
		// VacollectiveActivitiesPublishDAO().findById(actPubId).getTeacherId())
		// )).getId().getVacollectiveActivitiesPublish().getVacollectiveAct().getActName();
		String actName = vacollectiveActivitiesPublishDAO.findById(actPubId).getVacollectiveAct().getActName();
		// String actDate = vateacherAndCollectiveActDAO.findById(new
		// VateacherAndCollectiveActId(
		// new VacollectiveActivitiesPublishDAO().findById(actPubId),
		// new TeacherDAO().findById(new
		// VacollectiveActivitiesPublishDAO().findById(actPubId).getTeacherId())
		// )).getId().getVacollectiveActivitiesPublish().getActDate();
		String actDate = vacollectiveActivitiesPublishDAO.findById(actPubId).getActDate();
		try {
			ByteArrayOutputStream baos = null;
			ByteArrayOutputStream baos1 = null;
			ByteArrayOutputStream byteArrayOutputStream = null;
			byteArrayOutputStream = vateacherAndCollectiveActDAO.findwithexport(actDate, vacollectiveActivitiesPublishDAO.findById(actPubId).getVacollectiveAct().getActId(), actDate, actName);
//			baos = vateacherAndCollectiveActDAO.findwithexport(actPubId, actDate, actName);
//			baos1 = vaunJoinRecordDAO.findwithexport(vacollectiveActivitiesPublishDAO.findById(actPubId).getVacollectiveAct().getActId(),actDate,actName);
//			 vateacherAndCollectiveActDAO.findUnjoinedwithexport(actPubId,actDate,actName);
//			if (baos!=null || baos1!=null) {
//				if (baos != null) {
//					HttpServletResponse response = ServletActionContext.getResponse();
//					OutputStream outsStream = response.getOutputStream();
//					response.setHeader("Content-Disposition",
//							"attachment;filename=" + URLEncoder.encode(actDate + "活动" + actName + "参与信息.xls"));
//					byte[] bt = baos.toByteArray();
//					outsStream.write(bt, 0, bt.length);
//					outsStream.flush();
//					outsStream.close();
//				}
//				if (baos1 != null) {
//					HttpServletResponse response = ServletActionContext.getResponse();
//					OutputStream outsStream = response.getOutputStream();
//					response.setHeader("Content-Disposition",
//							"attachment;filename=" + URLEncoder.encode(actDate + "活动" + actName + "缺席信息.xls"));
//					byte[] bt = baos1.toByteArray();
//					outsStream.write(bt, 0, bt.length);
//					outsStream.flush();
//					outsStream.close();
//				} 
			if (byteArrayOutputStream != null) {
				HttpServletResponse response = ServletActionContext.getResponse();
				OutputStream outsStream = response.getOutputStream();
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode(actDate + "活动" + actName + "参与信息.xls"));
				byte[] bt = byteArrayOutputStream.toByteArray();
				outsStream.write(bt, 0, bt.length);
				outsStream.flush();
				outsStream.close();
			}else {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该活动没有数据");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getVaacttype() {
		return vaacttype;
	}

	public void setVaacttype(String vaacttype) {
		this.vaacttype = vaacttype;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getActPubId() {
		return actPubId;
	}

	public void setActPubId(String actPubId) {
		this.actPubId = actPubId;
	}

}
