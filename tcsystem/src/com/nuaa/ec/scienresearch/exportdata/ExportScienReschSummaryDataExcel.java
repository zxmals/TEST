package com.nuaa.ec.scienresearch.exportdata;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.summaryDataModel.ScientificResearchModuleData;
import com.nuaa.ec.utils.StoreData;
import com.nuaa.ec.utils.stringstore;

@SuppressWarnings("deprecation")
public class ExportScienReschSummaryDataExcel {
	public static HSSFWorkbook generateSummaryDataExcel(List<ScientificResearchModuleData> scienReschModlDataList,String researchLabId,String foredate,String afterdate){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(foredate+"-"+afterdate);
		//设置列宽
		String[] modules = stringstore.scienReschModules;
		int moduleCount=stringstore.scienReschModules.length;
		for(int i=0;i<=moduleCount*2;i++){
			sheet.setColumnWidth((short) i, 4000);
		}
		for(int i=1;i<=moduleCount*2-1;i+=2){
			CellRangeAddress cra = new CellRangeAddress(2,2,i,i+1);
			sheet.addMergedRegion(cra);
		}
//  合并单元格  org.apache.poi.hssf.util.Region(short rowFrom,short colFrom,short rowTo,short colTo)
		org.apache.poi.hssf.util.Region reg = new org.apache.poi.hssf.util.Region((short)0,(short)0,(short)1,(short)(10));
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
		if(researchLabId.trim().equals("allResearchLab")){
			cells[0].setCellValue("所有研究所");
		}else{
			cells[0].setCellValue(StoreData.getResearchLabMap().get(researchLabId));
		}
		//应用格式
		cells[0].setCellStyle(cellStyleforFonts);
		rownum++;
		row = sheet.createRow((short) rownum++);
		HSSFCell cell[] = new HSSFCell[19];
		int k=0;
		cell[0] = row.createCell(0);
		cell[0].setCellValue("研究所");
		cell[0].setCellStyle(cellStyle);
		for(int i=1;i<=17;i+=2){
			cell[i] = row.createCell(i);
			cell[i].setCellValue(modules[k++]);
			cell[i].setCellStyle(cellStyle);
		}
		if(scienReschModlDataList!=null){
			for(int i=0;i<scienReschModlDataList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[19];
				for(int p=0;p<cell.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * {"科研项目","科研奖励","学术著作","参加学术会议","入选人才工程","邀请专家讲学","期刊论文","主承办学术会议","总计（总/均）"};
 */
				cell[0].setCellValue(scienReschModlDataList.get(i).getResearchLabName());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(scienReschModlDataList.get(i).getScientificResearchProData().getSum());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(scienReschModlDataList.get(i).getScientificResearchProData().getAvg());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(scienReschModlDataList.get(i).getScientificRewardData().getSum());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(scienReschModlDataList.get(i).getScientificRewardData().getAvg());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(scienReschModlDataList.get(i).getAcademicWorkData().getAvg());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue(scienReschModlDataList.get(i).getAcademicWorkData().getAvg());
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(scienReschModlDataList.get(i).getJoinAcademicMeetingData().getSum());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(scienReschModlDataList.get(i).getJoinAcademicMeetingData().getAvg());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(scienReschModlDataList.get(i).getTalentProData().getSum());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(scienReschModlDataList.get(i).getTalentProData().getAvg());
				cell[10].setCellStyle(cellStyle);
				cell[11].setCellValue(scienReschModlDataList.get(i).getInviteExpertSpeechData().getSum());
				cell[11].setCellStyle(cellStyle);
				cell[12].setCellValue(scienReschModlDataList.get(i).getInviteExpertSpeechData().getAvg());
				cell[12].setCellStyle(cellStyle);
				cell[13].setCellValue(scienReschModlDataList.get(i).getPeriodicalData().getSum());
				cell[13].setCellStyle(cellStyle);
				cell[14].setCellValue(scienReschModlDataList.get(i).getPeriodicalData().getAvg());
				cell[14].setCellStyle(cellStyle);
				cell[15].setCellValue(scienReschModlDataList.get(i).getUndertakeAcademicMeetingData().getSum());
				cell[15].setCellStyle(cellStyle);
				cell[16].setCellValue(scienReschModlDataList.get(i).getUndertakeAcademicMeetingData().getAvg());
				cell[16].setCellStyle(cellStyle);
				cell[17].setCellValue(scienReschModlDataList.get(i).getSum());
				cell[17].setCellStyle(cellStyle);
				cell[18].setCellValue(scienReschModlDataList.get(i).getAvg());
				cell[18].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
