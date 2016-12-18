package com.nuaa.ec.scienresearch.exportdata;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.utils.StoreData;

public class ScientificResearchProjectExcel {

	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TeacherAndscientificResearchProject> TAScienReschProjktList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("科研项目["+researchLabName+"]");
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
		Map<String,Object> teachers =StoreData.getTeachertranslate();
		if(TAScienReschProjktList!=null){
			for(int i=0;i<TAScienReschProjktList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
//{"科研项目编号","科研项目名称","项目类型","项目原编号","项目来源","立项年份","项目总金/万","当年到款/万","负责人ID","负责人姓名","教师编号","教师姓名","本人承担任务","基础分数"};				
				cell[0].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getSrprojectId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getSrpname());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getProjectType().getProjectTpName());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getProjectNumber());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getProjectSource());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getAdmitedProjectYear());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getSumFunds());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(TAScienReschProjktList.get(i).getYearFunds());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(TAScienReschProjktList.get(i).getScientificResearchProject().getChargePersonId());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue((String)teachers.get(TAScienReschProjktList.get(i).getScientificResearchProject().getChargePersonId()));
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(TAScienReschProjktList.get(i).getTeacher().getTeacherId());
				cell[10].setCellStyle(cellStyle);
				cell[11].setCellValue(TAScienReschProjktList.get(i).getTeacher().getTeacherName());
				cell[11].setCellStyle(cellStyle);
				cell[12].setCellValue(TAScienReschProjktList.get(i).getSelfUndertakeTask().getUndertakeTaskName());
				cell[12].setCellStyle(cellStyle);
				cell[13].setCellValue(TAScienReschProjktList.get(i).getFinalScore());
				cell[13].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
