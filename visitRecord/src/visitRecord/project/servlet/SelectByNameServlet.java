package visitRecord.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import visitRecord.project.po.Client;
import visitRecord.project.po.Transaction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class TransactionServlet
 */
public class SelectByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectByNameServlet() {
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

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm").create();
		String name = gson.fromJson(request.getParameter("clientName"), String.class);
		// 根据sportid找相应内容
		Transaction transaction = new Transaction();
		List<Client> lstCient = transaction.selectByName(name);
		System.out.println(lstCient.toString());
		// -------------------响应客户端 JSon----------------
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String lstclient = gson.toJson(lstCient);
		
		System.out.println(lstclient);
		out.println(lstclient);

		out.flush();
		out.close();

	}

}
