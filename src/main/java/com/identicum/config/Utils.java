package com.identicum.config;

public class Utils
{
	public static boolean isNumber(String s)
	{
		try {
			Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		
				
	}
}
