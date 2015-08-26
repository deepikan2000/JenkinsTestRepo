package com.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;





import com.beans.Student;

public class StudentDao implements IStudentDao {
	
	 
	Connection con;
	
	public StudentDao()
	{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "hr",
					"password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.daos.IStudentDao#getStudentById(int)
	 */
	@Override
	public Student getStudentById(int id)
	{
		try {
			 
			PreparedStatement ps=con.prepareStatement("select * from Student where id=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Student st=new Student();
				st.setId(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setAddress(rs.getString(3));
				return st;
			}
			else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
				
		
	}
	public List<Student> getAllStudents()
	{
		List<Student> students=new ArrayList<Student>();
		try {
			 
			PreparedStatement ps=con.prepareStatement("select * from Student");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Student st=new Student();
				st.setId(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setAddress(rs.getString(3));
				students.add(st);
			}
			
				return students;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

}
