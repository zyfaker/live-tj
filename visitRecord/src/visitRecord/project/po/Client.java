package visitRecord.project.po;

public class Client {
	private int id;
	private int order;
	private String name;
	private String phone;
	private String gender;
	private String   date;
	private String teambelong;
	private String kownway;
	private String counselor;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTeambelong() {
		return teambelong;
	}
	public void setTeambelong(String teambelong) {
		this.teambelong = teambelong;
	}
	public String getKownway() {
		return kownway;
	}
	public void setKownway(String kownway) {
		this.kownway = kownway;
	}
	public String getCounselor() {
		return counselor;
	}
	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Client() {
		super();
	}
	public Client(int id, int order, String name, String phone, String gender,
			String date, String teambelong, String kownway, String counselor,
			String remark) {
		super();
		this.id = id;
		this.order = order;
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.date = date;
		this.teambelong = teambelong;
		this.kownway = kownway;
		this.counselor = counselor;
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", order=" + order + ", name=" + name
				+ ", phone=" + phone + ", gender=" + gender + ", date=" + date
				+ ", teambelong=" + teambelong + ", kownway=" + kownway
				+ ", counselor=" + counselor + ", remark=" + remark + "]";
	}


}
