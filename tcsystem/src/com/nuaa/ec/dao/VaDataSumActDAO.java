package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nuaa.ec.action.VaActDataSum;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.utils.NumberFormatUtil;
import com.nuaa.ec.utils.StoreData;
import com.nuaa.ec.va.exportdata.TeacherJoinedData;
import com.nuaa.ec.va.exportdata.UnjoinedActData;
import com.nuaa.ec.va.exportdata.VaDataSummaryExcel;
import com.opensymphony.xwork2.ActionContext;

public class VaDataSumActDAO {
	private static VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private static VaunJoinRecordDAO vaunJoinRecordDAO = new VaunJoinRecordDAO();
	private static Map<String, String> departmentMap = StoreData.getDepartmentMap();

	/**
	 * 汇总数据的导出
	 * 
	 * @param departmentId
	 * @param foredate
	 * @param afterdate
	 * @return
	 * @throws Exception
	 */
	public static ByteArrayOutputStream getExcelOutputStream(
			String departmentId, String foredate, String afterdate)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<VaActDataSum> vaActDataSumList = (List<VaActDataSum>) ActionContext
				.getContext().getSession().get("vaActSummaryDataByDepartment");
		ByteArrayOutputStream baosArrayOutputStream = new ByteArrayOutputStream();
		VaDataSummaryExcel.genereateVasummaryDataExcel(vaActDataSumList,
				departmentId, foredate, afterdate).write(baosArrayOutputStream);
		return baosArrayOutputStream;
	}

	/**
	 * 按照教师进行公益的数据汇总
	 */
	public static List<VaActDataSum> VasummaryDataByPerson(String queryTeacher,
			String foredate, String afterdate) throws Exception {
		List<VaActDataSum> vaActDataSumList = new ArrayList<VaActDataSum>();
		VaActDataSum vaActDataSum = null;
		List<Teacher> teachers = new TeacherDAO().findTeacherByFuzzyQuery(queryTeacher);
		for (Teacher teacher : teachers) {
			vaActDataSum = new VaActDataSum();
			vaActDataSum.setTeacherId(teacher.getTeacherId());
			vaActDataSum.setTeacherName(teacher.getTeacherName());
			// JoinedActData
			TeacherJoinedData teacherJoinedData = vateacherAndCollectiveActDAO
					.getSummaryDataByTeacher(teacher, foredate, afterdate);
			// UnjoinedActData
			UnjoinedActData unjoinedActData = vaunJoinRecordDAO
					.getSummaryDataByTeacher(teacher, foredate, afterdate);

			vaActDataSum.setTeacherJoinedData(teacherJoinedData);
			vaActDataSum.setUnjoinedActData(unjoinedActData);
			vaActDataSum.setSum(teacherJoinedData.getSum()
					+ unjoinedActData.getSum());
			vaActDataSum.setAverage(vaActDataSum.getSum() / 2);

			vaActDataSumList.add(vaActDataSum);
		}
		return vaActDataSumList;
	}

	/**
	 * 以系为单位进行数据汇总
	 * 
	 * @param departmentIds
	 * @param foredate
	 * @param afterdate
	 * @return
	 * @throws Exception
	 */
	public static List<VaActDataSum> vaActDataSummaryByDepartment(
			List<String> departmentIds, String foredate, String afterdate)
			throws Exception {
		List<VaActDataSum> vaActDataSumList = new ArrayList<VaActDataSum>();
		VaActDataSum vaActDataSum = null;
		for (String departmentId : departmentIds) {
			vaActDataSum = new VaActDataSum();
			vaActDataSum.setDepartmentId(departmentId);
			vaActDataSum.setDepartmentName(departmentMap.get(departmentId));
			// joinedActDataByDepartment
			TeacherJoinedData vaJoinedData = vateacherAndCollectiveActDAO
					.getSummaryDataByDepartment(departmentId, foredate,
							afterdate);
			// unjoinedActDataByDepartment
			UnjoinedActData vaUnjoinedActData = vaunJoinRecordDAO
					.getSummaryDataByDepartment(departmentId, foredate,
							afterdate);
			vaActDataSum.setTeacherJoinedData(vaJoinedData);
			vaActDataSum.setUnjoinedActData(vaUnjoinedActData);
			vaActDataSum.setSum(vaJoinedData.getSum()
					+ vaUnjoinedActData.getSum());
			vaActDataSum.setAverage(vaActDataSum.getSum() / 2);

			vaActDataSumList.add(vaActDataSum);
		}
		getVaDataForEachDepartment(vaActDataSumList);
		return vaActDataSumList;
	}

	/**
	 * 总计一栏中 得到所有系的平均和汇总情况
	 * 
	 * @param vaActDataSumList
	 * @throws Exception
	 */
	private static void getVaDataForEachDepartment(
			List<VaActDataSum> vaActDataSumList) throws Exception {
		// TODO Auto-generated method stub
		VaActDataSum vaActDataSum = new VaActDataSum();
		float joinedActDataSum = 0, unjoinedActDataSum = 0;
		for (VaActDataSum vaActDataSum2 : vaActDataSumList) {
			joinedActDataSum += vaActDataSum2.getTeacherJoinedData().getSum();
			unjoinedActDataSum += vaActDataSum2.getUnjoinedActData().getSum();
		}
		TeacherJoinedData vaJoinedData = new TeacherJoinedData();
		UnjoinedActData vaUnjoinedActData = new UnjoinedActData();

		int size = vaActDataSumList.size();
		// 活动参与
		vaJoinedData.setSum(joinedActDataSum);
		vaJoinedData.setAverage(NumberFormatUtil
				.getNumberAfterTransferPrecision(joinedActDataSum / size));
		// 活动缺席
		vaUnjoinedActData.setSum(unjoinedActDataSum);
		vaUnjoinedActData.setAverage(NumberFormatUtil
				.getNumberAfterTransferPrecision(unjoinedActDataSum / size));

		vaActDataSum.setDepartmentId("allDepartment");
		vaActDataSum.setDepartmentName("合计");
		vaActDataSum.setTeacherJoinedData(vaJoinedData);
		vaActDataSum.setUnjoinedActData(vaUnjoinedActData);
		vaActDataSum.setSum(vaJoinedData.getSum() + vaUnjoinedActData.getSum());
		vaActDataSum.setAverage(NumberFormatUtil
				.getNumberAfterTransferPrecision(vaActDataSum.getSum() / 2));
		vaActDataSumList.add(vaActDataSum);
	}
}
