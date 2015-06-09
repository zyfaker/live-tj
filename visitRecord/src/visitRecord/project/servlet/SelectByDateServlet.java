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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import visitRecord.project.po.Client;
import visitRecord.project.po.Transaction;

/**
 * Servlet implementation class SelectByDateServlet
 */
public class SelectByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectByDateServlet() {
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
		String date = gson.fromJson(request.getParameter("date"), String.class);

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = null;
//		try {
//			d = sdf.parse(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		Transaction transaction = new Transaction();
		List<Client> lstclient = transaction.selectByDate(date);
		
		// -------------------响应客户端 JSon----------------

		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String gson_data = gson.toJson(lstclient);
		
		System.out.println(gson_data);
		out.println(gson_data);

		out.flush();
		out.close();
	}

}
