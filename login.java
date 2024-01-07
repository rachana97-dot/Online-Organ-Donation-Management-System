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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		HttpSession session = request.getSession();
		try
		{
	
			String txtEmail=request.getParameter("txtEmail");			
			String  txtPassword=request.getParameter("txtPassword");
			
		
		DbCon conn=new DbCon();
		con=conn.getDbcon();
		pst=con.prepareStatement("select * from Users where Email=? AND password=? ");
		pst.setString(1, txtEmail);		
		pst.setString(2, txtPassword);	
		rs=pst.executeQuery();	
		if(rs.first())
		{
			session.setAttribute("ID", rs.getString("ID")); 
			session.setAttribute("usertype", rs.getString("usertype")); 
			session.setAttribute("userName", rs.getString("Name")); 
			response.sendRedirect("index.jsp");
		}else
		{
			response.sendRedirect("index.jsp?s=fail");
		}
		
		
	}catch(Exception ex)
		{
		out.print(ex);
		}
	}

}
