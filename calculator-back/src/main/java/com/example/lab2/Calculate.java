package com.example.lab2;

import org.springframework.stereotype.Service;

@Service
public class Calculate {

	public String calc(String a,char op ,String b)  {
		if(a=="." || b==".") {
			 String E="E";
	          return E;
		}

		if(a=="") {
		a="0";	
		}
		if(b=="") {
			b="0";
		}
	
		double x;
		double y;
		try {
		 x=Double.parseDouble(a);
		 y=Double.parseDouble(b);
		
		}
		catch(Exception e) {
          String E="E";
          return E;
		}
		
		
		double ans = 0;
		
		
		
		
		try {
		if(((int)op-(int)'+')==0) {
			ans=x+y;
	}
		
		else if(((int)op-(int)'-')==0) {
			ans=x-y;
	}
		
		else if(((int)op-(int)'-')==0) {
			ans=x-y;
	}
		else if(((int)op-(int)'*')==0) {
			ans=x*y;
	}
		else if(((int)op-(int)'d')==0) {
			if(y==0) {
				  String E="E";
		          return E;
			}
			ans=x/y;
	}
		
		}
		catch(Exception e) {
			   String E="E";
		          return E;
		}
		
		String z=""+ans;
		return z;
		
		
		
	}
	public String sqrtt(String a)  {
		if(a=="." ) {
			 String E="E";
	          return E;
		}
		double ans=0;
		try {
		ans=Double.parseDouble(a);
	    if(ans<0) {
			String E="E";
	          return E;	}
		ans=Math.sqrt(ans);

		}catch(Exception e) {
			   String E="E";
		          return E;	
		}
		String z=""+ans;  

		return z;
	}
	public String transpose2(String a)  {
		if(a=="." ) {
			 String E="E";
	          return E;
		}
		if(a=="" ) {
			  String E="E";
	          return E;	
		}

		double ans=0;
		try {
		ans=Double.parseDouble(a);
	    if(ans==0) {
	    	  String E="E";
	          return E;	
	    }
		ans=1/ans;
	    
	    
		}catch(Exception e) {
			   String E="E";
		          return E;	
		}
		String z=""+ans;  

		return z;
		
	}
	public String sqr2(String a)  {
		if(a=="." ) {
			 String E="E";
	          return E;}		
		double ans=0;
		try {
		ans=Double.parseDouble(a);
		
	    ans=Math.pow(ans, 2);
		}catch(Exception  e) {
			  String E="E";
	          return E;		
		}
		String z=""+ans;  

		return z;}
	
	
	public String perc2(String a ,String b)  {
		if(a=="." || b==".") {
			 String E="E";
	          return E;
		}

		if(a=="") {
			a="0";	
			}
			if(b=="") {
				b="0";
			}
			double x;
			double y;
			try {
			 x=Double.parseDouble(a);
			 y=Double.parseDouble(b);
			
			}
			catch(Exception e) {
				  String E="E";
		          return E;
			}
			double ans= (y*x)/100;
		String z=""+ans;

		return z;}
	

}
