package com.aimer.linxq.test;

import java.util.List;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TfclassTeachTimeDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;

public class DaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DepartmentDAO d = new DepartmentDAO();
		List<Department> list = d.findByDepartmentName("管工");
		System.out.println(list.size());
	}

}
