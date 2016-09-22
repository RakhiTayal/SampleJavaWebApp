import java.io.*;

import java.sql.*;

import javax.servlet.ServletException;

import javax.servlet.http.*;

import com.netapp.sfdc.sandbox.objects.CreatePartnerContact;
import com.netapp.sfdc.sandbox.objects.Job;

public class Search extends HttpServlet {

       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)

                     throws ServletException, IOException {

              response.setContentType("text/html");

              PrintWriter out = response.getWriter();        

              String id=request.getParameter("jobId");                          

              try{

                    /* Class.forName("oracle.jdbc.driver.OracleDriver");

                     Connection con=DriverManager.getConnection("jdbc:oracle:thin:@mcndesktop07:1521:xe","sandeep","welcome");               

                     PreparedStatement ps=con.prepareStatement("select * from userlogin where name=?");

                     ps.setString(1,name);   */                

                     out.print("<table width=25% border=1>");

                     out.print("<center><h1>Result:</h1></center>");
                     CreatePartnerContact createPartnerContact = new CreatePartnerContact();
                     Job job = new Job();
                     if (createPartnerContact.login()) {
                    	 job = createPartnerContact.querySample(id);             	       
             	        }
                   

                     //ResultSet rs=ps.executeQuery();                

                     //ResultSetMetaData rsmd=rs.getMetaData();

                     //while(rs.next())

                       // {

                     out.print("<tr>");

                     out.print("<td>"+"Job Id"+"</td>");

                        out.print("<td>"+job.getId()+"</td></tr>");

                        out.print("<tr><td>"+"Name"+"</td>");

                        out.print("<td>"+job.getName()+"</td></tr>");

                        out.print("<tr><td>"+"Status"+"</td>");

                        out.print("<td>"+job.getStatus()+"</td></tr>");

                        /*out.print("<tr><td>"+rsmd.getColumnName(4)+"</td>");

                        out.print("<td>"+rs.getString(4)+"</td></tr>");                  

                     }*/

                     out.print("</table>");

 

              }catch (Exception e2)

                {

                    e2.printStackTrace();

                }

 

              finally{out.close();

                }

       }

 
} 