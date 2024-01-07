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
 * Servlet implementation class donerReuest
 */
@WebServlet("/donerReuest")
public class donerReuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public donerReuest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		PrintWriter out=response.getWriter();
	
		try
		{
	
			String txtName=request.getParameter("txtName");
			String  txtDesis=request.getParameter("txtDesis");
			String  txtblood_group=request.getParameter("txtblood_group");
			String  txtorgens=request.getParameter("txtorgens");
			String  txtAddr=request.getParameter("txtAddr");
			String  txtReference="";
			
			
		
		DbCon conn=new DbCon();
		con=conn.getDbcon();		
		HttpSession session =request.getSession();
		int id= Integer.parseInt(session.getAttribute("ID").toString());
		pst=con.prepareStatement("insert into DonationRequest values(null,?,?,?,?,?,?,default,null,default,?);");
		
		pst.setString(1, txtName);
		pst.setString(2, txtDesis);
		pst.setString(3, txtblood_group);
		pst.setString(4, txtorgens);		
		pst.setString(5, txtAddr);	
		pst.setString(6, txtReference);	
		pst.setInt(7, id);	
		
		int status=pst.executeUpdate();		
		out.println("request send");
		con.close();
		if(status>0)	
		{
		response.sendRedirect("http://localhost:8080/organ/orgens/request.jsp?reg=Request Success.");
		}
		else
		{
			response.sendRedirect("http://localhost:8080/organ/orgens/request.jsp?reg=Request Fail.");
		}	
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
