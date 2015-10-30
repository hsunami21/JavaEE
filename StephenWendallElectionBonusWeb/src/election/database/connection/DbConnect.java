package election.database.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import election.web.model.Student;
import wendall.stephen.exceptions.DataSourceConnectException;
import wendall.stephen.exceptions.DataSourceNameException;
import wendall.stephen.exceptions.StudentNotRecognizedException;

public final class DbConnect {

	public static Connection getConnection() throws DataSourceNameException, DataSourceConnectException
	{
		Connection conn = null;
		try
		{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/TestDS");
			conn = ds.getConnection();
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new DataSourceNameException();
		}
		catch (SQLException e)
		{
			System.out.println("e.getMessage");
			throw new DataSourceConnectException();
		}
		return conn;
	}
	
	public static Student getStudentLogin(String studentID, String password) throws DataSourceNameException, DataSourceConnectException, SQLException, StudentNotRecognizedException
	{
		Connection conn = DbConnect.getConnection();
		String sql = "SELECT * FROM Test.Voter WHERE VID=? AND PASSWORD=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(studentID));
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next())
		{
			boolean voted;
			if (rs.getCharacterStream(5) == null)
				voted = false;
			else
				voted = true;
			return new Student(studentID, password, voted);
		}
		return null;
	}
	
	public static Map<String, Integer> getCandidates() throws DataSourceNameException, DataSourceConnectException, SQLException
	{
		Map<String, Integer> candidates = new HashMap<String, Integer>();
		Connection conn = DbConnect.getConnection();
		String sql = "SELECT * FROM Test.Candidate";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			candidates.put(rs.getString(2) + " " + rs.getString(3), rs.getInt(1));
		}
		
		return candidates;
	}

	public static boolean voteFor(String student, String name) throws DataSourceNameException, DataSourceConnectException, SQLException
	{
		Connection conn = DbConnect.getConnection();
		String[] names = name.split(" ");
		String sql = "UPDATE candidate SET CVOTES = CVOTES + 1 WHERE FIRSTNAME=? AND LASTNAME=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, names[0]);
		ps.setString(2, names[1]);
		int rs = ps.executeUpdate();
		if (rs > 0)
		{
			System.out.println("Voting Successful");
			sql = "UPDATE voter SET VOTED = 't' WHERE VID=?";
			PreparedStatement ps2 = conn.prepareStatement(sql);
			ps2.setString(1, student);
			int vp = ps2.executeUpdate();
			if (vp > 0)
				System.out.println("Student Voted Updated Successfully");
			return true;
		}
		System.out.println("Voting Unsuccessful");
		return false;
	}
}
