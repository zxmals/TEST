package com.nuaa.ec.teachingData.exportData;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.TffineCourseConstructionPerformance;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.StoreData;

public class SummerAndInternationCourseConstructionExcel {
	/**
	 * function：导出暑期与国际课程建设数据
	 * @param ths 导出数据的字段
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TfsummerCourseInternationalConstructionPerformanceUnionTfterm> tfSamerCorsIntnashnlConsPerfList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("暑期课程与或国际课程建设["+researchLabName+"]");
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
		if(tfSamerCorsIntnashnlConsPerfList!=null){
			Map<String,String> terms=EntityUtil.getTermList();
			for(int i=0;i<tfSamerCorsIntnashnlConsPerfList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * {"项目编号","项目名称","项目等级","数量单位","当前教师工号","当前教师姓名","分数","学期"};
 */
				cell[0].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getProjectId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getProjectName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getTfsummerCourseInternationalConstructionLevel().getProjectLevel());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getQuantityUnit());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getTeacher().getTeacherId());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getTeacher().getTeacherName());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getScore());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(terms.get(tfSamerCorsIntnashnlConsPerfList.get(i).getSummerCourseInterConsPerf().getTermId()));
				cell[7].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
