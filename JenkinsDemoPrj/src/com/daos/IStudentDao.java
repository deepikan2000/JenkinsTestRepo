package com.daos;



import java.util.List;

import com.beans.Student;

public interface IStudentDao {

	public abstract Student getStudentById(int id);
	public List<Student> getAllStudents();

}