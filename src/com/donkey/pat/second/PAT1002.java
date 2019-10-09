package com.donkey.pat.second;

import java.util.Scanner;

public class PAT1002 {
	  public static void main(String args[]){
		    Scanner in = new Scanner(System.in);
		    String s = in.next();
		    System.out.println(calculate(s));
		    in.close();
		  }
		  
		  public static String calculate(String s){
		    int sum = 0;
		    String result = "";
		   // String s = String.valueOf(a);
		    for(int i=0;i<s.length();i++){
		      sum  += Integer.parseInt(s.charAt(i)+"");
		    }
		    String str = String.valueOf(sum);
		    for(int j=0;j<str.length();j++){
		      if(j != str.length()-1){
		          if('1'== str.charAt(j)){
		            result += "yi"+" ";
		          }
		          if('2'== str.charAt(j)){
		            result += "er"+" ";
		          }
		          if('3'== str.charAt(j)){
		            result += "san"+" ";
		          }
		          if('4'== str.charAt(j)){
		            result += "si"+" ";
		          }
		          if('5'== str.charAt(j)){
		            result += "wu"+" ";
		          }
		          if('6'== str.charAt(j)){
		            result += "liu"+" ";
		          }
		          if('7'== str.charAt(j)){
		            result += "qi"+" ";
		          }
		          if('8'== str.charAt(j)){
		            result += "ba"+" ";
		          }
		          if('9'== str.charAt(j)){
		            result += "jiu"+" ";
		          }
		          if('0'== str.charAt(j)){
		            result += "ling"+" ";
		          }
		      }else{
		         if('1'== str.charAt(j)){
		            result += "yi";
		          }
		          if('2'== str.charAt(j)){
		            result += "er";
		          }
		          if('3'== str.charAt(j)){
		            result += "san";
		          }
		          if('4'== str.charAt(j)){
		            result += "si";
		          }
		          if('5'== str.charAt(j)){
		            result += "wu"+" ";
		          }
		          if('6'== str.charAt(j)){
		            result += "liu";
		          }
		          if('7'== str.charAt(j)){
		            result += "qi";
		          }
		          if('8'== str.charAt(j)){
		            result += "ba";
		          }
		          if('9'== str.charAt(j)){
		            result += "jiu";
		          }
		          if('0'== str.charAt(j)){
		            result += "ling";
		          }
		      }
		    }
		    return result;
		  }
		  
		}
