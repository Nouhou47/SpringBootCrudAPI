package com.jb;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jb.dao.PersonnelRepository;
import com.jb.model.Personnel;

@SpringBootApplication
public class CrudMongoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CrudMongoApplication.class, args);
		
		PersonnelRepository repo = ctx.getBean(PersonnelRepository.class);
		
		repo.save(new Personnel("P32", "Programmeur", "Inf"));
		System.out.println("fin$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		Personnel p = new Personnel("P01", "Programmeur", "Inf");
		Personnel p1 = new Personnel("P11", "Hacker", "Hac");
		Personnel p2 = new Personnel("P21", "Encodeur", "Enc");
		
		ArrayList<Personnel> arr = new ArrayList<>();
		arr.add(p);
		arr.add(p1);
		arr.add(p2);
		System.out.println(arr.toString());
		System.out.println("*******************");
		
	}

}
