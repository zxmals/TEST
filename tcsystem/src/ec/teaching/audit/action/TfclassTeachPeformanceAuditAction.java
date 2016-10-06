package ec.teaching.audit.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.model.ResearchLab;
import com.opensymphony.xwork2.ActionContext;

public class TfclassTeachPeformanceAuditAction implements RequestAware{
	public String execute() throws Exception{
		return "success";
	}
	public String getTF_classTeachPerformanceList(){
		return "success";
	}
	
	private int pageIndex=1;
	private int pageSize_CT;
	private String term_CT;
	private ResearchLab researchLab_CT;
	private int operstatus;
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private Map<String,Object> request;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_CT() {
		return pageSize_CT;
	}
	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
		session.put("pageSize_CT",pageSize_CT);
	}
	public String getTerm_CT() {
		return term_CT;
	}
	public void setTerm_CT(String term_CT) {
		this.term_CT = term_CT;
		session.put("term_CT",term_CT);
	}
	public ResearchLab getResearchLab_CT() {
		return researchLab_CT;
	}
	public void setResearchLab_CT(ResearchLab researchLab_CT) {
		this.researchLab_CT = researchLab_CT;
		session.put("researchLab_CT", researchLab_CT);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
	
}
