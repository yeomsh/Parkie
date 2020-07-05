package JavaProject;

import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Check_car extends JFrame {
	protected static JButton button[][];
	protected static int map[][];
	private JPanel panel1;
	Container container;
	private Color now;
	private String name;
	private boolean bool;

	public Check_car(String title) throws IOException {
		super(title);
		name = title;
		init();
		bool = true;

		this.setVisible(true);
		this.setSize(1500, 1000);
	}

	public Check_car(String title, boolean bool) throws IOException {
		super(title);
		name = title;
		this.bool = bool;
		init();
		
		this.setVisible(true);
		this.setSize(1500, 1000);
	}

	public void init() throws IOException {
		panel1 = new JPanel(null);
		container = this.getContentPane();
		map = new int[10][15];
		button = new JButton[10][15];

		BufferedReader br = new BufferedReader(new FileReader(name + ".txt"));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				String line = br.readLine();
				map[i][j] = Integer.parseInt(line, 10);
				
			}
		}
		br.close();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
				button[i][j] = new JButton("");
				button[i][j].setBackground(Color.WHITE);
				button[i][j].setSize(80, 80);
				button[i][j].setLocation(80 * j + 50, 80 * i + 105);
				panel1.add(button[i][j]);

			}
		}
		now = Color.WHITE;
		MakePark();
		if (bool != false)
			button[U_Map.s_x][U_Map.s_y].setBackground(Color.RED);
	}

	public void MakePark() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 15; j++) {
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
				button[i][j].setBackground(now);
			}
		}
		container.add(panel1);
	}

}