package com.hzb.Utils;

import java.util.Collections;
import java.util.Scanner;

public class Utils {

	/**
	 * 连续呼入多行数据之方法
	 */
	private void inputDate() {
		// 输入多行数据
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int a = in.nextInt();
			System.out.println(a);

		}
	}

	/**
	 * java 对小数点取整数的方法
	 */

	public void DecimalData(double value) {

		// 1 种
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		double d = 3.14159;
		System.out.println(df.format(d));

		// 2种

		java.math.BigDecimal bd = new java.math.BigDecimal("3.14159265");
		bd = bd.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);

		// 3
		long l1 = Math.round(value * 100); // 四舍五入
		double ret = l1 / 100.0; // 注意：使用 100.0 而不是 100

	}

	/**
	 * 
	 * 对arraylist排序使用 Collections.sort(arry);
	 */

	/**
	 * 
	 * 对行和列都是从小到大排序的数组进行查找
	 */

	public boolean getSortedArray(int array[][], int target) {

		int i = 0;
		int j = array.length - 1;
		while (i < array[0].length && j >= 0) {
			System.out.println("i=" + i + ":j=" + j);
			System.out.println(array[j][i]);
			if (array[j][i] == target)
				return true;
			else if (array[j][i] < target) {
				i++;
			} else if (array[j][i] > target) {
				j--;
			}

		}
		return false;
	}

	/**
	 * 计算两个程序之间的运行时间差值
	 * 
	 * 多重循环一般是大循环放外面，小循环放里面；
	 * 但也有特殊情况，看循环里面的计算。
	 */
	public void calTime() {
		long time1 = System.currentTimeMillis();
		int k = 0;
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 10000; j++) {
				k++;
			}

		long time2 = System.currentTimeMillis();
	}

	/**
	 *
	 * 打印字符串
	 * @param s
	 */
	public static void Println(String s){
		System.out.println(s);
	}

	/**
	 *
	 * @param s
	 */
	public static void Print(String s){
		System.out.print(s);
	}

}
