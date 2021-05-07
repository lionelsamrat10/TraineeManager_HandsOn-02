package com.cognizant.traineemanager;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.traineemanager.dao.TraineeDaoImpl;

@SpringBootApplication
public class TraineeManagerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraineeManagerApplication.class);
	private static TraineeDaoImpl traineeDaoImpl = new TraineeDaoImpl();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LOGGER.info("Welcome!" + "\n" + "Select an option?");
		int choice = 0;
		do {
			LOGGER.info("\n1. Register a trainee" + "\n" + "2. remove trainee" + "\n"
					+ "3. Find trainee by name " + "\n" + "4. Find trainee by cohort code" + "\n" + "5.Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				sc.nextLine();
				LOGGER.info("Enter trainee name:");
				String name = sc.nextLine();
				LOGGER.info("Enter email:");
				String email = sc.next();
				LOGGER.info("Enter emp id");
				int empid = sc.nextInt();
				LOGGER.info("Enter cohort code");
				String cohortCode = sc.next();
				
				traineeDaoImpl.registerTrainee(name, email, empid, cohortCode);
				break;
			
			case 2:
				LOGGER.info("Enter trainee id to remove");
				int id = sc.nextInt();

				traineeDaoImpl.removeTrainee(id);
				break;
				
			case 3: 
				LOGGER.info("Enter trainee name to find");
				String traineeName = sc.next();
				
				traineeDaoImpl.findTraineeByName(traineeName);				
				break;
			
			case 4:
				LOGGER.info("Enter cohort code to find");
				String code = sc.next();
				
				traineeDaoImpl.findAll(code);				
				break;
			default:
				break;
			}
		} while (choice != 5);

		LOGGER.info("Thank You!!!");
	}

}
