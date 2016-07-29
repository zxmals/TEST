package tcsystem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TeacherLoginInfoDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherLoginInfo;

public class test extends BaseHibernateDAO {

	private Session session;
	private Transaction transaction;
	private TeacherLoginInfoDAO teacherlgdao = new TeacherLoginInfoDAO();
	private TeacherDAO teacherdao = new TeacherDAO();
//	@Before
//	public void init() {
//		session = getSession();
//		transaction = session.beginTransaction();
//	}
//
//	@After
//	public void destory() {
//		transaction.commit();
//		session.close();
//	}
	
	@Test
	public void login(){
		String teacherid = "091300422";
		String pwd = "091300422";
		TeacherLoginInfo teacherlg = null;
		Teacher teacher = null;
		teacherlg = (TeacherLoginInfo)session.get(TeacherLoginInfo.class, teacherid);
		if(teacherlg!=null){
			if(pwd.equals(teacherlg.getPassword())){				
				teacherlg.setLastLoginDate(new java.sql.Timestamp(new java.util.Date().getTime()));
				teacherlg.setLoginTime(teacherlg.getLoginTime()+1);
				session.update(teacherlg);
				System.out.println("login success");
			}
			else
				System.out.println("login fail error password");
		}
		else{
			teacher = (Teacher)session.get(Teacher.class,teacherid);
			if(teacher!=null){
				if(pwd.equals(teacher.getTeacherId())){
					System.out.println("login success first login");
					teacherlg = new TeacherLoginInfo(teacherid, teacher, pwd, "1", "1", 1, new java.sql.Timestamp(new java.util.Date().getTime()));
					session.save(teacherlg);
				}
				else{
					System.out.println("login fail error password");
				}
			}else{
				System.out.println("login fail user not exist");
			}
		}
	}
	/***
	 * befor run this function must note @after & @befor
	 */
	@Test
	public void logindao(){
		String teacherid = "091300422";
		String pwd = "091300422";
		TeacherLoginInfo teacherlg = null;
		Teacher teacher = null;
		
		teacherlg = teacherlgdao.findById(teacherid);
		if(teacherlg!=null){
			if(pwd.equals(teacherlg.getPassword())){				
				teacherlg.setLastLoginDate(new java.sql.Timestamp(new java.util.Date().getTime()));
				teacherlg.setLoginTime(teacherlg.getLoginTime()+1);
				teacherlgdao.save(teacherlg);;
				System.out.println("log in");
			}
			else
				System.out.println("error password");
		}
		else{
			teacher = teacherdao.findById(teacherid);
			if(teacher!=null){
				if(pwd.equals(teacher.getTeacherId())){
					System.out.println("first login,welcome!");
					teacherlg = new TeacherLoginInfo(teacherid, teacher, pwd, "1", "1", 1, new java.sql.Timestamp(new java.util.Date().getTime()));
					teacherlgdao.save(teacherlg);
				}
				else{
					System.out.println("login fail error password");
				}
			}else{
				System.out.println("user not found");
			}
		}
		closeSession(this.getSession());
	}
}
