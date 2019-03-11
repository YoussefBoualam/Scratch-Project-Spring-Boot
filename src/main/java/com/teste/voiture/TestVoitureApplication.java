package com.teste.voiture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestVoitureApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TestVoitureApplication.class, args);
		
		//ApplicationContext appCon = SpringApplication.run(TestVoitureApplication.class, args);
		//voitureRepository vRepo = appCon.getBean(voitureRepository.class);
		//vRepo.save(new Voiture("Audi","R8",250000));		
		//vRepo.save(new Voiture("Mercides","AMG",350000));
		//vRepo.save(new Voiture("BMW","X6",200000));		vRepo.save(new Voiture("Audi","R8",250000));
		//vRepo.findAll().forEach(v->System.out.println(v.getInfo()));
		
	}

}
