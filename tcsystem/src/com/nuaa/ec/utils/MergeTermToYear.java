package com.nuaa.ec.utils;


/**
 * 将两个学期化为一个学期，主要用于录入记录周期为一学年的的情况 将每年的两学期的记录的录入合并为一个学期存储。
 * 
 * @author linhd
 * 
 */
public class MergeTermToYear {
	public static String getTermAfterMerge(String termId) {
		/*
		 * 获取termID的最后一位数字 如果是奇数，原封不动 如果是偶数 说明是第二学期， 那么将TermID后退一个。 找到奇数学期
		 */
		String lastNumberOfTermId = termId.charAt(termId.length() - 1) + "";
		int lastNumberOfTermIdToInteger = Integer.parseInt(lastNumberOfTermId);
		if (lastNumberOfTermIdToInteger % 2 == 0) {
			termId = getPreviousTermId(termId);
		}
		return termId;
	}
	
	public static String getPreviousTermId(String termId){
		/*
		 * 获取TermID除去前缀“Term”之后的字符串
		 */
		String termIdExceptPrefix=termId.substring(4);
		/*
		 * 找到第一个非零数字的位置
		 */
		int lengthOfTermIdExceptPrefix=termIdExceptPrefix.length();
		int firstPositionNotZero=-1;
		for(int i=0;i<lengthOfTermIdExceptPrefix;i++){
			if(termIdExceptPrefix.charAt(i)!='0'){
				firstPositionNotZero=i;
				break;
			}
		}
		/*
		 * 获得从第一个非零的位置到最后的非零数据区域
		 */
		String fromNotZeroPositionToEnd = termIdExceptPrefix.substring(firstPositionNotZero);
		/*
		 * 上一个ID的非零编号区域Term0000000**；
		 */
		int previousTermIdOfNotZeroSection=Integer.parseInt(fromNotZeroPositionToEnd)-1;
		/*
		 * 补0，如果有两位数减一变成一位数要补零
		 */
		 String previousTermIdOfNotZeroSectionToString=previousTermIdOfNotZeroSection+"";
		 int lengthOfpreviousTermIdOfNotZeroSectionToString=previousTermIdOfNotZeroSectionToString.length();
		 for(int i=0;i<9-lengthOfpreviousTermIdOfNotZeroSectionToString;i++){
			 previousTermIdOfNotZeroSectionToString="0"+previousTermIdOfNotZeroSectionToString;
		 }
		 return "Term"+previousTermIdOfNotZeroSectionToString;
	}
}
