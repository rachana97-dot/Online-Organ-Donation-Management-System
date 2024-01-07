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
 * Servlet implementation class orgenReuest
 */
@WebServlet("/orgenReuest")
public class orgenReuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orgenReuest() {
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
	
			String txtOrgen=request.getParameter("txtOrgen");
			String  txtDRName=request.getParameter("txtDRName");
			String  txtAge=request.getParameter("txtAge");
			String  txtProblemYear=request.getParameter("txtProblemYear");
			String  txtAddr=request.getParameter("txtAddr");
			String  txtReference=request.getParameter("txtReference");
			
			
		
		DbCon conn=new DbCon();
		con=conn.getDbcon();		
		HttpSession session =request.getSession();
		int id= Integer.parseInt(session.getAttribute("ID").toString());
		pst=con.prepareStatement("insert into OrgenRequest values(null,?,?,?,?,?,?,default,null,default,?);");
		
		pst.setString(1, txtOrgen);
		pst.setString(2, txtDRName);
		pst.setString(3, txtAge);
		pst.setString(4, txtProblemYear);		
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
