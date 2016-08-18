package com.nuaa.ec.utils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SessionFactory;

public class PrimaryKMaker {

	public String getLastElement(String colname,String table){
		String sql = "select  top 1 "+colname+" from "+table+" order by  "+colname+" desc";
		Session session = SessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		String lpk  = null;
		try{			
			Query query = session.createSQLQuery(sql);
			if(query.list().size()>0)
				lpk = (String)query.list().get(0);
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally{
			if(session!=null)
				try {
					session.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
		}
		return lpk;
	}
	/***
	 * 找到对应的的表的最后一个主键并生成一个新的主键
	 * /find the last-pk to the table which you find and make the next-pk.
	 * @param colname 列名
	 * @param table 表名
	 * @param foreword	主键前标志关键字
	 * @return
	 */
	public String mkpk(String colname,String table,String foreword){
		String lastpk = getLastElement(colname, table);
		if(lastpk==null)
			return foreword+"000000001";		
		String pk = 1+Integer.parseInt(lastpk.substring(foreword.length(), lastpk.length()))+"";
		int j = pk.length();
		for(int i=0;i<9-j;i++){
			pk = "0"+pk;
		}
//		System.out.println(foreword+pk);
		return foreword+pk;
	}

	public static void main(String[] args){
		String colname = "ActID";
		String table = "VACollectiveAct";
		String foreword = "vaact";
		System.out.println(new PrimaryKMaker().mkpk(colname, table, foreword));
	}
}
