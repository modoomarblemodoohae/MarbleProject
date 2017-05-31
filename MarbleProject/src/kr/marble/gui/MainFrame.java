package kr.marble.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kr.marble.IManagerListener;
import kr.marble.Manager;
import kr.marble.Player;
import kr.marble.building.Building;
import kr.marble.building.IBuildingListener;
import kr.marble.goldcard.GoldCard;
import kr.marble.goldcard.IGoldCardListener;

public class MainFrame extends JFrame {
	
	private JButton okBtn, noBtn, diceBtn;
	
	private JTextField[] fields = new JTextField[4];
	private JLabel[] labels = new JLabel[4];
	
	private JButton button[] = new JButton[32];
	private JTextArea area = new JTextArea();
	private JScrollPane pane;
	private ButtonGroup group = new ButtonGroup();
	private Manager manager = Manager.getInstance();
	
	private final static int WIDTH = 75;
	private final static int HEIGHT = 133;
	
	private int x = 700;
	private int y = 680; 
	
	public MainFrame(){
		button = new JButton[32];
		setTitle("Marble Project");
		setSize(1500,900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		manager.init();
		
		createMap();
		createRight();

		setVisible(true);
	}
	
	public void createMap() {
		for(int i = 0; i < 4; i++) {
			for(int j = i * (button.length / 4); j <= (i * (button.length / 4)) + 7; j ++){
				button[j] = new JButton(new ImageIcon("C:/workspace/MarbleProject/image/" + j + ".png"));
				button[j].setBorderPainted(false);
				button[j].setContentAreaFilled(false);
				//button[j].setFocusPainted(false);
				if(i == 0) {
					if(j == 0)
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
					else
						button[j].setBounds(x, y, WIDTH, HEIGHT);
					if(j == 7)
						x -= HEIGHT;
					else
						x -= WIDTH;
				} else if(i == 1) {
					if(j == 8)
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
					else 
						button[j].setBounds(x, y, HEIGHT, WIDTH);
					if(j == 15) 
						y -= HEIGHT;
					else
						y -= WIDTH;
				} else if(i == 2) {
					if(j == 16) {
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
						x += HEIGHT;
					} else {
						button[j].setBounds(x, y, WIDTH, HEIGHT);
						x += WIDTH;
					}
				} else {
					if(j == 24) {
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
						y += HEIGHT;
					} else {
						button[j].setBounds(x, y, HEIGHT, WIDTH);
						y += WIDTH;
					}
				}
				button[j].addActionListener(new CityButtonListener(this, j));
				add(button[j]);
			}
		}
	}
	
	public void createRight() {
		Font font = new Font("����ü", Font.PLAIN, 15);
		pane = new JScrollPane(area);
		pane.setBounds(850, 30, 600, 300);
		
		area.setEditable(false);
		area.setFont(font);
		
		area.setText(log("Hello World"));
		
		add(pane);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public String log(String str) {
		Calendar calendar = Calendar.getInstance();
		String month = String.format("[%02d��%02d�� %02d:%02d:%02d] %s", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR)
				, calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), str);
		return month;
	}
	
	class EventListener implements IManagerListener, IBuildingListener, IGoldCardListener {

		@Override
		public void onSelectGoldCard(Player player) { // Ȳ��ī�带 �������� ������
			
		}

		@Override
		public void onBuyingBuilding(Building building, Player player) { // �ǹ��� ����������
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onJoinWho(Building building, Player player) { // �ǹ��� ���� ��������
			// TODO Auto-generated method stub
			// �ǹ��� �����ڰ� ���� �� 
		}

		@Override
		public void onUsingFromBuilding(Building building, GoldCard card) { // Ȳ��ī�带 ������ �߶�����
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onJoinIsLand(Player player) { // ���ε��� ��������
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGiveDonate(Player player) { // ��α��� �޾�����
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSpaceTravel(Player player) { // ���ֿ���
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onReceiveDonate(Player player, double donate) { // ��α� ������
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNoMoneyPlayer(Player player) { // �Ļ��� �Ǿ�����
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class CityButtonListener implements ActionListener {

		private int idx;
		private MainFrame frame;
		
		public CityButtonListener(MainFrame frame, int idx) {
			this.idx = idx;
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(idx % 4 == 0) return;
			new BuildingDialog(frame, manager.getBuilding(idx));
		}
		
	}
}
