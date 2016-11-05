package com.nuaa.ec.utils;

import org.junit.Test;

public class JUnitTest {
	@Test
	public void testMergeTermToYear(){
		String s="Term000000012";
		String result=MergeTermToYear.getTermAfterMerge(s);
		System.out.println(result);
	}
}
