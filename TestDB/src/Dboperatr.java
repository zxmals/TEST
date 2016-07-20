
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.T_DEPART;
import model.T_USER;


public class Dboperatr {

	public static void main(String[] args){		
//		//插入数据到表T_DEPART
//		insertDepart(new T_DEPART("32010006","320100","06","部门6","","6"));//Add 3201004~3201005
//		//插入数据到表T_DEPART
//		insert(new T_DEPART("32010005","320100","05","部门5","","5")); //Add depart: 3201004~3201005
//		//插入数据到表T_USER
//		insert(new T_USER("320100lar6","320100", "lar6", "立案人6", "","", "32010003", "", "5", "")); //Add User: 320100lar2~320100lar6
		
//		//更新表T_USER的数据
//		updateUser("YHBM", "32010003", "320100lar3");
//		//更新表T_DEPART的数据
//		updateDepart("BMMC", "部门4_updatetest", "32010004");
		
//		//删除行从表T_DEPART
//		deleteDepart("32010005");
//		//删除行从表T_USER
//		deleteUser("320100lar5");
		
//		//4. 用Map存储所有用户的用户代码
//		Map<String, Object> map = mapquerytest();
//		//5. 遍历Map输出用户代码和用户姓名
//		for(Map.Entry<String, Object> e: map.entrySet()){
//			System.out.println(e.getKey()+"------"+e.getValue());
//		}
		
//		//6.用List 存储所有用户的信息，包括用户代码（T_USER.YHDM），用户姓名（T_USER.YHXM），部门代码（T_USER.YHBM），用户所在部门名称 (T_DEPART.BMMC)
//		List<T_USER>user = getUser();
//		//7. 遍历List输出用户姓名，和用户所在部门名称
//		for(T_USER e:user){
//			System.out.println("姓名:"+e.getYHXM()+"\t所在部门:"+e.getYHBM());
//		}
		
	
//		//8.2016-01-01 00:00:00 的 1个月28天后的时间，并以“yyyy-MM-dd”的样式输出
//		Calendar cal = Calendar.getInstance();
//		cal.set(2016, 0, 1, 0, 0, 0);
//		cal.add(Calendar.MONTH, 1);		
//		cal.add(Calendar.DAY_OF_MONTH, 28);
//		Date date = cal.getTime();
//		System.out.println(ToolUtils.convertRq(date, "yyyy-MM-dd"));
		
		
	}
	/***
	 * 链接数据库
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;
		String driver = "com.sybase.jdbc3.jdbc.SybDriver";
		String url = "jdbc:sybase:Tds:zxmal:5000/testdb?charset=cp936";
		String username = "sa";
		String password = "";
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}	
	/**
	 * 增加部门或者用户/add depart or user
	 * @param o
	 * @return
	 */
	public static int insert(Object o){
		int flag = 1;
		String objtype = o.getClass().getSimpleName();
		Connection con = null;
		Statement state = null;
		T_DEPART depart = null;
		T_USER user = null;
		String sql = null;
		if("T_DEPART".equals(objtype)){
			depart = (T_DEPART)o;	
			sql = "insert into T_DEPART values('"+depart.getBMDM()+"','"+depart.getDWDM()+"','"+depart.getBMID()+"','"+depart.getBMMC()+"','"+depart.getSFJY()+"',"+Integer.parseInt(depart.getPXH())+")";			
		}
		if("T_USER".equals(objtype)){
			user = (T_USER)o;
			sql = "insert into T_USER values('"+user.getYHDM()+"','"+user.getDWDM()+"','"+user.getYHID()+"','"+user.getYHXM()+"','"+user.getYHKL()+"','"+user.getYHXB()+"','"+user.getYHBM()+"','"+user.getCSRQ()+"',"+Integer.parseInt(user.getPXH())+",'"+user.getSFJY()+"')";
		}
		try{
			con = getConn();
			con.setAutoCommit(false);
			state = con.createStatement();
			state.execute(sql);
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{				
				if(state!=null)
					state.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * update/修改  表   dbo.T_USER
	 * @param colname
	 * @param value
	 * @param id
	 * @return
	 */
	public static int updateUser(String colname,String value,String id){
		int flag = 1;		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update T_USER set "+colname+" = ? where YHDM = ?";		
		try{
			con = getConn();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);
			ps.setString(1, value);
			ps.setString(2, id);
			ps.execute();
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{				
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * update/修改  表   dbo.T_DEPART
	 * @param colname
	 * @param value
	 * @param id
	 * @return
	 */
	public static int updateDepart(String colname,String value,String id){
		int flag = 1;		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update T_DEPART set "+colname+" = ? where BMDM = ?";		
		try{
			con = getConn();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);			
			ps.setString(1, value);
			ps.setString(2, id);
			ps.execute();
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{				
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 删除行从表T_DEPART
	 * @param id
	 * @return
	 */
	public static int deleteDepart(String id){
		int flag = 1;		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from T_DEPART where BMDM = ?";		
		try{
			con = getConn();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);			
			ps.setString(1, id);
			ps.execute();
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{				
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 删除行从表T_USER
	 * @param id
	 * @return
	 */
	public static int deleteUser(String id){
		int flag = 1;		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from T_USER where YHDM = ?";		
		try{
			con = getConn();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql);			
			ps.setString(1, id);
			ps.execute();
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{				
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	/**
	 * 用Map存储所有用户的用户代码
	 * @return
	 */
	public static Map<String, Object> mapquerytest(){
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "select YHDM,YHXM from T_USER";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				map.put((String)rs.getObject("YHXM"), rs.getObject("YHDM"));				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{			
				try {
					if(rs!=null)
						rs.close();
					if(conn!=null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		

		return map;
	}
	
	/**
	 * 用List 存储所有用户的信息，包括用户代码（T_USER.YHDM），用户姓名（T_USER.YHXM），部门代码（T_USER.YHBM），用户所在部门名称 (T_DEPART.BMMC)
	 * @return
	 */
	public static List<T_USER> getUser(){
		List<T_USER> userli = new ArrayList<T_USER>();
		T_USER user = null;
		String sql = "select T_USER.YHDM,T_USER.YHXM,T_USER.YHBM,T_DEPART.BMMC from T_USER,T_DEPART where T_USER.YHBM = T_DEPART.BMDM";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user = new T_USER(rs.getString("YHDM"), rs.getString("YHXM"), rs.getString("YHBM"), rs.getString("BMMC"));
				userli.add(user);
			}
		}catch(Exception e){
			
		}finally{
			
		}
		if(userli.size()!=0)
			return userli;
		else
			return null;
	}
	
	
//	/***
//	 * 增加部门/add depart
//	 * @param depart
//	 * @return
//	 */
//	public static int insertDepart(T_DEPART depart){
//		int flag = 1;
//		Connection con = null;
//		ResultSet rs = null;
//		PreparedStatement ps = null;
//		String sql = "insert into T_DEPART VALUES(?,?,?,?,?,?)";
//		try{
//			con = getConn();
//			con.setAutoCommit(false);
//			ps = con.prepareStatement(sql);
//			ps.setString(1, depart.getBMDM());
//			ps.setString(2, depart.getDWDM());
//			ps.setString(3, depart.getBMID());
//			ps.setString(4, depart.getBMMC());
//			ps.setString(5, depart.getSFJY());
//			ps.setLong(6, Integer.parseInt(depart.getPXH()));
//			ps.execute();
//			con.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			flag = 0;
//		}finally{
//			try{				
//				if(rs!=null)
//					rs.close();
//				if(ps!=null)
//					ps.close();
//				if(con!=null)
//					con.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return flag;
//	}
	/**
	 * 查询/select
	 * @param sql
	 * @param param
	 * @return
	 */	
	public static List query(String sql,String[] param){		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ResultSetMetaData mdata = null;
		Map<String, Object> map = new HashMap<>();
		List<Object> li = new ArrayList<>();
		int colnum = 0;		
		try{
			con = getConn();
			ps = con.prepareStatement(sql);
			if(param!=null)
				for(int i=1;i<param.length;i++)
					ps.setString(i, param[i-1]);
			rs = ps.executeQuery();
			while(rs.next()){
				mdata = rs.getMetaData();
				colnum = mdata.getColumnCount();
				for(int j=1;j<=colnum;j++){
					map.put(mdata.getColumnName(j),rs.getObject(j));
				}
				li.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(con!=null)
					con.close();
				if(rs!=null)
					rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return li;
	}
}
