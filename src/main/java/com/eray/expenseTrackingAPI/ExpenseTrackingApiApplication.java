package com.eray.expenseTrackingAPI;

import com.eray.expenseTrackingAPI.dto.Request.CreateUserRequest;
import com.eray.expenseTrackingAPI.model.User;
import com.eray.expenseTrackingAPI.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.eray.expenseTrackingAPI.enums.Role.ADMIN;
import static com.eray.expenseTrackingAPI.enums.Role.USER;

@SpringBootApplication
public class ExpenseTrackingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingApiApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(
			AuthService service
	) {
		return args -> {
			CreateUserRequest admin = CreateUserRequest.builder()
					.name("Admin")
					.surname("Admin")
					.tc("admin@mail.com")
					.password("password")
					.country("Turkey")
					.city("Ankara")
					.address("adminnnn")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());


			CreateUserRequest user = CreateUserRequest.builder()
					.name("User")
					.surname("User")
					.tc("user@mail.com")
					.password("password")
					.country("Turkey")
					.city("Ankara")
					.address("userrr")
					.role(USER)
					.build();
			System.out.println("User token: " + service.register(user).getAccessToken());



		};
	}


}