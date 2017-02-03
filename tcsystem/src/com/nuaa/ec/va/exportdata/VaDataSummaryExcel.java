package com.nuaa.ec.va.exportdata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.action.VaActDataSum;
import com.nuaa.ec.utils.StoreData;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

public class VaDataSummaryExcel {
	@SuppressWarnings("unused")
	public static HSSFWorkbook genereateVasummaryDataExcel(List<VaActDataSum> vaActDataSumList, String departmentId,
			String foredate, String afterdate) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(foredate + "-" + afterdate);
		String[] modules = stringstore.vasummary;
		for (int i = 0; i <= modules.length * 2; i++) {
			sheet.setColumnWidth(i, 4000);
		}
		for (int i = 1; i <= modules.length * 2 - 1; i += 2) {
			CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, i, i + 1);
			sheet.addMergedRegion(cellRangeAddress);
		}
		Region region = new Region((short) 0, (short) 0, (short) 1, (short) (10));
		sheet.addMergedRegion(region);
		// 创建预备居中格式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 创建预备居中大字体格式
		HSSFCellStyle cellStyleforFonts = workbook.createCellStyle();
		// 设置格式居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleforFonts.setAlignment(CellStyle.ALIGN_CENTER);
		// 创建字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 14);
		// 合并字体到 居中大字体格式
		cellStyleforFonts.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		if (departmentId.trim().equals("allDepartment")) {
			cells[0].setCellValue("所有系");
		} else {
			cells[0].setCellValue(StoreData.getDepartmentMap().get(departmentId));
		}
		// 应用格式
		cells[0].setCellStyle(cellStyleforFonts);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[7];
		int k = 0;
		cell[0] = row.createCell(0);
		cell[0].setCellValue("系别");
		cell[0].setCellStyle(cellStyle);
		for (int i = 1; i <= 5; i += 2) {
			cell[i] = row.createCell(i);
			cell[i].setCellValue(modules[k++]);
			cell[i].setCellStyle(cellStyle);
		}
		if (vaActDataSumList != null) {
			for (int i = 0; i < vaActDataSumList.size(); i++) {
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[7];
				for (int j = 0; j < cell.length; j++) {
					cell[j] = row.createCell(j);
				}
				/**
				 * 导出字段 "活动参与（总/均）","活动缺席（总/均）","总计（总/均）"
				 */
				cell[0].setCellValue(vaActDataSumList.get(i).getDepartmentName());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(vaActDataSumList.get(i).getTeacherJoinedData().getSum());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(vaActDataSumList.get(i).getTeacherJoinedData().getAverage());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(vaActDataSumList.get(i).getUnjoinedActData().getSum());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(vaActDataSumList.get(i).getUnjoinedActData().getAverage());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(vaActDataSumList.get(i).getSum());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(vaActDataSumList.get(i).getAverage());
				cell[6].setCellStyle(cellStyle);
			}
		}
		ActionContext.getContext().getSession().remove("VaActSummaryData");
		return workbook;
	}
}
