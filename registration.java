package organ;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		PrintWriter out=response.getWriter();
	
		try
		{
	
			String txtEmail=request.getParameter("txtEmail");
			String  txtName=request.getParameter("txtName");
			String  txtMobile=request.getParameter("txtMobile");
			String  txtPassword=request.getParameter("txtPassword");
			
			
			
		
		DbCon conn=new DbCon();
		con=conn.getDbcon();
		pst=con.prepareStatement("select id from Users where  Email=?");
		pst.setString(1, txtEmail);
		rs=pst.executeQuery();
		Boolean hasrow=false;
		if(rs.next())
		{
			hasrow=true;
		}
		if(hasrow==false)
		{
		pst=con.prepareStatement("insert into Users values(null,?,?,?,?,default);");
		
		pst.setString(1, txtEmail);
		pst.setString(2, txtMobile);
		pst.setString(3, txtName);
		pst.setString(4, txtPassword);		
		int status=pst.executeUpdate();		
		out.println("registor");
		con.close();
		if(status>0)	
		{
		response.sendRedirect("http://localhost:8080/organ/Login/registration.jsp?reg=Registration Success.");
		}else
		{
			response.sendRedirect("http://localhost:8080/organ/Login/registration.jsp?reg=Registration Fail.");
		}
		}
		else
		{
			response.sendRedirect("http://localhost:8080/organ/Login/registration.jsp?reg=Email Alredy Exist.");
		}
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
