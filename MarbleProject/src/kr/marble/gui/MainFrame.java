package kr.marble.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.marble.Manager;

public class MainFrame extends JFrame {
	
	private JButton startBtn, closeBtn;
	private JLabel label;
	
	private MainFrame frame;
	
	public MainFrame() {
		super("Marble Project");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		frame = this;
		
		startBtn = new JButton("게임 시작");
		closeBtn = new JButton("게임 종료");
		label = new JLabel(new ImageIcon("image/logo.png"));
		
		label.setBounds(0, 0, getWidth(), 200);
		startBtn.setBounds((getWidth() / 2) - 60, 220, 120, 50);
		closeBtn.setBounds((getWidth() / 2) - 60, 280, 120, 50);
		
		add(label);
		add(startBtn);
		add(closeBtn);
		
		setUndecorated(true);
		getContentPane().setBackground(new Color(190, 240, 255));
		
		startBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startBtn.setEnabled(false);
				JDialog dialog = new PlayerNameDialog();
			}
		});
		
		closeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		setVisible(true);
	}
	
	class PlayerNameDialog extends JDialog {

		private JTextField[] fields = new JTextField[4];
		private JLabel[] labels = new JLabel[4];
		private String[] viewPlayer = {"Player1", "Player2", "Player3", "Player4"};
		private Font font = new Font("한컴 윤체 B", Font.BOLD, 15);
		private JButton checkBtn = new JButton("게임으로 이동");
		
		public PlayerNameDialog() {
			super(frame, "플레이어 이름을 입력해주세요");
			setSize(400, 200);
			setLocationRelativeTo(null);
			setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
			
			for(int i = 0; i < viewPlayer.length; i ++) {
				labels[i] = new JLabel(viewPlayer[i]+"\t");
				fields[i] = new JTextField(15);
				
				labels[i].setFont(font);
				fields[i].setFont(font);
				
				add(labels[i]);
				add(fields[i]);
			}
			add(checkBtn);
			
			checkBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String name[] = new String[4];
					for(int i = 0; i < fields.length; i ++) {
						if(fields[i].getText().equalsIgnoreCase("")) return;
						name[i] = new String(fields[i].getText());
					}
					Manager.getInstance().initPlayer(name);
					Manager.getInstance().init();
					new GameMap();
					setVisible(false);
				}
			});
			
			setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		
		new MainFrame();
	}
}
