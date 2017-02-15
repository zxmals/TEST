package com.nuaa.ec.va.exportdata;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VacollectiveActivitiesPublish;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VaunJoinRecord;
import com.nuaa.ec.utils.StoreData;

public class VaActListExcel {
	public static HSSFWorkbook generateExcel(String[] ths, List<VacollectiveActivitiesPublish> list,
			String departmentName, String foredate, String afterdate) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(foredate + "-" + afterdate);
		// 设置列宽
		for (int i = 0; i < ths.length; i++) {
			sheet.setColumnWidth((short) i, 5000);
		}
		// 合并单元格
		Region region = new Region((short) 0, (short) 0, (short) 1, (short) (ths.length - 1));
		sheet.addMergedRegion(region);
		// 创建预备居中格式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle = wb.createCellStyle();
		// 设置格式居中
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);
		cellStyleforFontStyle.setAlignment(cellStyle.ALIGN_CENTER);
		// 创建字体
		HSSFFont font = wb.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 合并字体到居中大字体格式
		cellStyleforFontStyle.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		cells[0].setCellValue(departmentName + foredate + "至" + afterdate + "活动列表");
		// 应用格式
		cells[0].setCellStyle(cellStyleforFontStyle);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[ths.length];
		for (int i = 0; i < cell.length; i++) {
			cell[i] = row.createCell(i);
			cell[i].setCellValue(ths[i]);
			cell[i].setCellStyle(cellStyle);
		}
		if (list != null) {
			Map<String, Object> teachers = StoreData.getTeachertranslate();
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for (int j = 0; j < ths.length; j++) {
					cell[j] = row.createCell(j);
				}
				cell[0].setCellValue(list.get(i).getActPubId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(list.get(i).getVacollectiveAct().getActName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(list.get(i).getVacollectiveAct().getActType());
				if (list.get(i).getVacollectiveAct().getActType().equals("1")) {
					cell[2].setCellValue("规定性活动");
				} else if (list.get(i).getVacollectiveAct().getActType().equals("2")) {
					cell[2].setCellValue("选择性活动");
				} else if (list.get(i).getVacollectiveAct().getActType().equals("3")) {
					cell[2].setCellValue("其他活动");
				}
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(list.get(i).getVacollectiveAct().getAttendee());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(list.get(i).getActDate());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(list.get(i).getTeacherId());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue((String) teachers.get(list.get(i).getTeacherId()));
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(list.get(i).getVacollectiveAct().getScore());
				cell[7].setCellStyle(cellStyle);
			}
		}
		return wb;
	}

	public static HSSFWorkbook generateJoinedExcel(String[] ths, List<VateacherAndCollectiveAct> list, String actDate,
			String actName) {
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(actName + "活动参与信息");
		// 设置列宽
		for (int i = 0; i < ths.length; i++) {
			sheet.setColumnWidth((short) i, 5000);
		}
		// 合并单元格
		Region region = new Region((short) 0, (short) 0, (short) 1, (short) (ths.length - 1));
		sheet.addMergedRegion(region);
		// 创建预备居中格式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle = wb.createCellStyle();
		// 设置格式居中
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);
		cellStyleforFontStyle.setAlignment(cellStyle.ALIGN_CENTER);
		// 创建字体
		HSSFFont font = wb.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 合并字体到居中大字体格式
		cellStyleforFontStyle.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		cells[0].setCellValue(actDate + "  " + actName + "  " + "参与信息");
		// 应用格式
		cells[0].setCellStyle(cellStyleforFontStyle);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[ths.length];
		for (int i = 0; i < cell.length; i++) {
			cell[i] = row.createCell(i);
			cell[i].setCellValue(ths[i]);
			cell[i].setCellStyle(cellStyle);
		}
		if (list != null) {
			Map<String, Object> teachers = StoreData.getTeachertranslate();
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for (int j = 0; j < ths.length; j++) {
					cell[j] = row.createCell(j);
				}
				cell[0].setCellValue(list.get(i).getId().getVacollectiveActivitiesPublish().getActPubId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(list.get(i).getId().getVacollectiveActivitiesPublish().getVacollectiveAct()
						.getActName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(list.get(i).getId().getTeacher().getTeacherId());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue((String) teachers.get(list.get(i).getId().getTeacher().getTeacherId()));
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(list.get(i).getScore());
				cell[4].setCellStyle(cellStyle);
				if (list.get(i).getAspareTire().equals("0")) {
					cell[5].setCellValue("待审核");
				}else if (list.get(i).getAspareTire().equals("1")) {
					cell[5].setCellValue("审核通过");
				}else if (list.get(i).getAspareTire().equals("2")) {
					cell[5].setCellValue("审核不通过");
				}
				cell[5].setCellStyle(cellStyle);
				;
			}
		}
		return wb;
	}

	public static HSSFWorkbook generateTeacherUnJoinedExcel(String[] vaunjoinedact, List<VaunJoinRecord> list2,
			String actDate, String actName) {
		// TODO Auto-generated method stub
		VacollectiveActDAO vacollectiveActDAO = new VacollectiveActDAO();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet2 = wb.createSheet(actName + "活动缺席信息");
		// 设置列宽
		for (int i = 0; i < vaunjoinedact.length; i++) {
			sheet2.setColumnWidth((short) i, 5000);
		}
		// 合并单元格
		Region region1 = new Region((short) 0, (short) 0, (short) 1, (short) (vaunjoinedact.length - 1));
		sheet2.addMergedRegion(region1);
		// 创建预备居中格式
		HSSFCellStyle cellStyle1 = wb.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle1 = wb.createCellStyle();
		// 设置格式居中
		cellStyle1.setAlignment(cellStyle1.ALIGN_CENTER);
		cellStyleforFontStyle1.setAlignment(cellStyle1.ALIGN_CENTER);
		// 创建字体
		HSSFFont font1 = wb.createFont();
		// 设置字体大小
		font1.setFontHeightInPoints((short) 14);
		// 合并字体到居中大字体格式
		cellStyleforFontStyle1.setFont(font1);
		int rownum1 = 0;
		HSSFRow row1 = sheet2.createRow((short) rownum1++);
		HSSFCell cells1[] = new HSSFCell[1];
		cells1[0] = row1.createCell(0);
		cells1[0].setCellValue(actDate + "  " + actName + "  " + "缺席信息");
		// 应用格式
		cells1[0].setCellStyle(cellStyleforFontStyle1);
		rownum1++;
		row1 = sheet2.createRow((short) rownum1++);
		HSSFCell cell1[] = new HSSFCell[vaunjoinedact.length];
		for (int i = 0; i < cell1.length; i++) {
			cell1[i] = row1.createCell(i);
			cell1[i].setCellValue(vaunjoinedact[i]);
			cell1[i].setCellStyle(cellStyle1);
		}
		if (list2 != null) {
			Map<String, Object> teachers = StoreData.getTeachertranslate();
			for (int i = 0; i < list2.size(); i++) {
				row1 = sheet2.createRow(rownum1++);
				cell1 = new HSSFCell[vaunjoinedact.length];
				for (int j = 0; j < vaunjoinedact.length; j++) {
					cell1[j] = row1.createCell(j);
				}
				cell1[0].setCellValue(list2.get(i).getActId());
				cell1[0].setCellStyle(cellStyle1);
				cell1[1].setCellValue(vacollectiveActDAO.findById(list2.get(i).getActId()).getActName());
				cell1[1].setCellStyle(cellStyle1);
				cell1[2].setCellValue(list2.get(i).getTeacherId());
				cell1[2].setCellStyle(cellStyle1);
				cell1[3].setCellValue((String) teachers.get(list2.get(i).getTeacherId()));
				cell1[3].setCellStyle(cellStyle1);
				cell1[4].setCellValue(list2.get(i).getResultscore());
				cell1[4].setCellStyle(cellStyle1);
				if (list2.get(i).getAsparetire().equals("0")) {
					cell1[5].setCellValue("待审核");
				}else if (list2.get(i).getAsparetire().equals("1")) {
					cell1[5].setCellValue("审核通过");
				}else if (list2.get(i).getAsparetire().equals("2")) {
					cell1[5].setCellValue("审核不通过");
				}
				cell1[5].setCellStyle(cellStyle1);
			}
		}
		return wb;
	}

	public static HSSFWorkbook generateExcel(String[] vajoinedact, String[] vaunjoinedact, List<VateacherAndCollectiveAct> list,
			List<VaunJoinRecord> list2, String actDate, String actName) {
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(actName + "活动参与信息");
		HSSFSheet sheet2 = wb.createSheet(actName + "缺席信息");
		// 设置列宽
		for (int i = 0; i < vajoinedact.length; i++) {
			sheet.setColumnWidth((short) i, 5000);
		}
		// 合并单元格
		Region region = new Region((short) 0, (short) 0, (short) 1, (short) (vajoinedact.length - 1));
		sheet.addMergedRegion(region);
		// 创建预备居中格式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle = wb.createCellStyle();
		// 设置格式居中
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);
		cellStyleforFontStyle.setAlignment(cellStyle.ALIGN_CENTER);
		// 创建字体
		HSSFFont font = wb.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 合并字体到居中大字体格式
		cellStyleforFontStyle.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		cells[0].setCellValue(actDate + "  " + actName + "  " + "参与信息");
		// 应用格式
		cells[0].setCellStyle(cellStyleforFontStyle);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[vajoinedact.length];
		for (int i = 0; i < cell.length; i++) {
			cell[i] = row.createCell(i);
			cell[i].setCellValue(vajoinedact[i]);
			cell[i].setCellStyle(cellStyle);
		}
		if (list != null) {
			Map<String, Object> teachers = StoreData.getTeachertranslate();
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[vajoinedact.length];
				for (int j = 0; j < vajoinedact.length; j++) {
					cell[j] = row.createCell(j);
				}
				cell[0].setCellValue(list.get(i).getId().getVacollectiveActivitiesPublish().getActPubId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(list.get(i).getId().getVacollectiveActivitiesPublish().getVacollectiveAct()
						.getActName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(list.get(i).getId().getTeacher().getTeacherId());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue((String) teachers.get(list.get(i).getId().getTeacher().getTeacherId()));
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(list.get(i).getScore());
				cell[4].setCellStyle(cellStyle);
				if (list.get(i).getAspareTire().equals("0")) {
					cell[5].setCellValue("待审核");
				}else if (list.get(i).getAspareTire().equals("1")) {
					cell[5].setCellValue("审核通过");
				}else if (list.get(i).getAspareTire().equals("2")) {
					cell[5].setCellValue("审核不通过");
				}
				cell[5].setCellStyle(cellStyle);
				;
			}
		}
		
		VacollectiveActDAO vacollectiveActDAO = new VacollectiveActDAO();
		// 设置列宽
		for (int i = 0; i < vaunjoinedact.length; i++) {
			sheet2.setColumnWidth((short) i, 5000);
		}
		// 合并单元格
		Region region1 = new Region((short) 0, (short) 0, (short) 1, (short) (vaunjoinedact.length - 1));
		sheet2.addMergedRegion(region1);
		// 创建预备居中格式
		HSSFCellStyle cellStyle1 = wb.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle1 = wb.createCellStyle();
		// 设置格式居中
		cellStyle1.setAlignment(cellStyle1.ALIGN_CENTER);
		cellStyleforFontStyle1.setAlignment(cellStyle1.ALIGN_CENTER);
		// 创建字体
		HSSFFont font1 = wb.createFont();
		// 设置字体大小
		font1.setFontHeightInPoints((short) 14);
		// 合并字体到居中大字体格式
		cellStyleforFontStyle1.setFont(font1);
		int rownum1 = 0;
		HSSFRow row1 = sheet2.createRow((short) rownum1++);
		HSSFCell cells1[] = new HSSFCell[1];
		cells1[0] = row1.createCell(0);
		cells1[0].setCellValue(actDate + "  " + actName + "  " + "缺席信息");
		// 应用格式
		cells1[0].setCellStyle(cellStyleforFontStyle1);
		rownum1++;
		row1 = sheet2.createRow((short) rownum1++);
		HSSFCell cell1[] = new HSSFCell[vaunjoinedact.length];
		for (int i = 0; i < cell1.length; i++) {
			cell1[i] = row1.createCell(i);
			cell1[i].setCellValue(vaunjoinedact[i]);
			cell1[i].setCellStyle(cellStyle1);
		}
		if (list2 != null) {
			Map<String, Object> teachers = StoreData.getTeachertranslate();
			for (int i = 0; i < list2.size(); i++) {
				row1 = sheet2.createRow(rownum1++);
				cell1 = new HSSFCell[vaunjoinedact.length];
				for (int j = 0; j < vaunjoinedact.length; j++) {
					cell1[j] = row1.createCell(j);
				}
				cell1[0].setCellValue(list2.get(i).getActId());
				cell1[0].setCellStyle(cellStyle1);
				cell1[1].setCellValue(vacollectiveActDAO.findById(list2.get(i).getActId()).getActName());
				cell1[1].setCellStyle(cellStyle1);
				cell1[2].setCellValue(list2.get(i).getTeacherId());
				cell1[2].setCellStyle(cellStyle1);
				cell1[3].setCellValue((String) teachers.get(list2.get(i).getTeacherId()));
				cell1[3].setCellStyle(cellStyle1);
				cell1[4].setCellValue(list2.get(i).getResultscore());
				cell1[4].setCellStyle(cellStyle1);
				if (list2.get(i).getAsparetire().equals("0")) {
					cell1[5].setCellValue("待审核");
				}else if (list2.get(i).getAsparetire().equals("1")) {
					cell1[5].setCellValue("审核通过");
				}else if (list2.get(i).getAsparetire().equals("2")) {
					cell1[5].setCellValue("审核不通过");
				}
				cell1[5].setCellStyle(cellStyle1);
			}
		}
		return wb;
	}
}
