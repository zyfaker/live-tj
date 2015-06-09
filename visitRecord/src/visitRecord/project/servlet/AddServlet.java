package visitRecord.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class AddServlet
 */
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String addclient = request.getParameter("client_data");
		System.out.println(addclient);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm").create();
		
		Client c = gson.fromJson(addclient, Client.class);
		

		
		Client client = new Client();
		client.setOrder(c.getOrder());
		client.setName(c.getName());
		client.setPhone(c.getPhone());
		client.setTeambelong(c.getTeambelong());
		client.setKownway(c.getKownway());
		client.setCounselor(c.getCounselor());
		client.setRemark(c.getRemark());
		client.setGender(c.getGender());
		client.setDate(c.getDate());
		
		Transaction transaction = new Transaction();
		boolean flag = transaction.insert(client);

		// -------------------响应客户端 JSon----------------

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String gson_data = gson.toJson(flag);
		
		System.out.println(gson_data);
		out.println(gson_data);

		out.flush();
		out.close();
	}

}
