package com.hakim.mangeempolye;

import java.util.ArrayList;
import com.hakim.mangeempolye.domain.Role;
import com.hakim.mangeempolye.domain.UserApp;
import com.hakim.mangeempolye.services.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MangeEmpolyeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangeEmpolyeApplication.class, args);
	}
/*@Bean
CommandLineRunner  run(UserService userService){

		return args->{

			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_DEVELOPER"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));


			userService.saveUser(new UserApp(null,"Diego"," Mendoza", "1828",new ArrayList<>()));
			userService.saveUser(new UserApp(null,"Gonzalo"," abollo", "12365",new ArrayList<>()));
			userService.saveUser(new UserApp(null,"Sara"," Laventi", "983",new ArrayList<>()));

			userService.addRoleToUser("Diego", "ROLE_MANGER");
			userService.addRoleToUser("Gonzalo", "ROLE_DEVELOPER");
			userService.addRoleToUser("Sara", "ROLE_SUPER_ADMIN");

		
		};
	}
*/
}
