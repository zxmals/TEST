package com.nuaa.ec.scienresearch.exportdata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.model.TeacherAndacademicWork;

public class AcademicWorkExcel {
	/**
	 * function：导出学术著作数据
	 * @param ths 导出数据的字段
	 * @param TAakdemicWorkList Excel表对应的数据实体类
	 * @param researchLabName 研究所名称
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TeacherAndacademicWork> TAakdemicWorkList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("学术著作["+researchLabName+"]");
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
		if(TAakdemicWorkList!=null){
			for(int i=0;i<TAakdemicWorkList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * 	"出版社名称","学术著作编号","第一作者","学术著作名称","出版日期","ISBN","字数","是否其他作者参与","教师编号","教师姓名","本人承担任务","	基础分数"
 */
				cell[0].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getAcaworkId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getFirstAuthor());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getWorkName());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getPublishDate());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getPublishClub().getPublishClubName());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getIsbn());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getWordNumber());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(TAakdemicWorkList.get(i).getAcademicWork().getOtherAuthorJoin());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(TAakdemicWorkList.get(i).getTeacher().getTeacherId());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(TAakdemicWorkList.get(i).getTeacher().getTeacherName());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(TAakdemicWorkList.get(i).getSelfUndertakeTask().getUndertakeTaskName());
				cell[10].setCellStyle(cellStyle);
				cell[11].setCellValue(TAakdemicWorkList.get(i).getFinalScore());
				cell[11].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
