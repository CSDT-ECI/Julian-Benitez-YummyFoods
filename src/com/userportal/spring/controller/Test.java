package com.userportal.spring.controller;

import java.util.Random;


public class Test 
{
	public static void main(String arg[])
	{
		Random r = new Random();
		int i1 = r.nextInt(80 - 65) + 65;
		System.out.println(i1);
	}
}
