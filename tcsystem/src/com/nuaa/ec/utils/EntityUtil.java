package com.nuaa.ec.utils;

import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;

import com.nuaa.ec.dao.SessionFactory;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.Tfterm;

public class EntityUtil {

	private static Configuration hibernateconfig = SessionFactory
			.getConfiguration();

	private static PersistentClass getPersistentclass(Class clazz) {
		synchronized (Entity.class) {
			PersistentClass pc = hibernateconfig.getClassMapping(clazz
					.getName());
			if (pc == null) {
				hibernateconfig = hibernateconfig.addClass(clazz);
				pc = hibernateconfig.getClassMapping(clazz.getName());
			}
			return pc;
		}
	}

	/***
	 * get table - name
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getTableName(Class clazz) {
		return getPersistentclass(clazz).getTable().getName();
	}

	/***
	 * get pk - column - name
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getPkColumnName(Class clazz) {
		return getPersistentclass(clazz).getTable().getPrimaryKey()
				.getColumn(0).getName();
	}

	//TODO: Utils meth0d
		public static String generateQueryCondition(String foredate,String afterdate,String colname){
			StringBuffer condition = new StringBuffer();
			condition.append("AND");
			if(foredate!=null){
				if(!"".equals(foredate.trim()))
					condition.append(" "+colname+">='"+foredate+"' AND");
				if(afterdate!=null){
					if(!"".equals(afterdate.trim()))
						condition.append(" "+colname+"<='"+afterdate+"' AND");
				}
			}else{
				if(afterdate!=null){
					if(!"".equals(afterdate.trim()))
						condition.append(" "+colname+"<='"+afterdate+"' AND");
				}
			}
			return condition.substring(0, condition.length()-3);
		}
		
		public static String generateTeachingQueryCondition(Tfterm term,String colname){
			StringBuffer condition = new StringBuffer();
			condition.append("AND");
			if(term!=null){
				if(!"".equals(term.getTermId().trim())){
					condition.append(" "+colname+"='"+term.getTermId()+"' AND");
				}
			}
			return condition.substring(0, condition.length()-3);
		}
		
		public static String findReasearchLabIdByTeacherId(String teacherId,Session session){
			 try {
		         String queryString = "from Teacher where "
		         		+ "teacherId=? "
		         		+ "and spareTire='1' "; 
		         Query queryObject = session.createQuery(queryString);
				 queryObject.setParameter(0, teacherId);
				 if(queryObject.list().size()>0){
					 return ((Teacher)queryObject.list().get(0)).getResearchLab().getResearchLabId();
				 }else return null;
		      } catch (RuntimeException re) {
		         throw re;
		      }
		}
		
		public static String findDepartIdByTeacherId(String teacherId,Session session){
			 try {
		         String queryString = "from Teacher where "
		         		+ "teacherId=? "
		         		+ "and spareTire='1' "; 
		         Query queryObject = session.createQuery(queryString);
				 queryObject.setParameter(0, teacherId);
				 if(queryObject.list().size()>0){
					 return ((Teacher)queryObject.list().get(0)).getDepartment().getDepartmentId();
				 }else return null;
		      } catch (RuntimeException re) {
		         throw re;
		      }
		}
		
}
