package organ;
import java.sql.*;

public class DbCon 
{

	private Connection dbcon;
	
	public DbCon()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbcon=DriverManager.getConnection("jdbc:mysql://localhost:3306/organ?user=root&password=root");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public Connection getDbcon() {
		return dbcon;
	}
	
	
	

}
