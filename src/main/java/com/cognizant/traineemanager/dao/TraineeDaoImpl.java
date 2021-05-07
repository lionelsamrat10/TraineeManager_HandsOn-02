package com.cognizant.traineemanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.traineemanager.exceptions.InvalidTraineeDataException;
import com.cognizant.traineemanager.model.Trainee;


public class TraineeDaoImpl implements TraineeDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TraineeDaoImpl.class);
	private List<Trainee> TraineeList;

	public TraineeDaoImpl() {
		this.TraineeList = new ArrayList<>();
	}

	@Override
	public void registerTrainee(String name, String email, int empid, String cohortCode) {
		String validEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(validEmail);
		Matcher emailMatcher = emailPattern.matcher(email);
		if (!emailMatcher.matches()) {
			throw new InvalidTraineeDataException("Make sure email is in correct format\n");
		}
		
		// name constraint
				char[] chars = name.toCharArray();
				for (char c : chars) {
					if (Character.isDigit(c))
						throw new InvalidTraineeDataException("Name cannot have numeric data");
				}

				// id constraint
				if (String.valueOf(empid).length() != 6) {
					throw new InvalidTraineeDataException("Empid should have 6 numbers");
				}

				// cohort constraint AAAXXAAXX
				String validCohort = "^[A-Za-z]{3}[0-9]{2}[A-Za-z]{2}[0-9]{2}";
				Pattern cohortPattern = Pattern.compile(validCohort);
				Matcher cohortMatcher = cohortPattern.matcher(cohortCode);
				if (!cohortMatcher.matches()) {
					throw new InvalidTraineeDataException("Error! Please enter with format AAAXXAAXX");
				}

				Trainee trainee = new Trainee(name, email, empid, cohortCode);
				TraineeList.add(trainee);
				LOGGER.info("Trainee added successfully");
	}

	@Override
	public void removeTrainee(int empid) {
		for (Trainee trainee : TraineeList) {
			if (trainee.getEmpid() == empid) {
				TraineeList.remove(trainee);
				LOGGER.info("Trainee removed successfully");
				break;
			}
		}
	}

	@Override
	public void findTraineeByName(String name) {
		for (Trainee trainee : TraineeList) {
			if (trainee.getName().equals(name))
				LOGGER.debug("Trainee found with name:" + name + " Trainee cohort code: " + trainee.getCohortCode()
						+" Trainee emp id: " + trainee.getEmpid()  + " Trainee email: " + trainee.getEmail());
		}
	}

	@Override
	public void findAll(String cohortCode) {
		for (Trainee trainee : TraineeList) {
			if (trainee.getCohortCode().equals(cohortCode)) {
				LOGGER.debug("Trainee found with cohort code:" + cohortCode + " Trainee name: " + trainee.getName()
						+ " Trainee emp id: " + trainee.getEmpid() + " Trainee email: " + trainee.getEmail());
			}
		}
	}

}
