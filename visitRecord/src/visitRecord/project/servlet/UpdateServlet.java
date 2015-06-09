package visitRecord.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visitRecord.project.po.Client;
import visitRecord.project.po.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String updateclient = request.getParameter("client_data");
		System.out.println(updateclient);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm").create();
		
		Client c = gson.fromJson(updateclient, Client.class);
		
		Transaction transaction = new Transaction();
		boolean result = transaction.updateClient(c);
		
		// -------------------响应客户端 JSon----------------

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String gson_data = gson.toJson(result);
		
		System.out.println(gson_data);
		out.println(gson_data);

		out.flush();
		out.close();
	}

}
