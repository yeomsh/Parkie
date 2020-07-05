package JavaProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class U_Map extends JFrame implements ActionListener {
	private String name = null;
	protected static JButton button[][];
	protected static int map[][];
	protected static int car[][];
	private JButton find;
	private JPanel panel1;
	Container container;
	public static int s_x;
	public static int s_y;
	private int car_size;
	private Color now;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs = null;

	int gender;
	int disabled;
	int mycar;

	public U_Map(String title, int car_size) throws IOException {
		super(title);
		name = title;
		this.car_size = car_size;
		init();
	
		this.setVisible(true);
		this.setSize(1500, 1000);
	}

	public void connectDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:5306/userdb", "root", "1103");
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) { // TODO Auto-generated catch block
		}
	}

	public void userInit() throws SQLException {
		String sql = "select * from userinfo where id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, MyFrame.u_name);
		rs = pstmt.executeQuery();
		gender = rs.getInt(4);
		disabled = rs.getInt(5);
		mycar = rs.getInt(7);
	}

	public void recommend() {

		int[][] arrcar = new int[10][15];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (car[i][j] != 0) {
					if (j == 0) {
						arrcar[i][j] += car[i][j + 1];
					}
					if (j == 14) {
						arrcar[i][j] += car[i][j - 1];
					} else
						arrcar[i][j] += car[i][j - 1] + car[i][j + 1];
					if (gender == 1) {
						if ((map[i][j] / 10) == 0x101) {
							arrcar[i][j] += 50;
							button[i][j].setText("추천");
						}
					}
					if (disabled == 1) {
						if ((map[i][j] / 10) == 0x102) {
							arrcar[i][j] += 100;
							button[i][j].setText("추천");
						}
					}
				}
			}

		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (disabled == 1) {
					if (arrcar[i][j] >= 100)
						button[i][j].setText("추천");
				} else if (gender == 1) {
					if (arrcar[i][j] >= 50)
						button[i][j].setText("추천");
				} else {
					if (mycar == 8) {
						if (arrcar[i][j] >= 20)
							button[i][j].setText("추천");
					}
					if (mycar == 5) {
						if (arrcar[i][j] >= 20)
							button[i][j].setText("추천");

					} else {
						if (arrcar[i][j] >= 17)
							button[i][j].setText("추천");
					}

				}
			}
		}

	}

	public void init() throws IOException {
		find = new JButton("자리 선정하기");
		find.setBounds(1300, 10, 150, 50);
		find.addActionListener(this);
		panel1 = new JPanel(null);
		panel1.add(find);
		container = this.getContentPane();
		s_x = 0;
		s_y = 0;
		map = new int[10][15];
		car = new int[10][15];
		button = new JButton[10][15];

		BufferedReader br = new BufferedReader(new FileReader(name + ".txt"));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				String line = br.readLine();
				map[i][j] = Integer.parseInt(line, 10);

			}
		}
		br.close();

		BufferedReader br2 = new BufferedReader(new FileReader(name + "car.txt"));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				String line = br2.readLine();
				car[i][j] = Integer.parseInt(line);

			}
		}
		br2.close();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				button[i][j] = new JButton("");
				button[i][j].setBackground(Color.WHITE);
				button[i][j].setSize(80, 80);
				button[i][j].setLocation(80 * j + 50, 80 * i + 105);
				panel1.add(button[i][j]);
				button[i][j].addActionListener(this);

			}
		}
		now = Color.WHITE;
		MakePark();
		recommend();
	}

	public void MakePark() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (car[i][j] != 0 && car[i][j] != 10)
					now = Color.gray;
				else {
					if (map[i][j] == 0x000)
						now = Color.WHITE;
					else if (map[i][j] == 0x304)
						now = Color.PINK;
					else if ((map[i][j] / 0x10) == 0x103)
						now = Color.GREEN;
					else if ((map[i][j] / 0x10) == 0x101)
						now = new Color(155, 255, 148);
					else if ((map[i][j] / 0x10) == 0x102)
						now = new Color(0, 183, 0);
					else if (map[i][j] == 0x301)
						now = Color.CYAN;
					else if (map[i][j] == 0x303)
						now = Color.YELLOW;
					else if (map[i][j] == 0x302)
						now = Color.ORANGE;
				}
				button[i][j].setBackground(now);

			}
		}
		container.add(panel1);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == find) {
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 15; j++)
					button[i][j].setEnabled(false);
			int a, b;
			a = s_x;
			b = s_y;
			Astar star = new Astar(a, b);

			star.findWay();
			star.sort();
			car[s_x][s_y] = car_size;
			String str = null;

		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				if (arg0.getSource() == button[i][j] && (map[i][j] / 0x1000) == 1) {
					button[s_x][s_y].setText("");
					button[i][j].setText("●");
					s_x = i;
					s_y = j;
				}
			}
		}
	}
}