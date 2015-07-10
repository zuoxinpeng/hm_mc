package com.hm.util;

import java.util.Random;

/**
 * @author zuoxp
 */
public class RandomUtil {

	private static Random random = new Random(System.currentTimeMillis());
	private static char[] ca = new char[] { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	/**
	 * 产生一个给定长度的数字字串
	 * 
	 * @param n
	 * @return
	 */
	public static synchronized String generate(int n) {
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(10);
			cr[i] = Integer.toString(x).charAt(0);
		}
		return (new String(cr));
	}

	/**
	 * 产生一个给定长度限定数字范围的数字字串
	 * 
	 * @param n
	 *            给定长度
	 * @param m
	 *            限定的数字长度内
	 * @return
	 */
	public static synchronized String generate(int n, int m) {
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(m);
			cr[i] = Integer.toString(x + 1).charAt(0);
		}
		return (new String(cr));
	}

	public static synchronized String generateMixed(int n) {
		char[] cr = new char[n];
		for (int i = 0; i < n; i++) {
			int x = random.nextInt(62);
			cr[i] = ca[x];
		}
		return (new String(cr));
	}

	public static synchronized int nextInt(int n) {
		return random.nextInt(n);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(generateMixed(8));
		}
	}
}
