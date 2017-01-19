package com.nuaa.ec.utils;

import java.text.DecimalFormat;

public class NumberFormatUtil {
	private static DecimalFormat df = null;
	static{
		df = new DecimalFormat("#.00");
	}
	public static float getNumberAfterTransferPrecision(double origin){
		return Float.parseFloat(df.format(origin));
	}
	public static void main(String[] args) {
		System.out.println(getNumberAfterTransferPrecision(12.456));
	}
}
