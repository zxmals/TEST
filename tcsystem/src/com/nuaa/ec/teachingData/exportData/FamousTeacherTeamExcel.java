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
import com.nuaa.ec.model.TffamousTeacherTeamPerformance;
import com.nuaa.ec.utils.StoreData;

public class FamousTeacherTeamExcel {
	/**
	 * function：导出教学名师和教师团队指导数据
	 * @param ths 导出数据的字段
	 * @param departmentName 系名称
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TffamousTeacherTeamPerformance> tfFamousTeacherTeamPerfList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("教学名师和教学团队["+researchLabName+"]");
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
		if(tfFamousTeacherTeamPerfList!=null){
			Map<String,Object> teachers=StoreData.getTeachertranslate();
			for(int i=0;i<tfFamousTeacherTeamPerfList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * {"教学名师与教学团队编号","获奖级别","项目总分","本人承担任务","负责人工号","负责人姓名","当前教师编号","当前教师姓名","教师绩效"};
 */
				cell[0].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getTeacherTeamPerformanceId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getTffamousTeacherTeamRewadLevel().getTeachRewardLevelName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getProjectSumScore());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(tfFamousTeacherTeamPerfList.get(i).getSelfUndertakeTask().getUndertakeTaskName());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getChargePersonId());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue((String)teachers.get(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getChargePersonId()));
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTeacher().getTeacherId());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTeacher().getTeacherName());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(tfFamousTeacherTeamPerfList.get(i).getSingelScore());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(tfFamousTeacherTeamPerfList.get(i).getTffamousTeacherTeamProject().getTfterm().getTerm());
				cell[9].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
