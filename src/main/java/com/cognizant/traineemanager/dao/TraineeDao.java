package com.cognizant.traineemanager.dao;

public interface TraineeDao {
	public void registerTrainee(String name, String email, int empid, String cohortCode);
	public void removeTrainee(int empid);
	public void findTraineeByName(String name);
	public void findAll(String cohortCode);
}
