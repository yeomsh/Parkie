package JavaProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener, ItemListener {
	public static String u_name = null;
	JTextField id; // 로그인
	JPasswordField password;

	JTextField id1; // 관리자
	JPasswordField password1;

	JTextField id2; // 운전자
	JPasswordField password2;

	JTextField age;
	JTextField fee;

	JTextField basicFee;
	JTextField disName;
	JTextField disRate;

	JTextArea ta_dis;

	JTextField location;
	JTextField location2;

	JLabel l_id; // 로그인
	JLabel l_pw;

	JLabel l_id1; // 관리자
	JLabel l_pw1;

	JLabel l_id2; // 운전자
	JLabel l_pw2;
	JLabel l_age;
	JLabel l_gender;
	JLabel l_disabled;
	JLabel l_nation;
	JLabel l_car;
	JLabel l_login;
	JLabel l_fee;
	JLabel l_disInfo;
	JLabel l_basicFee;
	JLabel l_disName;
	JLabel l_disRate;
	JLabel l_location;
	JLabel l_location2;

	JButton btn_login;
	JButton btn_signup;
	JButton btn_admin, btn_user;
	JButton ad_enter, us_enter, log_enter;
	JButton btn_end; // 초기화면에서 나가기
	JButton[] btn_return;
	JButton btn_home1, btn_home2, btn_home3;
	JButton btn_parkManaging, btn_parkCurrent, btn_parkFee;
	JButton btn_park, btn_checkFee, btn_updateCar, btn_checkCar;
	JButton btn_small, btn_mid, btn_big, btn_cal;
	JButton btn_fee;
	JButton btn_location;

	JPanel pa_start; // 초기화면
	JPanel pa_login; // 로그인 화면
	JPanel pa_signup; // 회원가입화면
	JPanel pa_user; // 운전자 로그인
	JPanel pa_admin; // 운전자 회원가입
	JPanel pa_adminMenu; // 운전자메뉴 처음
	JPanel pa_userMenu; // 관리자메뉴 처음
	JPanel pa_updateCar; // 운전자 -> 차 갱신하기
	JPanel pa_checkFee; // 요금 계산
	JPanel pa_parkFee; // 관리자->주차요금 관리
	JPanel pa_userLocation;
	JPanel pa_logo;

	JCheckBox c_disabled1, c_disabled2;
	JCheckBox c_nation1, c_nation2;
	JCheckBox c_female, c_male;
	JCheckBox c_small, c_mid, c_big;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs = null;
	Map myframe;

	static String u_location;
	String ad_location;
	Vector<Fee> arr_fee = new Vector();

	public void connectDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:5306/userdb", "root", "1103");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		}
	}

	public void registerAdmin() {
		String sql = "insert into admininfo values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1" + id1.getText());
			pstmt.setString(2, password1.getText());

			pstmt.executeUpdate();

			id1.setText("");
			password1.setText("");

		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		}
	}

	public void registerUser() {
		String sql = "insert into userinfo values(?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "2" + id2.getText());
			pstmt.setString(2, password2.getText());
			pstmt.setString(3, age.getText());
			if (c_male.isSelected())
				pstmt.setInt(4, 0); // 남자
			else
				pstmt.setInt(4, 1);
			if (c_disabled1.isSelected())
				pstmt.setInt(5, 1);
			else
				pstmt.setInt(5, 0);
			if (c_nation1.isSelected())
				pstmt.setInt(6, 1);
			else
				pstmt.setInt(6, 0);
			if (c_small.isSelected())
				pstmt.setInt(7, 8);
			else if (c_mid.isSelected())
				pstmt.setInt(7, 5);
			else if (c_big.isSelected())
				pstmt.setInt(7, 2);

			pstmt.executeUpdate();

			id2.setText("");
			password2.setText("");
			age.setText("");
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		}
	}

	public boolean login() {

		String sql1 = "select * from userinfo where id=? and pw=?";
		String sql2 = "select * from admininfo where id=? and pw=?";
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id.getText());

			pstmt.setString(2, password.getText());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				id.setText("");
				password.setText("");

				return true;
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, id.getText());

				pstmt.setString(2, password.getText());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					id.setText("");
					password.setText("");
					return true;
				}
			}

		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } }

		}
		id.setText("");
		password.setText("");
		return false;

	}


	public void updateCar(int size) {
		String sql = "update userinfo set car=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, size);
			pstmt.setString(2, rs.getString(1));
			pstmt.executeUpdate();
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		}
	}

	public void addInfo() {
		if (!disName.getText().isEmpty())
			ta_dis.append(disName.getText() + "," + disRate.getText() + "%" + "\n");
	}

	public MyFrame() {
		this("주차친구 파키");
	}

	public MyFrame(String title) {
		super(title);
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 700);
		this.setVisible(true);
	}

	public void init() {

		btn_return = new JButton[9];

		for (int i = 0; i < 9; i++) {
			btn_return[i] = new JButton("뒤로가기");
			btn_return[i].setBackground(Color.CYAN);
			btn_return[i].setBounds(500, 500, 150, 80);
			btn_return[i].addActionListener(this);
		}

		// 초기화면
		pa_start = new JPanel();
		pa_start.setLayout(null);
		pa_start.setBackground(Color.GREEN);

		btn_login = new JButton("로그인");
		btn_login.setBackground(Color.WHITE);
		btn_login.addActionListener(this);
		btn_login.setBounds(230, 100, 200, 100);
		pa_start.add(btn_login);

		btn_signup = new JButton("회원가입");
		btn_signup.setBackground(Color.WHITE);
		btn_signup.addActionListener(this);
		btn_signup.setBounds(230, 300, 200, 100);
		pa_start.add(btn_signup);

		btn_end = new JButton("나가기");
		btn_end.setBackground(Color.WHITE);
		btn_end.addActionListener(this);
		btn_end.setBounds(400, 500, 200, 100);
		pa_start.add(btn_end);

		this.add(pa_start);

		// 로그인 눌렀을 때 화면

		pa_login = new JPanel();
		pa_login.setLayout(null);
		pa_login.setBackground(Color.green);

		l_id = new JLabel("아이디:");
		l_id.setBounds(230, 230, 200, 100);
		pa_login.add(l_id);

		l_pw = new JLabel("비밀번호: ");
		l_pw.setBounds(230, 280, 200, 100);
		pa_login.add(l_pw);

		id = new JTextField(10);
		id.setBounds(290, 270, 100, 30);
		pa_login.add(id);

		password = new JPasswordField(10);
		password.setBounds(290, 320, 100, 30);
		pa_login.add(password);

		l_id = new JLabel("관리자: 1 + 아이디, 운전자: 2 + 아이디");
		l_id.setBounds(230, 360, 600, 100);

		l_id.setForeground(Color.yellow);
		pa_login.add(l_id);

		log_enter = new JButton("로그인");
		log_enter.setBounds(400, 270, 150, 80);
		log_enter.addActionListener(this);
		pa_login.add(log_enter);

		pa_login.add(btn_return[0]);

		// 회원가입 눌렀을 때

		pa_signup = new JPanel();
		pa_signup.setLayout(null);
		pa_signup.setBackground(Color.green);

		btn_admin = new JButton("관리자");
		btn_admin.setBackground(Color.GREEN);
		btn_admin.setBounds(230, 100, 200, 100);
		btn_admin.addActionListener(this);
		pa_signup.add(btn_admin);

		btn_user = new JButton("사용자");
		btn_user.setBackground(Color.GREEN);
		btn_user.setBounds(230, 300, 200, 100);
		btn_user.addActionListener(this);
		pa_signup.add(btn_user);

		pa_signup.add(btn_return[1]);

		// 회원가입에서 관리자를 체크했을 때
		pa_admin = new JPanel();
		pa_admin.setLayout(null);
		pa_admin.setBackground(Color.green);

		l_id1 = new JLabel("아이디");
		l_id1.setBounds(230, 230, 200, 100);
		pa_admin.add(l_id1);

		l_pw1 = new JLabel("비밀번호");
		l_pw1.setBounds(230, 280, 200, 100);
		pa_admin.add(l_pw1);

		id1 = new JTextField();
		id1.setBounds(290, 270, 100, 30);
		pa_admin.add(id1);

		password1 = new JPasswordField();
		password1.setBounds(290, 320, 100, 30);
		pa_admin.add(password1);

		l_location2 = new JLabel("사용지역:");
		l_location2.setBounds(230, 365, 200, 100);
		pa_admin.add(l_location2);

		location2 = new JTextField(10);
		location2.setBounds(290, 400, 100, 30);
		pa_admin.add(location2);

		ad_enter = new JButton("가입하기");
		ad_enter.addActionListener(this);
		ad_enter.setBounds(400, 270, 150, 80);

		pa_admin.add(ad_enter);
		// 회원가입에서 운전자를 체크했을 때
		pa_user = new JPanel();
		pa_user.setLayout(null);
		pa_user.setBackground(Color.green);

		l_id2 = new JLabel("아이디:");
		l_id2.setBounds(230, 220, 200, 100);
		pa_user.add(l_id2);

		l_pw2 = new JLabel("비밀번호: ");
		l_pw2.setBounds(230, 260, 200, 100);
		pa_user.add(l_pw2);

		id2 = new JTextField(10);
		id2.setBounds(290, 250, 100, 30);
		pa_user.add(id2);

		password2 = new JPasswordField(10);
		password2.setBounds(290, 290, 100, 30);
		pa_user.add(password2);

		l_age = new JLabel("나이:");
		l_age.setBounds(230, 300, 200, 100);
		pa_user.add(l_age);

		l_gender = new JLabel("성별:");
		l_gender.setBounds(230, 350, 200, 100);
		pa_user.add(l_gender);

		age = new JTextField();
		age.setBounds(290, 330, 100, 30);
		pa_user.add(age);

		c_male = new JCheckBox("남");
		c_male.setBackground(Color.green);
		c_female = new JCheckBox("여");
		c_female.setBackground(Color.GREEN);
		c_male.setBounds(290, 370, 50, 50);
		c_female.setBounds(340, 370, 50, 50);

		ButtonGroup b_gender = new ButtonGroup();
		b_gender.add(c_male);
		b_gender.add(c_female);
		pa_user.add(c_male);
		pa_user.add(c_female);
		l_disabled = new JLabel("장애인 주차증 소지");
		l_disabled.setBounds(230, 410, 200, 100);
		pa_user.add(l_disabled);

		c_disabled1 = new JCheckBox("Y");
		c_disabled1.setBackground(Color.GREEN);
		c_disabled1.setBounds(350, 430, 50, 50);
		c_disabled1.addItemListener(this);
		pa_user.add(c_disabled1);

		c_disabled2 = new JCheckBox("N");
		c_disabled2.setBackground(Color.GREEN);
		c_disabled2.setBounds(400, 430, 50, 50);
		c_disabled2.addItemListener(this);
		pa_user.add(c_disabled2);

		l_nation = new JLabel("국가유공자 ");
		l_nation.setBounds(230, 450, 200, 100);
		pa_user.add(l_nation);

		c_nation1 = new JCheckBox("Y");
		c_nation1.setBackground(Color.GREEN);
		c_nation1.setBounds(350, 472, 50, 50);
		c_nation1.addItemListener(this);
		pa_user.add(c_nation1);

		c_nation2 = new JCheckBox("N");
		c_nation2.setBackground(Color.GREEN);
		c_nation2.setBounds(400, 472, 50, 50);
		c_nation2.addItemListener(this);
		pa_user.add(c_nation2);

		l_car = new JLabel("차량 크기 ");
		l_car.setBounds(230, 490, 200, 100);
		pa_user.add(l_car);

		c_big = new JCheckBox("대");
		c_big.setBackground(Color.GREEN);
		c_big.setBounds(350, 510, 50, 50);
		c_big.addItemListener(this);
		pa_user.add(c_big);

		c_mid = new JCheckBox("중");
		c_mid.setBackground(Color.GREEN);
		c_mid.setBounds(400, 510, 50, 50);
		c_mid.addItemListener(this);
		pa_user.add(c_mid);

		c_small = new JCheckBox("소");
		c_small.setBackground(Color.GREEN);
		c_small.setBounds(450, 510, 50, 50);
		c_small.addItemListener(this);
		pa_user.add(c_small);

		ButtonGroup b_dis = new ButtonGroup();
		b_dis.add(c_disabled1);
		b_dis.add(c_disabled2);

		ButtonGroup b_nation = new ButtonGroup();
		b_nation.add(c_nation1);
		b_nation.add(c_nation2);

		ButtonGroup b_car = new ButtonGroup();
		b_car.add(c_small);
		b_car.add(c_mid);
		b_car.add(c_big);

		us_enter = new JButton("가입하기");
		us_enter.setBounds(400, 340, 150, 80);
		us_enter.addActionListener(this);
		pa_user.add(us_enter);

		// 관리자로 로그인했을 때 관리자 초기 화면
		pa_adminMenu = new JPanel();
		pa_adminMenu.setLayout(null);
		pa_adminMenu.setBackground(Color.GREEN);

		btn_parkManaging = new JButton("주차장 시설 관리");
		btn_parkManaging.addActionListener(this);
		btn_parkManaging.setBounds(230, 100, 200, 100);
		pa_adminMenu.add(btn_parkManaging);

		btn_parkCurrent = new JButton("주차장 현황");
		btn_parkCurrent.addActionListener(this);
		btn_parkCurrent.setBounds(230, 300, 200, 100);
		pa_adminMenu.add(btn_parkCurrent);

		btn_parkFee = new JButton("주차장 요금 관리");
		btn_parkFee.addActionListener(this);
		btn_parkFee.setBounds(230, 500, 200, 100);
		pa_adminMenu.add(btn_parkFee);

		pa_adminMenu.add(btn_return[2]);

		// 관리자 로그인-> 주차장 요금 관리
		pa_parkFee = new JPanel();
		pa_parkFee.setLayout(null);
		pa_parkFee.setBackground(Color.GREEN);

		l_basicFee = new JLabel("기본요금:");
		l_basicFee.setBounds(230, 120, 200, 100);
		pa_parkFee.add(l_basicFee);

		basicFee = new JTextField(10);
		basicFee.setBounds(300, 155, 100, 30);
		pa_parkFee.add(basicFee);

		l_disInfo = new JLabel("할인정보");
		l_disInfo.setBounds(230, 250, 200, 100);
		pa_parkFee.add(l_disInfo);

		l_disName = new JLabel("이름:");
		l_disName.setBounds(230, 300, 200, 100);
		pa_parkFee.add(l_disName);

		disName = new JTextField(10);
		disName.setBounds(290, 335, 100, 30);
		pa_parkFee.add(disName);

		l_disRate = new JLabel("할인율:");
		l_disRate.setBounds(230, 390, 200, 100);
		pa_parkFee.add(l_disRate);

		disRate = new JTextField(10);
		disRate.setBounds(290, 430, 100, 30);
		pa_parkFee.add(disRate);

		ta_dis = new JTextArea();
		ta_dis.setBounds(450, 280, 80, 300);
		pa_parkFee.add(ta_dis);

		btn_fee = new JButton("등록");
		btn_fee.addActionListener(this);
		btn_fee.setBounds(230, 480, 200, 100);
		pa_parkFee.add(btn_fee);

		// 운전자로 로그인했을 때 사용지역 설정
		pa_userLocation = new JPanel();
		pa_userLocation.setLayout(null);
		pa_userLocation.setBackground(Color.GREEN);

		l_location = new JLabel("사용지역:");
		l_location.setBounds(230, 150, 200, 100);
		pa_userLocation.add(l_location);

		location = new JTextField(10);
		location.setBounds(300, 180, 100, 30);
		pa_userLocation.add(location);

		btn_location = new JButton("등록");
		btn_location.addActionListener(this);
		btn_location.setBounds(230, 300, 200, 100);
		pa_userLocation.add(btn_location);
		// 운전자로 로그인했을 때 초기 화면
		pa_userMenu = new JPanel();
		pa_userMenu.setLayout(null);
		pa_userMenu.setBackground(Color.GREEN);

		btn_park = new JButton("주차하기");
		btn_park.addActionListener(this);
		btn_park.setBounds(230, 50, 200, 100);
		pa_userMenu.add(btn_park);

		btn_checkCar = new JButton("차량 위치 조회");
		btn_checkCar.addActionListener(this);
		btn_checkCar.setBounds(230, 200, 200, 100);
		pa_userMenu.add(btn_checkCar);

		btn_checkFee = new JButton("주차요금 조회");
		btn_checkFee.addActionListener(this);
		btn_checkFee.setBounds(230, 350, 200, 100);
		pa_userMenu.add(btn_checkFee);

		btn_updateCar = new JButton("차 정보 갱신");
		btn_updateCar.addActionListener(this);
		btn_updateCar.setBounds(230, 500, 200, 100);
		pa_userMenu.add(btn_updateCar);

		btn_return[3].setText("출차하기");
		pa_userMenu.add(btn_return[3]);

		// 운전자 로그인 -> 차정보 갱신 눌렀을 때
		pa_updateCar = new JPanel();
		pa_updateCar.setLayout(null);
		pa_updateCar.setBackground(Color.GREEN);

		btn_small = new JButton("소형");
		btn_small.addActionListener(this);
		btn_small.setBounds(230, 100, 200, 100);
		pa_updateCar.add(btn_small);

		btn_mid = new JButton("중형");
		btn_mid.addActionListener(this);
		btn_mid.setBounds(230, 300, 200, 100);
		pa_updateCar.add(btn_mid);

		btn_big = new JButton("대형");
		btn_big.addActionListener(this);
		btn_big.setBounds(230, 500, 200, 100);
		pa_updateCar.add(btn_big);

		// 운전자 로그인 -> 요금계산
		pa_checkFee = new JPanel();
		pa_checkFee.setLayout(null);
		pa_checkFee.setBackground(Color.GREEN);

		btn_cal = new JButton("계산");
		btn_cal.addActionListener(this);
		btn_cal.setBounds(400, 250, 100, 50);
		pa_checkFee.add(btn_cal);

		l_fee = new JLabel("이용 시간 ");
		l_fee.setBounds(230, 250, 100, 50);
		pa_checkFee.add(l_fee);
		pa_checkFee.add(btn_return[4]);

		fee = new JTextField();
		fee.setBounds(300, 250, 100, 30);
		pa_checkFee.add(fee);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btn_login) {
			pa_start.hide();
			this.add(pa_login);
			pa_login.show();
		} else if (e.getSource() == btn_signup) {
			pa_start.hide();
			this.add(pa_signup);
			pa_signup.show();

		} else if (e.getSource() == log_enter) {
			connectDB();
			if (login()) {
				try {
					if (rs.getString(1).charAt(0) == '1') {
						pa_login.hide();
						this.add(pa_adminMenu);
						pa_adminMenu.show();
					} else {
						pa_login.hide();
						u_name = id.getText();
						this.add(pa_userLocation);
						pa_userLocation.show();

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		} else if (e.getSource() == btn_location) {
			u_location = location.getText();
			pa_userLocation.hide();
			this.add(pa_userMenu);
			pa_userMenu.show();

		} else if (e.getSource() == us_enter)

		{
			connectDB();
			registerUser();

			pa_user.hide();
			pa_start.show();

		} else if (e.getSource() == ad_enter) {
			connectDB();
			registerAdmin();
			ad_location = location2.getText();

			pa_admin.hide();
			pa_start.show();
		} else if (e.getSource() == btn_end) {
			System.exit(EXIT_ON_CLOSE);
		} else if (e.getSource() == btn_admin) {
			pa_signup.hide();
			this.add(pa_admin);
			pa_admin.show();
			// 관리자라면 아이디 맨 앞에 1 넣어주기
		} else if (e.getSource() == btn_user) {
			pa_signup.hide();
			this.add(pa_user);
			pa_user.show();
		} else if (e.getSource() == btn_return[0]) {
			pa_login.hide();
			pa_start.show();
		} else if (e.getSource() == btn_return[1]) {
			pa_signup.hide();
			pa_start.show();
		} else if (e.getSource() == btn_return[2]) {
			pa_adminMenu.hide();
			pa_start.show();
		} else if (e.getSource() == btn_return[3]) {
			pa_userMenu.hide();
			U_Map.car[U_Map.s_x][U_Map.s_y] = 10;
			pa_start.show();
		} else if (e.getSource() == btn_return[4]) {
			pa_checkFee.hide();
			pa_userMenu.show();
		} else if (e.getSource() == btn_parkManaging) { 
			myframe = new Map(location2.getText());
		} else if (e.getSource() == btn_parkCurrent) {
			pa_adminMenu.hide();
			try {
				Check_car checkcar = new Check_car(location2.getText(), false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pa_adminMenu.show();
		} else if (e.getSource() == btn_parkFee) {
			pa_adminMenu.hide();
			this.add(pa_parkFee);
			pa_parkFee.show();

		} else if (e.getSource() == btn_park) { 
			pa_userMenu.hide();
			int car_size = 5;
			try {
				U_Map userframe = new U_Map(location.getText(), car_size);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pa_userMenu.show();
		} else if (e.getSource() == btn_checkFee) {
			pa_userMenu.hide();
			this.add(pa_checkFee);
			pa_checkFee.show();
		} else if (e.getSource() == btn_updateCar) {
			pa_userMenu.hide();
			this.add(pa_updateCar);
			pa_updateCar.show();
		} else if (e.getSource() == btn_checkCar) {
			pa_userMenu.hide();
			try {
				Check_car checkcar = new Check_car(location.getText(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pa_userMenu.show();
		} else if (e.getSource() == btn_small) {
			updateCar(8);
			pa_updateCar.hide();
			pa_userMenu.show();
		} else if (e.getSource() == btn_mid) {
			updateCar(5);
			pa_updateCar.hide();
			pa_userMenu.show();
		} else if (e.getSource() == btn_big) {
			updateCar(2);
			pa_updateCar.hide();
			pa_userMenu.show();
		} else if (e.getSource() == btn_fee) {
			int t_fee;
			t_fee = Integer.parseInt(basicFee.getText());
			basicFee.setText("");
			
			addInfo();
			pa_parkFee.hide();
			pa_adminMenu.show();
		} else if (e.getSource() == btn_cal) {
			int t_time;
			int temp = -1;
			int total_fee;
			String stotal_fee;
			t_time = Integer.parseInt(fee.getText()); 
			if (arr_fee.size() > 0) {
				for (int i = 0; i < arr_fee.size(); i++) {
					if (u_location == arr_fee.elementAt(i).getLocation())
						temp = i;
				}
				if (temp != -1) {
					total_fee = arr_fee.elementAt(temp).getFee() * t_time;
					stotal_fee = Integer.toString(total_fee);
					fee.setText((stotal_fee));
				} else {
					total_fee = 2000 * t_time;
					stotal_fee = Integer.toString(total_fee);
					fee.setText((stotal_fee));
				}
			} else {
				total_fee = 2000 * t_time;
				stotal_fee = Integer.toString(total_fee);
				fee.setText((stotal_fee));
			}
		
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == c_female) {

		} else if (e.getSource() == c_male) {

		}

		else if (e.getSource() == c_disabled1) {

		} else if (e.getSource() == c_disabled2) {

		} else if (e.getSource() == c_nation1) {

		} else if (e.getSource() == c_nation2) {

		}

	}

}