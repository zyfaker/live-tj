package visitRecord.project.po;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visitRecord.project.db.ConnectionManager;
import visitRecord.project.db.DBUtils;
import visitRecord.project.db.TransactionManager;

public class Transaction {
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	public Transaction() {
		super();
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}
	
	public List<Client> selectAllUser() {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		List<Client> lstUser = new ArrayList<Client>();
		String strSQL = "select * from client";
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, new Object[] {});
		// 获取查询结果
		try {
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setOrder(resultSet.getInt("order"));
				client.setName(resultSet.getString("name"));
				client.setPhone(resultSet.getString("phone"));
				client.setTeambelong(resultSet.getString("teambelong"));
				client.setKownway(resultSet.getString("knowway"));
				client.setCounselor(resultSet.getString("counselor"));
				client.setRemark(resultSet.getString("remark"));
				client.setGender(resultSet.getString("gender"));
				client.setDate(resultSet.getString("date"));
				
				lstUser.add(client);

			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
	}
	
	public List<Client> selectByOrder(int order) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		List<Client> lstUser = new ArrayList<Client>();
		String strSQL = "select * from client where order = ?";
		Object[] params = new Object[] { order };
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, params);
		// 获取查询结果
		try {
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setOrder(resultSet.getInt("order"));
				client.setName(resultSet.getString("name"));
				client.setPhone(resultSet.getString("phone"));
				client.setTeambelong(resultSet.getString("teambelong"));
				client.setKownway(resultSet.getString("knowway"));
				client.setCounselor(resultSet.getString("counselor"));
				client.setRemark(resultSet.getString("remark"));
				client.setGender(resultSet.getString("gender"));
				client.setDate(resultSet.getString("date"));
				
				lstUser.add(client);

			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
	}

	public List<Client> selectByDate(String date) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		List<Client> lstUser = new ArrayList<Client>();
		String strSQL = "select * from client where date like '%"+date+"%'";
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, new Object[] {});
		// 获取查询结果
		try {
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setOrder(resultSet.getInt("order"));
				client.setName(resultSet.getString("name"));
				client.setPhone(resultSet.getString("phone"));
				client.setTeambelong(resultSet.getString("teambelong"));
				client.setKownway(resultSet.getString("knowway"));
				client.setCounselor(resultSet.getString("counselor"));
				client.setRemark(resultSet.getString("remark"));
				client.setGender(resultSet.getString("gender"));
				client.setDate(resultSet.getString("date"));
				
				lstUser.add(client);

			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
	}
	
	public List<Client> selectByCounselor(String counselor) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		List<Client> lstUser = new ArrayList<Client>();
		String strSQL = "select * from client where counselor like '%"+counselor+"%'";
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, new Object[] {});
		// 获取查询结果
		try {
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setOrder(resultSet.getInt("order"));
				client.setName(resultSet.getString("name"));
				client.setPhone(resultSet.getString("phone"));
				client.setTeambelong(resultSet.getString("teambelong"));
				client.setKownway(resultSet.getString("knowway"));
				client.setCounselor(resultSet.getString("counselor"));
				client.setRemark(resultSet.getString("remark"));
				client.setGender(resultSet.getString("gender"));
				client.setDate(resultSet.getString("date"));
				
				lstUser.add(client);

			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
	}
	
	public List<Client> selectByName(String name) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		List<Client> lstUser = new ArrayList<Client>();
		String strSQL = "select * from client where name like '%"+name+"%'";
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, new Object[] {});
		// 获取查询结果
		try {
			while (resultSet.next()) {
				Client client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setOrder(resultSet.getInt("order"));
				client.setName(resultSet.getString("name"));
				client.setPhone(resultSet.getString("phone"));
				client.setTeambelong(resultSet.getString("teambelong"));
				client.setKownway(resultSet.getString("knowway"));
				client.setCounselor(resultSet.getString("counselor"));
				client.setRemark(resultSet.getString("remark"));
				client.setGender(resultSet.getString("gender"));
				client.setDate(resultSet.getString("date"));
				
				lstUser.add(client);

			}
			return lstUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}
	}
	
	public boolean insert(Client client) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();
		// 拆分属性
		//int id = client.getId();
		int order = client.getOrder();
		String name = client.getName();
		String phone = client.getPhone();
		String teambelong = client.getTeambelong();
		String knowway = client.getKownway();
		String counselor = client.getCounselor();
		String remark = client.getRemark();
		String gender = client.getGender();
		String date = client.getDate();


		String strSQL = "insert into client value(null,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { order, name, phone, teambelong, knowway,
				counselor, remark, gender, date};
		int affectedRows = dbUtils.execOthers(conn, strSQL, params);
		if (affectedRows > 0) {
			TransactionManager.commit();
		} else {
			TransactionManager.rollback();
		}
		return affectedRows>0 ? true:false;
	}
	
	public int deleteByOrder(int order) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();

		String strSQL = "delete from client where order=?";
		Object[] params = new Object[] { order };
		int affectedRows = dbUtils.execOthers(conn, strSQL, params);
		if (affectedRows > 0) {
			TransactionManager.commit();
		} else {
			TransactionManager.rollback();
		}
		return affectedRows;
	}
	
	public boolean updateClient(Client client) {
		// TODO Auto-generated method stub
		// 获取链接对象
		Connection conn = this.connectionManager.openConnection();
		TransactionManager.conn = conn;
		// 开始事务管理
		TransactionManager.beginTransaction();
		// 拆分属性
		int id = client.getId();
		int order = client.getOrder();
		String name = client.getName();
		String phone = client.getPhone();
		String teambelong = client.getTeambelong();
		String knowway = client.getKownway();
		String counselor = client.getCounselor();
		String remark = client.getRemark();
		String gender = client.getGender();
		String date = client.getDate();
		
		String strSQL = "update client set order=?,name=?,phone=?,teambelong=?,knowway=?,counselor=?,remark=?,gender=?,date=? where id=?";
		Object[] params = new Object[] {order, name, phone, teambelong, knowway,
				counselor, remark, gender, date, id };
		int affectedRows = dbUtils.execOthers(conn, strSQL, params);
		if (affectedRows > 0) {
			TransactionManager.commit();
		} else {
			TransactionManager.rollback();
		}
		return affectedRows>0 ? true:false;																																			
	}
}
