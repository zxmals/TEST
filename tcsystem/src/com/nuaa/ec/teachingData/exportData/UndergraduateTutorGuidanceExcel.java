package com.nuaa.ec.teachingData.exportData;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.TfundergraduateTutorGuidancePerformanceUnionTfterm;

public class UndergraduateTutorGuidanceExcel {
	/**
	 * function：导出本科生导师指导数据
	 * @param ths 导出数据的字段
	 * @param departmentName 系名称
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TfundergraduateTutorGuidancePerformanceUnionTfterm> tfUndergraduateTutorGuidList,String researchLabName,String foredate,String afterdate){
		HSSFWorkbook wb = new HSSFWorkbook();
		TftermDAO tftermDAO=new TftermDAO();
		HSSFSheet sheet = wb.createSheet(tftermDAO.findById(foredate).getTerm()+"-"+tftermDAO.findById(afterdate).getTerm());
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
		cells[0].setCellValue("本科生导师指导["+researchLabName+"]");
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
		if(tfUndergraduateTutorGuidList!=null){
			for(int i=0;i<tfUndergraduateTutorGuidList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * {"指导编号","学生数量","指导年数","当前教师工号","当前教师姓名","教师绩效"};
 */
				cell[0].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getTfundergraduateTutorGuidanceCache().getGuidanceId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getStudentQuantity());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getYears());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getTeacher().getTeacherId());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getTeacher().getTeacherName());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(tfUndergraduateTutorGuidList.get(i).getUndergraduateTutorGuidancePerformance().getFinalScore());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(tfUndergraduateTutorGuidList.get(i).getCurrentTerm().getTerm());
				cell[6].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
