package com.nuaa.ec.va.exportdata;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VacollectiveActivitiesPublish;
import com.nuaa.ec.utils.StoreData;

public class VaActListExcel {
	public static HSSFWorkbook generateExcel(String[] ths,List<VacollectiveActivitiesPublish> list,String departmentName,String foredate,String afterdate){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(foredate + "-" + afterdate);
		//设置列宽
		for (int i = 0; i < ths.length; i++) {
			sheet.setColumnWidth((short)i, 5000);
		}
		//合并单元格
		Region region = new Region((short)0, (short)0, (short)1, (short)(ths.length - 1));
		sheet.addMergedRegion(region);
		//创建预备居中格式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		//创建预备居中大字体格式
		HSSFCellStyle cellStyleforFontStyle = wb.createCellStyle();
		//设置格式居中
		cellStyle.setAlignment(cellStyle.ALIGN_CENTER);
		cellStyleforFontStyle.setAlignment(cellStyle.ALIGN_CENTER);
		//创建字体
		HSSFFont font = wb.createFont();
		//设置字体大小
		font.setFontHeightInPoints((short)14);
		//合并字体到居中大字体格式
		cellStyleforFontStyle.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short)rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		cells[0].setCellValue(departmentName +foredate + "至"+ afterdate +"活动列表");
		//应用格式
		cells[0].setCellStyle(cellStyleforFontStyle);
		rownum++;
		row = sheet.createRow((short)rownum++);
		HSSFCell cell[] = new HSSFCell[ths.length];
		for (int i = 0; i < cell.length; i++) {
			cell[i] = row.createCell(i);
			cell[i].setCellValue(ths[i]);
			cell[i].setCellStyle(cellStyle);
		}
		if (list!=null) {
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
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(list.get(i).getVacollectiveAct().getAttendee());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(list.get(i).getActDate());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(list.get(i).getTeacherId());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue((String)teachers.get(list.get(i).getTeacherId()));
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(list.get(i).getVacollectiveAct().getScore());
				cell[7].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
