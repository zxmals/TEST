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
import com.nuaa.ec.model.TeacherAndscientificResearchReward;

public class ScientificResearchRewardExcel {
	/**
	 * function：导出科研项目奖励数据
	 * @param ths 导出数据的字段
	 * @param TAScienReschRewodList Excel表对应的数据实体类
	 * @param researchLabName 研究所名称
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook  generateExcel(String[] ths,List<TeacherAndscientificResearchReward> TAScienReschRewodList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("科研项目奖励["+researchLabName+"]");
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
		if(TAScienReschRewodList!=null){
			for(int i=0;i<TAScienReschRewodList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * 	{"","科研奖励编号","获奖级别","获奖类别","科研奖励名称","授奖部门","获奖日期","获奖总人数","教师编号","教师姓名","本人排名","基础分数"};
 */
				cell[0].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getSrrewardId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getRewardLevel().getRewardLevelName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getRewardType().getRewardTypeName());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getSrrewardName());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getAwardDepartment());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getRewardDate());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(TAScienReschRewodList.get(i).getScientificResearchReward().getRewardTotalPeople());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(TAScienReschRewodList.get(i).getTeacher().getTeacherId());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(TAScienReschRewodList.get(i).getTeacher().getTeacherName());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(TAScienReschRewodList.get(i).getSelfRanking());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(TAScienReschRewodList.get(i).getFinalScore());
				cell[10].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
