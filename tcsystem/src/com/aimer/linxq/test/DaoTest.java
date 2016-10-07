package com.aimer.linxq.test;

import java.util.List;

import com.nuaa.ec.dao.TfclassTeachTimeDAO;

public class DaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TfclassTeachTimeDAO classTeachTimeDao = new TfclassTeachTimeDAO();
		List list = classTeachTimeDao.findAll();
		System.out.println(list.toString());
	}

}
