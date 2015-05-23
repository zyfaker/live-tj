package edu.nankai.VisitRecord.classes;                                                       
                                                                                   
import java.io.Serializable;                                                                                                                    
import java.util.Date;
                                                                                   
public class Users implements Serializable {                                        
                                                                                   
	private int id;                                                                
	private String name;                                                           
	private String phone;                                                          
	private String belong;                                                         
	private String guwen;                                                          
	private Date date;                                                             
	private String beizhu; 
	private String know;
	private Date date2;
	private Date date3;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String name, String phone, String belong,
			String guwen, Date date, String beizhu, String know, Date date2,
			Date date3) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.belong = belong;
		this.guwen = guwen;
		this.date = date;
		this.beizhu = beizhu;
		this.know = know;
		this.date2 = date2;
		this.date3 = date3;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
	public String getGuwen() {
		return guwen;
	}
	public void setGuwen(String guwen) {
		this.guwen = guwen;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getKnow() {
		return know;
	}
	public void setKnow(String know) {
		this.know = know;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", belong=" + belong + ", guwen=" + guwen + ", date=" + date
				+ ", beizhu=" + beizhu + ", know=" + know + ", date2=" + date2
				+ ", date3=" + date3 + "]";
	}
	
	
	                                                 
                                                                                   
}                                                                                  
                                                                                   