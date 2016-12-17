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
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;
import com.nuaa.ec.utils.StoreData;

public class EnterpriseWorkStationExcel {
	/**
	 * function：导出企业工作站和联合培养基地数据
	 * @param ths 导出数据的字段
	 * @param foredate 下限日期
	 * @param afterdate 上限日期
	 * @return HSSFWorkbook
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook  generateExcel(String[] ths,List<TfenterpriseWorkstationTrainingBaseConstructionPerformance> tfenterPrsList,String researchLabName,String foredate,String afterdate){
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
		cells[0].setCellValue("企业工作站和联合培养基地["+researchLabName+"]");
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
		if(tfenterPrsList!=null){
			Map<String,Object> teachers = StoreData.getTeachertranslate();
			for(int i=0;i<tfenterPrsList.size();i++){
				row = sheet.createRow(rownum++);
				cell = new HSSFCell[ths.length];
				for(int p=0;p<ths.length;p++){
					cell[p] = row.createCell(p);
				}
/**
 * 导出字段：
 * {"项目编号","项目名称","建设水平","项目总分","数量单位","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
 */
				cell[0].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getProjectId());
				cell[0].setCellStyle(cellStyle);
				cell[1].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getProjectName());
				cell[1].setCellStyle(cellStyle);
				cell[2].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getTfenterpriseWorkstationTrainingbaseConstructionLevel().getTrainingConstruLevel());
				cell[2].setCellStyle(cellStyle);
				cell[3].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getProjectSumScore());
				cell[3].setCellStyle(cellStyle);
				cell[4].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getQuantityUnit());
				cell[4].setCellStyle(cellStyle);
				cell[5].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getChargePersonId());
				cell[5].setCellStyle(cellStyle);
				cell[6].setCellValue((String)teachers.get(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getChargePersonId()));
				cell[6].setCellStyle(cellStyle);
				cell[7].setCellValue(tfenterPrsList.get(i).getTeacher().getTeacherId());
				cell[7].setCellStyle(cellStyle);
				cell[8].setCellValue(tfenterPrsList.get(i).getTeacher().getTeacherName());
				cell[8].setCellStyle(cellStyle);
				cell[9].setCellValue(tfenterPrsList.get(i).getSelfUndertakeTask().getUndertakeTaskName());
				cell[9].setCellStyle(cellStyle);
				cell[10].setCellValue(tfenterPrsList.get(i).getSingleScore());
				cell[10].setCellStyle(cellStyle);
				cell[11].setCellValue(tfenterPrsList.get(i).getTfenterpriseWorkstationTrainingBaseConstructionProject().getTfterm().getTerm());
				cell[11].setCellStyle(cellStyle);
			}
		}
		return wb;
	}
}
