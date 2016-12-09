package com.nuaa.ec.scienresearch.exportdata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.model.PeriodicalPaperInfoUnionModel;

public class PeriodicalPaperExcel {

	public static HSSFWorkbook  generateExcel(String[] ths,List<PeriodicalPaperInfoUnionModel> tapli,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("期刊论文["+researchLabName+"]");
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
		if(tapli!=null){
			for(int i=0;i<tapli.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
				cell[0].setCellValue(tapli.get(i).getPeriodicalPapers().getPpid());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(tapli.get(i).getPeriodicalPapers().getFirstAuthor());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(tapli.get(i).getPeriodicalPapers().getSecondAuthor());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(tapli.get(i).getPeriodicalPapers().getThesisTitle());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(tapli.get(i).getPeriodicalPapers().getPeriodical().getPeriodicalName());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(tapli.get(i).getPeriodicalPapers().getYear());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(tapli.get(i).getPeriodicalPapers().getFile());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(tapli.get(i).getPeriodicalPapers().getPhase());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(tapli.get(i).getPeriodicalPapers().getDescribe());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(tapli.get(i).getTAPeriodical().getTeacher().getTeacherId());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(tapli.get(i).getTAPeriodical().getTeacher().getTeacherName());
				cell[10].setCellStyle(cellStyle);
				cell[11].setCellValue(tapli.get(i).getTAPeriodical().getFinalScore());
				cell[11].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
