package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class controller {

	@Autowired  
	Model m;
	
	@Autowired  

	Calculate c;

   
	@GetMapping("/{a}/{op}/{b}")	

public Model calc(@PathVariable String a ,@PathVariable char op ,@PathVariable String b ) {
	
		
		try {
			m.setAns(c.calc(a, op, b));
		} catch (Exception e) {
         m.setAns("E");
		}

	return m;
}
	@GetMapping("/sqrt/{a}")	

public Model sqrt(@PathVariable String a) {
		

		try {
			m.setAns(c.sqrtt(a));
		} catch (Exception e) {
         m.setAns("E");
		}

		return m;
		
	}
	@GetMapping("/trans/{a}")	

public Model transpose(@PathVariable String a) {
		
   

		try {
			m.setAns(c.transpose2(a));
		} catch (Exception e) {
         m.setAns("E");
		}
		return m;
		
	}
	
	@GetMapping("/sqr/{a}")	

public Model sqr(@PathVariable String a) {
		
   

		try {
			m.setAns(c.sqr2(a));
		} catch (Exception e) {
         m.setAns("E");
		}
		
		return m;
		
	}
	
	@GetMapping("/perc/{a}/{b}")	

public Model perc(@PathVariable String a, @PathVariable String b) {
		
   

		try {
			m.setAns(c.perc2(a,b));
		} catch (Exception e) {
         m.setAns("E");
		}

		
		return m;
		
	}
	
	
}
