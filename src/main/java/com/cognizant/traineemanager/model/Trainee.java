package com.cognizant.traineemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Trainee {
	private String name;
	private String email;
	private int empid;
	private String cohortCode;
}
