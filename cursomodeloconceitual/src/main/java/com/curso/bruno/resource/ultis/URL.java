package com.curso.bruno.resource.ultis;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String param){
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		
	}
	
	public static List<Integer> decondIntList(String s){
		/*
		 * List<String> Ids = Arrays.asList(","); List<Integer>IdsInteger= new
		 * ArrayList<Integer>(); for (String string : Ids) {
		 * IdsInteger.add(Integer.parseInt(string)); }
		 * return IdsInteger;
		 */
		
		return Arrays.asList(s.split(",")).stream().map(elemento -> Integer.parseInt(elemento)).collect(Collectors.toList());
				
		
	}

}
