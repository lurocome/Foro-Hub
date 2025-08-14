package com.desafio.foro.foroHub;

import com.desafio.foro.foroHub.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForoHubApplication   {


	public static void main(String[] args) {

		SpringApplication.run(ForoHubApplication.class, args);
	}


}
