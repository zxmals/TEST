package com.nuaa.ec.science.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.RewardLevelDAO;
import com.nuaa.ec.dao.RewardTypeDAO;
import com.nuaa.ec.dao.ScientificResearchRewardScoreDAO;
import com.nuaa.ec.model.RewardLevel;
import com.nuaa.ec.model.RewardType;
import com.nuaa.ec.model.ScientificResearchRewardScore;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ScientificProjectRewardSetAction implements RequestAware{

	private Map<String, Object> request;
	private Integer operstatus;
	
	private RewardLevel rewardlev;
	private RewardType rewardty;
	private ScientificResearchRewardScore rewardscore;
	
	private RewardLevelDAO rewardlevdao = new RewardLevelDAO();
	private RewardTypeDAO rewardtypdao = new RewardTypeDAO();
	private ScientificResearchRewardScoreDAO rewardscoredao = new ScientificResearchRewardScoreDAO();
	
	private PrimaryKMaker pkmk = new PrimaryKMaker();

	//default method
	public String execute(){
		return "success";
	}
	//TODO:RewardLevel Set --奖励等级设置
	/***
	 * get all of Reward-level inf //获取所有科研奖励级别信息
	 * @return
	 * @throws Exception
	 */
	public String getProjectRewardLevelINF() throws Exception{
		request.put("rewardlevli", rewardlevdao.findAll());
		return "success";
	}
	/***
	 * 新增一个科研奖励等级 //add a new project -reward --level inf
	 * @return
	 * @throws Exception
	 */
	public String addProjectRewardLevel() throws Exception{
		Transaction tx = null;
		try {
			rewardlev.setSpareTire("1");
			rewardlev.setRewardLevelId(pkmk.mkpk(EntityUtil.getPkColumnName(RewardLevel.class), EntityUtil.getTableName(RewardLevel.class), "SRRL"));
			rewardlevdao.save(rewardlev);
			tx = rewardlevdao.getSession().beginTransaction();
			tx.commit();
			getProjectRewardLevelINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	/***
	 * update a project reward--level inf//更新一个科研奖励等级
	 * @throws Exception
	 */
	public void updateProjectRewardLevel()throws Exception{
		Transaction tx = null;
		try {
			rewardlev.setSpareTire("1");
			rewardlevdao.merge(rewardlev);
			tx = rewardlevdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/***
	 * 删除 一个科研奖励等级 //delete a project reward eveluate --Level
	 * @throws Exception
	 */
	public void deleteProjectRewardLevel()throws Exception{
		Transaction tx = null;
		try {
			rewardlev.setSpareTire("0");
			rewardlevdao.merge(rewardlev);
			tx = rewardlevdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO:RewardType Set --奖励类型设置
	/***
	 * 获取所有科研奖励类型
	 * @return
	 * @throws Exception
	 */
	public String getProjectRewardTypeINF() throws Exception{
		request.put("rewardtypli", rewardtypdao.findAll());
		return "success";
	}
	/***
	 * 添加一个 科研项目奖励类型
	 * @return
	 * @throws Exception
	 */
	public String addProjectRewardType() throws Exception{
		Transaction tx = null;
		try {
			rewardty.setSpareTire("1");
			rewardty.setRewardTypeId(pkmk.mkpk(EntityUtil.getPkColumnName(RewardType.class), EntityUtil.getTableName(RewardType.class), "SRRT"));
			rewardtypdao.save(rewardty);
			tx = rewardtypdao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			getProjectRewardTypeINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	/***
	 * 更新科研奖励类型 //
	 * @throws Exception
	 */
	public void updateProjectRewardType() throws Exception{
		Transaction tx = null;
		try {
			rewardty.setSpareTire("1");
			rewardtypdao.merge(rewardty);
			tx = rewardtypdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/***
	 * 删除科研奖励类型
	 * @throws Exception
	 */
	public void deleteProjectRewardType() throws Exception{
		Transaction tx = null;
		try {
			rewardty.setSpareTire("0");
			rewardtypdao.merge(rewardty);
			tx = rewardtypdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO:Reward-evaluate -score  Set --奖励评分设置
	public String  getProjectRewardScoreINF() throws Exception{
		request.put("projectrewardscoreli", rewardscoredao.findAll());
		getProjectRewardLevelINF();
		getProjectRewardTypeINF();
		return "success";
	}
	//TODO: Getter and Setter
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public RewardLevel getRewardlev() {
		return rewardlev;
	}

	public void setRewardlev(RewardLevel rewardlev) {
		this.rewardlev = rewardlev;
	}

	public RewardType getRewardty() {
		return rewardty;
	}

	public void setRewardty(RewardType rewardty) {
		this.rewardty = rewardty;
	}

	public ScientificResearchRewardScore getRewardscore() {
		return rewardscore;
	}

	public void setRewardscore(ScientificResearchRewardScore rewardscore) {
		this.rewardscore = rewardscore;
	}
	
	public Integer getOperstatus() {
		return operstatus;
	}
	
	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
}
