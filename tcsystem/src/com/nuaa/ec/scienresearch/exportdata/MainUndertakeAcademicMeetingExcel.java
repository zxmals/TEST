package com.nuaa.ec.scienresearch.exportdata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;

public class MainUndertakeAcademicMeetingExcel {

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TeacherAndmainUndertakeAcademicMeeting> TAMnUndrtkAkdmkMetingList,String researchLabName,String foredate,String afterdate){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(foredate+"-"+afterdate);
		//设置列宽
		for(int i=0;i<ths.length;i++){
			sheet.setColumnWidth((short) i, 5000);
		}
//  合并单元格  org.apache.poi.hssf.util.Region(short rowFrom,short colFrom,short rowTo,short colTo)
		org.apache.poi.hssf.util.Region reg = new org.apache.poi.hssf.util.Region((short)0,(short)0,(short)1,(short)(ths.length-1));
		sheet.addMergedRegion(reg);
		//创建预备居中格式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		//创建预备居中大字体格式
		HSSFCellStyle cellStyleforFonts = wb.createCellStyle();
		//设置格式居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleforFonts.setAlignment(CellStyle.ALIGN_CENTER);
		//创建字体
		HSSFFont font = wb.createFont(); 
		//设置字体大小
		font.setFontHeightInPoints((short)14);
		//合并字体到 居中大字体格式
		cellStyleforFonts.setFont(font);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cells[] = new HSSFCell[1];
		cells[0] = row.createCell(0);
		cells[0].setCellValue("主承办学术会议["+researchLabName+"]");
		//应用格式
		cells[0].setCellStyle(cellStyleforFonts);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[ths.length];
		for(int i=0;i<ths.length;i++){
			cell[i] = row.createCell(i);
			cell[i].setCellValue(ths[i]);
			cell[i].setCellStyle(cellStyle);
		}
		if(TAMnUndrtkAkdmkMetingList!=null){
			for(int i=0;i<TAMnUndrtkAkdmkMetingList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
//				{"主承办学术会议编号","主承办学术会议名称","会议类型","会议地点","会议时间","负责人ID","负责人姓名","教师编号","教师姓名","本人承担任务","基础分数"};				
				cell[0].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getAcaMeetingId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getAcaMeetingName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getMainUndertakeAcademicMeetingType().getAcaMeetType());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getMainUndertakeAcademicMeetingPlace().getAcaMeetPlace());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getMeetingdate());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getChargePersonId());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getMainUndertakeAcademicMeeting().getChargePerson());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getTeacher().getTeacherId());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getTeacher().getTeacherName());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getSelfUndertakeTask().getUndertakeTaskName());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(TAMnUndrtkAkdmkMetingList.get(i).getFinalScore());
				cell[10].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
