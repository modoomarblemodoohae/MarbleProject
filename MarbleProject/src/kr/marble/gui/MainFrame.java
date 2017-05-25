package kr.marble.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	
	private JButton[] btns = new JButton[32];
	private JButton okBtn, noBtn, diceBtn;
	
	private JTextField[] fields = new JTextField[4];
	private JLabel[] labels = new JLabel[4];
	
	public MainFrame() {
		super("-- 부루마블 게임 --");
		
		setSize(1200, 900);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
