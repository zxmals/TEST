package com.nuaa.ec.utils;

import javax.persistence.Entity;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;

import com.nuaa.ec.dao.SessionFactory;
public class EntityUtil {

	private static Configuration hibernateconfig = SessionFactory.getConfiguration();
	
	
	private static PersistentClass getPersistentclass(Class clazz){
		synchronized (Entity.class) {
			PersistentClass pc = hibernateconfig.getClassMapping(clazz.getName());
			if(pc==null){
				hibernateconfig = hibernateconfig.addClass(clazz);
				pc = hibernateconfig.getClassMapping(clazz.getName());
			}
			return pc;
		}
	}
	/***
	 * get table - name
	 * @param clazz
	 * @return
	 */
	public static String getTableName(Class clazz){
		return getPersistentclass(clazz).getTable().getName();
	}
	/***
	 * get pk - column - name 
	 * @param clazz
	 * @return
	 */
	public static String getPkColumnName(Class clazz){
		return getPersistentclass(clazz).getTable().getPrimaryKey().getColumn(0).getName();
	}
}
