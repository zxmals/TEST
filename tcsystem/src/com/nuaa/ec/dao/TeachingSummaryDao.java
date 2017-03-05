package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.teachingData.exportData.TeachingCompetitionExcel;
import com.nuaa.ec.utils.E_SummaryOfTeacher;
import com.nuaa.ec.utils.E_SummaryOfTeaching;
import com.nuaa.ec.utils.Statistics_asist;

public class TeachingSummaryDao {

	private TfclassTeachPefromanceDAO classteachdao = new TfclassTeachPefromanceDAO();
	private TfdegreeThesisGuidancePerformanceDAO paperguidancedao = new TfdegreeThesisGuidancePerformanceDAO();
	private TfteachingCompetitionPerformanceDAO teachingcompetedao = new TfteachingCompetitionPerformanceDAO();
	private TfteachingAbilityImprovePerformanceDAO teachingabilitydao = new TfteachingAbilityImprovePerformanceDAO();
	private TffamousTeacherTeamPerformanceDAO famousteachteamdao = new TffamousTeacherTeamPerformanceDAO();
	private TfteachingRearchPerformanceDAO teachingresearchdao = new TfteachingRearchPerformanceDAO();
	private TfteachingPaperPerformanceDAO teachingpaperdao = new TfteachingPaperPerformanceDAO();
	private TfteachingAchievementPerformanceDAO teachingachievedao = new TfteachingAchievementPerformanceDAO();
	private TftextbookConstructionPerformanceDAO textbookdao = new TftextbookConstructionPerformanceDAO();
	private TffineCourseConstructionPerformanceDAO finecoursedao = new TffineCourseConstructionPerformanceDAO();
	private TfprofessionalProjectDeclarePerformanceDAO professionprojectdao = new TfprofessionalProjectDeclarePerformanceDAO();
	private TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO firmworkstationdao = new TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO();
	private TfsummerCourseInternationalConstructionPerformanceDAO summercoursedao = new TfsummerCourseInternationalConstructionPerformanceDAO();
	private TfpracticeInnovationGuidePerformanceDAO practiceinnovationdao = new TfpracticeInnovationGuidePerformanceDAO();
	private TfstudentCompetitionGuidancePerformanceDAO studentscompetdao = new TfstudentCompetitionGuidancePerformanceDAO();
	private TfjoinStudentActivityPerformanceDAO studentactivitydao = new TfjoinStudentActivityPerformanceDAO();
	private TfundergraduateTutorGuidancePerformanceDAO undergraduateguidancedao = new TfundergraduateTutorGuidancePerformanceDAO();
	private TfoffCampusPracticeGuidancePerformanceDAO offcampusdao = new TfoffCampusPracticeGuidancePerformanceDAO();
	
	public List<E_SummaryOfTeacher> getTeacherSUM(String foreterm,String afterterm,String teacherId){
		TeacherDAO teacherdao = new TeacherDAO();
		List<Teacher> teacherli = teacherdao.findTeacherByFuzzyQuery(teacherId);
		List<E_SummaryOfTeacher> sumteacherli = new ArrayList<E_SummaryOfTeacher>();
		E_SummaryOfTeacher sumteacher = null;
		double sum = 0;
		for(int i=0;i<teacherli.size();i++){
			sumteacher = new E_SummaryOfTeacher();
			sum = 0;
			
			sumteacher.setTeacher(teacherli.get(i));
			
			sumteacher.setClassTeaching(classteachdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getClassTeaching().getSum();
			
			sumteacher.setDegreeGuidance(paperguidancedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getDegreeGuidance().getSum();
			
			sumteacher.setFamousTeacherTeam(famousteachteamdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getFamousTeacherTeam().getSum();
			
			sumteacher.setFineCourse(finecoursedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getFineCourse().getSum();
			
			sumteacher.setFirmWorkstationTrainingBase(firmworkstationdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getFirmWorkstationTrainingBase().getSum();
			
			sumteacher.setOff_campusPracticeGuidance(offcampusdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getOff_campusPracticeGuidance().getSum();
			
			sumteacher.setPracticeInnovationGuidance(practiceinnovationdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getPracticeInnovationGuidance().getSum();
			
			sumteacher.setProfessionalProjectConstruction(professionprojectdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getProfessionalProjectConstruction().getSum();
			
			sumteacher.setStudentsActivity(studentactivitydao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getStudentsActivity().getSum();
			
			sumteacher.setStudentsCompetitionGuidance(studentscompetdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getStudentsCompetitionGuidance().getSum();
			
			sumteacher.setSummerInternationalCourse(summercoursedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getSummerInternationalCourse().getSum();
			
			sumteacher.setTeachingAbilityImprove(teachingabilitydao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTeachingAbilityImprove().getSum();
			
			sumteacher.setTeachingAchievement(teachingachievedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTeachingAchievement().getSum();
			
			sumteacher.setTeachingCompetition(teachingcompetedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTeachingCompetition().getSum();
			
			sumteacher.setTeachingPaper(teachingpaperdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTeachingPaper().getSum();
			
			sumteacher.setTeachingResearch(teachingresearchdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTeachingResearch().getSum();
			
			sumteacher.setTextBookConstruction(textbookdao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getTextBookConstruction().getSum();
			
			sumteacher.setUndergraduateGuidance(undergraduateguidancedao.getSAperson(foreterm, afterterm, teacherli.get(i).getTeacherId()));
			sum += sumteacher.getUndergraduateGuidance().getSum();
			
			sumteacher.setSumUP(new Statistics_asist(sum, (double)((int)((sum/18)/0.01))*0.01));
			
			sumteacherli.add(sumteacher);
		}
		if(sumteacherli.size()>0){
			return sumteacherli;
		}else{
			return null;
		}
	}
	
	/*
	 * 根据所有系获取汇总信息
	 */
	public List<E_SummaryOfTeaching> getAllSum(String foreterm,String afterterm){
		@SuppressWarnings("unchecked")
		List<Department> departsli = new DepartmentDAO().findAll();
		List<E_SummaryOfTeaching> esotli = new ArrayList<E_SummaryOfTeaching>();
		E_SummaryOfTeaching es = null;
		double summarys[] = new double[18];
		for(int i=0;i<departsli.size();i++){
			double sum = 0;
			es = new E_SummaryOfTeaching();
			es.setDeparts(departsli.get(i));
			
			es.setClassTeaching(classteachdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getClassTeaching().getSum();
			summarys[0] += es.getClassTeaching().getSum();
			
			es.setDegreeGuidance(paperguidancedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getDegreeGuidance().getSum();
			summarys[1] += es.getDegreeGuidance().getSum();
			
			es.setFamousTeacherTeam(famousteachteamdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFamousTeacherTeam().getSum();
			summarys[2] += es.getFamousTeacherTeam().getSum();
			
			es.setFineCourse(finecoursedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFineCourse().getSum();
			summarys[3] += es.getFineCourse().getSum();
			
			es.setFirmWorkstationTrainingBase(firmworkstationdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getFirmWorkstationTrainingBase().getSum();
			summarys[4] += es.getFirmWorkstationTrainingBase().getSum();
			
			es.setOff_campusPracticeGuidance(offcampusdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getOff_campusPracticeGuidance().getSum();
			summarys[5] += es.getOff_campusPracticeGuidance().getSum();
			
			es.setPracticeInnovationGuidance(practiceinnovationdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getPracticeInnovationGuidance().getSum();
			summarys[6] += es.getPracticeInnovationGuidance().getSum();
			
			es.setProfessionalProjectConstruction(professionprojectdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getProfessionalProjectConstruction().getSum();
			summarys[7] += es.getProfessionalProjectConstruction().getSum();
			
			es.setStudentsActivity(studentactivitydao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getStudentsActivity().getSum();
			summarys[8] += es.getStudentsActivity().getSum();
			
			es.setStudentsCompetitionGuidance(studentscompetdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getStudentsCompetitionGuidance().getSum();
			summarys[9] += es.getStudentsCompetitionGuidance().getSum();
			
			es.setSummerInternationalCourse(summercoursedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getSummerInternationalCourse().getSum();
			summarys[10] += es.getSummerInternationalCourse().getSum();
			
			es.setTeachingAbilityImprove(teachingabilitydao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingAbilityImprove().getSum();
			summarys[11] += es.getTeachingAbilityImprove().getSum();
			
			es.setTeachingAchievement(teachingachievedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingAchievement().getSum();
			summarys[12] += es.getTeachingAchievement().getSum();
			
			es.setTeachingCompetition(teachingcompetedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingCompetition().getSum();
			summarys[13] += es.getTeachingCompetition().getSum();
			
			es.setTeachingPaper(teachingpaperdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingPaper().getSum();
			summarys[14] += es.getTeachingPaper().getSum();
			
			es.setTeachingResearch(teachingresearchdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTeachingResearch().getSum();
			summarys[15] += es.getTeachingResearch().getSum();
			
			es.setTextBookConstruction(textbookdao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getTextBookConstruction().getSum();
			summarys[16] += es.getTextBookConstruction().getSum();
			
			es.setUndergraduateGuidance(undergraduateguidancedao.getSA(foreterm, afterterm, departsli.get(i)));
			sum += es.getUndergraduateGuidance().getSum();
			summarys[17] += es.getUndergraduateGuidance().getSum();
			
			es.setSumUP(new Statistics_asist(sum,(double)((int)((sum/18)/0.01))*0.01));
			esotli.add(es);
		}
		E_SummaryOfTeaching eso = new E_SummaryOfTeaching();
		eso.setDeparts(new Department("", "合计", "", "", "", null));
		double sum = 0;
		eso.setClassTeaching(new Statistics_asist(summarys[0], (double)((int)((summarys[0]/departsli.size())/0.01))*0.01));
		sum += eso.getClassTeaching().getSum();
		eso.setDegreeGuidance(new Statistics_asist(summarys[1], (double)((int)((summarys[1]/departsli.size())/0.01))*0.01));
		sum += eso.getDegreeGuidance().getSum();
		eso.setFamousTeacherTeam(new Statistics_asist(summarys[2], (double)((int)((summarys[2]/departsli.size())/0.01))*0.01));
		sum += eso.getFamousTeacherTeam().getSum();
		eso.setFineCourse(new Statistics_asist(summarys[3], (double)((int)((summarys[3]/departsli.size())/0.01))*0.01));
		sum += eso.getFineCourse().getSum();
		eso.setFirmWorkstationTrainingBase(new Statistics_asist(summarys[4], (double)((int)((summarys[4]/departsli.size())/0.01))*0.01));
		sum += eso.getFirmWorkstationTrainingBase().getSum();
		eso.setOff_campusPracticeGuidance(new Statistics_asist(summarys[5], (double)((int)((summarys[5]/departsli.size())/0.01))*0.01));
		sum += eso.getOff_campusPracticeGuidance().getSum();
		eso.setPracticeInnovationGuidance(new Statistics_asist(summarys[6], (double)((int)((summarys[6]/departsli.size())/0.01))*0.01));
		sum += eso.getPracticeInnovationGuidance().getSum();
		eso.setProfessionalProjectConstruction(new Statistics_asist(summarys[7], (double)((int)((summarys[7]/departsli.size())/0.01))*0.01));
		sum += eso.getProfessionalProjectConstruction().getSum();
		eso.setStudentsActivity(new Statistics_asist(summarys[8], (double)((int)((summarys[8]/departsli.size())/0.01))*0.01));
		sum += eso.getStudentsActivity().getSum();
		eso.setStudentsCompetitionGuidance(new Statistics_asist(summarys[9], (double)((int)((summarys[9]/departsli.size())/0.01))*0.01));
		sum += eso.getStudentsCompetitionGuidance().getSum();
		eso.setSummerInternationalCourse(new Statistics_asist(summarys[10], (double)((int)((summarys[10]/departsli.size())/0.01))*0.01));
		sum += eso.getSummerInternationalCourse().getSum();
		eso.setTeachingAbilityImprove(new Statistics_asist(summarys[11], (double)((int)((summarys[11]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingAbilityImprove().getSum();
		eso.setTeachingAchievement(new Statistics_asist(summarys[12], (double)((int)((summarys[12]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingAchievement().getSum();
		eso.setTeachingCompetition(new Statistics_asist(summarys[13], (double)((int)((summarys[13]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingCompetition().getSum();
		eso.setTeachingPaper(new Statistics_asist(summarys[14], (double)((int)((summarys[14]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingPaper().getSum();
		eso.setTeachingResearch(new Statistics_asist(summarys[15], (double)((int)((summarys[15]/departsli.size())/0.01))*0.01));
		sum += eso.getTeachingResearch().getSum();
		eso.setTextBookConstruction(new Statistics_asist(summarys[16], (double)((int)((summarys[16]/departsli.size())/0.01))*0.01));
		sum += eso.getTextBookConstruction().getSum();
		eso.setUndergraduateGuidance(new Statistics_asist(summarys[17], (double)((int)((summarys[17]/departsli.size())/0.01))*0.01));
		sum += eso.getUndergraduateGuidance().getSum();
		eso.setSumUP(new Statistics_asist(sum, (double)((int)((sum/18)/0.01))*0.01));
		esotli.add(eso);
		return esotli;
	}
	/*
	 * 根据单个系获取汇总信息
	 */
	public List<E_SummaryOfTeaching> getSingleSum(String foreterm,String afterterm,Department depart){
		List<E_SummaryOfTeaching> esotli = new ArrayList<E_SummaryOfTeaching>();
		DepartmentDAO departsdao = new DepartmentDAO();
		E_SummaryOfTeaching es = null;
			double sum = 0;
			es = new E_SummaryOfTeaching();
			es.setDeparts(departsdao.findById(depart.getDepartmentId()));
			
			es.setClassTeaching(classteachdao.getSA(foreterm, afterterm, depart));
			sum += es.getClassTeaching().getSum();
			
			es.setDegreeGuidance(paperguidancedao.getSA(foreterm, afterterm, depart));
			sum += es.getDegreeGuidance().getSum();
			
			es.setFamousTeacherTeam(famousteachteamdao.getSA(foreterm, afterterm, depart));
			sum += es.getFamousTeacherTeam().getSum();
			
			es.setFineCourse(finecoursedao.getSA(foreterm, afterterm, depart));
			sum += es.getFineCourse().getSum();
			
			es.setFirmWorkstationTrainingBase(firmworkstationdao.getSA(foreterm, afterterm, depart));
			sum += es.getFirmWorkstationTrainingBase().getSum();
			
			es.setOff_campusPracticeGuidance(offcampusdao.getSA(foreterm, afterterm, depart));
			sum += es.getOff_campusPracticeGuidance().getSum();
			
			es.setPracticeInnovationGuidance(practiceinnovationdao.getSA(foreterm, afterterm, depart));
			sum += es.getPracticeInnovationGuidance().getSum();
			
			es.setProfessionalProjectConstruction(professionprojectdao.getSA(foreterm, afterterm, depart));
			sum += es.getProfessionalProjectConstruction().getSum();
			
			es.setStudentsActivity(studentactivitydao.getSA(foreterm, afterterm, depart));
			sum += es.getStudentsActivity().getSum();
			
			es.setStudentsCompetitionGuidance(studentscompetdao.getSA(foreterm, afterterm, depart));
			sum += es.getStudentsCompetitionGuidance().getSum();
			
			es.setSummerInternationalCourse(summercoursedao.getSA(foreterm, afterterm, depart));
			sum += es.getSummerInternationalCourse().getSum();
			
			es.setTeachingAbilityImprove(teachingabilitydao.getSA(foreterm, afterterm, depart));
			sum += es.getTeachingAbilityImprove().getSum();
			
			es.setTeachingAchievement(teachingachievedao.getSA(foreterm, afterterm, depart));
			sum += es.getTeachingAchievement().getSum();
			
			es.setTeachingCompetition(teachingcompetedao.getSA(foreterm, afterterm, depart));
			sum += es.getTeachingCompetition().getSum();
			
			es.setTeachingPaper(teachingpaperdao.getSA(foreterm, afterterm, depart));
			sum += es.getTeachingPaper().getSum();
			
			es.setTeachingResearch(teachingresearchdao.getSA(foreterm, afterterm, depart));
			sum += es.getTeachingResearch().getSum();
			
			es.setTextBookConstruction(textbookdao.getSA(foreterm, afterterm, depart));
			sum += es.getTextBookConstruction().getSum();
			
			es.setUndergraduateGuidance(undergraduateguidancedao.getSA(foreterm, afterterm, depart));
			sum += es.getUndergraduateGuidance().getSum();
			
			es.setSumUP(new Statistics_asist(sum,(double)((int)((sum/18)/0.01))*0.01));
			esotli.add(es);
		return esotli;
	}
	
	public ByteArrayOutputStream genarateTeachingSUMExpot(String foreterm,String afterterm,String departId,List<E_SummaryOfTeaching> exportsdata){
		TftermDAO tftermdao = new TftermDAO();
		DepartmentDAO departdao = new DepartmentDAO();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(tftermdao.findById(foreterm).getTerm()+"-"+tftermdao.findById(afterterm).getTerm());
		//设置列宽
		for(int i=0;i<19;i++){
			sheet.setColumnWidth((short) i, 5000);
		}
		sheet.setColumnWidth((short) 2, 8000);
		sheet.setColumnWidth((short) 5, 8000);
		sheet.setColumnWidth((short) 11, 8000);
		sheet.setColumnWidth((short) 12, 8000);
		sheet.setColumnWidth((short) 13, 8000);
		sheet.setColumnWidth((short) 19, 3000);
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
		
		//合并单元格  org.apache.poi.hssf.util.Region(short rowFrom,short colFrom,short rowTo,short colTo)
		org.apache.poi.hssf.util.Region reg = new org.apache.poi.hssf.util.Region((short)0,(short)0,(short)1,(short)(19));
		sheet.addMergedRegion(reg);
		int rownum = 0;
		HSSFRow row = sheet.createRow((short) rownum++);
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		cell.setCellValue("教学汇总  ["+("alldepart".equals(departId)?"所有系":departdao.findById(departId).getDepartmentName())+"]");
		cell.setCellStyle(cellStyleforFonts);
		reg = new org.apache.poi.hssf.util.Region((short)2,(short)1,(short)2,(short)(5));
		sheet.addMergedRegion(reg);
		reg = new org.apache.poi.hssf.util.Region((short)2,(short)6,(short)2,(short)(13));
		sheet.addMergedRegion(reg);
		reg = new org.apache.poi.hssf.util.Region((short)2,(short)14,(short)2,(short)(18));
		sheet.addMergedRegion(reg);
		rownum++;
		row = sheet.createRow(rownum++);
		cell = row.createCell(1); 
		cell.setCellValue("教学能力与实效");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(6);
		cell.setCellValue("综合改革与教学研究");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(14);
		cell.setCellValue("学生指导工作");
		cell.setCellStyle(cellStyle);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		row = sheet.createRow(rownum++);
		//表头
		cell = row.createCell(0);
		cell.setCellValue("系");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(1);
		cell.setCellValue("课堂教学(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(2);
		cell.setCellValue("学位论文指导质量(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(3);
		cell.setCellValue("教学竞赛(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(4);
		cell.setCellValue("教学能力提升(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(5);
		cell.setCellValue("教学名师和教学团队(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(6);
		cell.setCellValue("教学研究(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(7);
		cell.setCellValue("教学论文(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(8);
		cell.setCellValue("教学成果奖(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(9);
		cell.setCellValue("教材建设(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(10);
		cell.setCellValue("精品课程建设(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(11);
		cell.setCellValue("专业建设项目申报(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(12);
		cell.setCellValue("企业工作站和联合培养基地建设(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(13);
		cell.setCellValue("暑期课程与国际课程建设(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(14);
		cell.setCellValue("实践创新指导(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(15);
		cell.setCellValue("学生竞赛指导(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(16);
		cell.setCellValue("参与学生活动(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(17);
		cell.setCellValue("本科生导师指导(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(18);
		cell.setCellValue("校外实践指导(总/均)");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(19);
		cell.setCellValue("总计(总/均)");
		cell.setCellStyle(cellStyle);
		for(int i=0;i<exportsdata.size();i++){
			row = sheet.createRow(rownum++);
			cell = row.createCell(0);
			cell.setCellValue(exportsdata.get(i).getDeparts().getDepartmentName());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(1);
			cell.setCellValue(exportsdata.get(i).getClassTeaching().getSum()+" / "+exportsdata.get(i).getClassTeaching().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(2);
			cell.setCellValue(exportsdata.get(i).getDegreeGuidance().getSum()+" / "+exportsdata.get(i).getDegreeGuidance().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(3);
			cell.setCellValue(exportsdata.get(i).getTeachingCompetition().getSum()+" / "+exportsdata.get(i).getTeachingCompetition().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(4);
			cell.setCellValue(exportsdata.get(i).getTeachingAbilityImprove().getSum()+" / "+exportsdata.get(i).getTeachingAbilityImprove().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(5);
			cell.setCellValue(exportsdata.get(i).getFamousTeacherTeam().getSum()+" / "+exportsdata.get(i).getFamousTeacherTeam().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(6);
			cell.setCellValue(exportsdata.get(i).getTeachingResearch().getSum()+" / "+exportsdata.get(i).getTeachingResearch().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(7);
			cell.setCellValue(exportsdata.get(i).getTeachingPaper().getSum()+" / "+exportsdata.get(i).getTeachingPaper().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(8);
			cell.setCellValue(exportsdata.get(i).getTeachingAchievement().getSum()+" / "+exportsdata.get(i).getTeachingAchievement().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(9);
			cell.setCellValue(exportsdata.get(i).getTextBookConstruction().getSum()+" / "+exportsdata.get(i).getTextBookConstruction().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(10);
			cell.setCellValue(exportsdata.get(i).getFineCourse().getSum()+" / "+exportsdata.get(i).getFineCourse().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(11);
			cell.setCellValue(exportsdata.get(i).getProfessionalProjectConstruction().getSum()+" / "+exportsdata.get(i).getProfessionalProjectConstruction().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(12);
			cell.setCellValue(exportsdata.get(i).getFirmWorkstationTrainingBase().getSum()+" / "+exportsdata.get(i).getFirmWorkstationTrainingBase().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(13);
			cell.setCellValue(exportsdata.get(i).getSummerInternationalCourse().getSum()+" / "+exportsdata.get(i).getSummerInternationalCourse().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(14);
			cell.setCellValue(exportsdata.get(i).getPracticeInnovationGuidance().getSum()+" / "+exportsdata.get(i).getPracticeInnovationGuidance().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(15);
			cell.setCellValue(exportsdata.get(i).getStudentsCompetitionGuidance().getSum()+" / "+exportsdata.get(i).getStudentsCompetitionGuidance().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(16);
			cell.setCellValue(exportsdata.get(i).getStudentsActivity().getSum()+" / "+exportsdata.get(i).getStudentsActivity().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(17);
			cell.setCellValue(exportsdata.get(i).getUndergraduateGuidance().getSum()+" / "+exportsdata.get(i).getUndergraduateGuidance().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(18);
			cell.setCellValue(exportsdata.get(i).getOff_campusPracticeGuidance().getSum()+" / "+exportsdata.get(i).getOff_campusPracticeGuidance().getAvg());
			cell.setCellStyle(cellStyle);
			cell = row.createCell(19);
			cell.setCellValue(exportsdata.get(i).getSumUP().getSum()+" / "+exportsdata.get(i).getSumUP().getAvg());
			cell.setCellStyle(cellStyle);
		}
		try {
			wb.write(baos);
			return  baos;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
