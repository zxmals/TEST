package com.nuaa.ec.action;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.VaDataSumActDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.utils.StoreData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VaDataSummaryAction extends ActionSupport implements RequestAware {

	private static List<String> departmentIds = new ArrayList<String>();
	static {
		List<Department> departments = StoreData.getDepartmentList();
		for (Department department : departments) {
			departmentIds.add(department.getDepartmentId());
		}
	}

	/**
	 * 按照xi汇总数据
	 * 
	 * @throws Exception
	 */
//	public String getVaSummaryDataByDepartment() throws Exception {
//		List<VaActDataSum> vaActDataSumList = new ArrayList<VaActDataSum>();
//		if (departmentId.trim().equals("allDepartment")) {
//			vaActDataSumList = VaDataSumActDAO.vaActDataSummaryByDepartment(
//					departmentIds, foredate, afterdate);
//		} else {
//			List<String> departmentIds = new ArrayList<String>();
//			departmentIds.add(departmentId);
//			vaActDataSumList = VaDataSumActDAO.vaActDataSummaryByDepartment(
//					departmentIds, foredate, afterdate);
//		}
//		this.request.put("vaActSummaryDataByDepartment", vaActDataSumList);
//		/**
//		 * 用于导出汇总数据时候不用重新查询，直接从缓存中取出数据，导出以后 清掉session对应的项
//		 * session保存的总是最近一次查询的汇总结果
//		 */
//		session.put("vaActSummaryDataByDepartment", vaActDataSumList);
//		return "success";
//	}

	/**
	 * 根据TeacherId得到数据
	 * @return
	 * @throws Exception
	 */
	public String getVaDataByTeacher() throws Exception{
		this.request.put("vaActSummaryDateByPerson", VaDataSumActDAO.VasummaryDataByPerson(teacherId, foredate, afterdate));
		return "success";
	}
	
	/**
	 * 数据导出
	 * @throws Exception
	 */
	public void getSummaryDataExcel() throws Exception {
		ByteArrayOutputStream baos = VaDataSumActDAO.getExcelOutputStream(
				(String) session.get("departmentId_sum"),
				(String) session.get("foredate_sum"),
				(String) session.put("afterdate_sum", afterdate));
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream outputStream = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode("公益数据汇总", "UTF-8") + ".xls");
		byte[] by = baos.toByteArray();
		outputStream.write(by, 0, by.length);
		outputStream.flush();
		outputStream.close();
	}

	public void getDetailDataInfoJson() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		String json = null;
		if (this.modulename.trim().equals("vaJoinedData")) {
			List<VateacherAndCollectiveAct> vaJoinedActDataActs = vateacherAndCollectiveActDAO.getPersonDetailsOfJoinedAct(teacherId,foredate,afterdate);
//			json = Jsonutil.
		}
	}
	public String execute() throws Exception {
		return "success";
	}

	private String foredate;
	private String afterdate;
	private String departmentId;
	private String teacherId;
	private String modulename;
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO;
	private VaunJoinRecordDAO vaunJoinRecordDAO;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;

	public static List<String> getDepartmentIds() {
		return departmentIds;
	}

	public static void setDepartmentIds(List<String> departmentIds) {
		VaDataSummaryAction.departmentIds = departmentIds;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public VateacherAndCollectiveActDAO getVateacherAndCollectiveActDAO() {
		return vateacherAndCollectiveActDAO;
	}

	public void setVateacherAndCollectiveActDAO(VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO) {
		this.vateacherAndCollectiveActDAO = vateacherAndCollectiveActDAO;
	}

	public VaunJoinRecordDAO getVaunJoinRecordDAO() {
		return vaunJoinRecordDAO;
	}

	public void setVaunJoinRecordDAO(VaunJoinRecordDAO vaunJoinRecordDAO) {
		this.vaunJoinRecordDAO = vaunJoinRecordDAO;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
