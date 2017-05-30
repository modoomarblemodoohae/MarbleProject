package kr.marble.gui;

import java.awt.Container; 
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {
	
	private JButton okBtn, noBtn, diceBtn;
	
	private JTextField[] fields = new JTextField[4];
	private JLabel[] labels = new JLabel[4];
	
	private JButton button[] = new JButton[32];
	
	private final static int WIDTH = 70;
	private final static int HEIGHT = 140;
	
	private int x = 1000;
	private int y = 700; 
	
	public MainFrame(){
		button = new JButton[32];
		setTitle("asd");
		setSize(1500,900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c= getContentPane();
		c.setLayout(null);
		
		for(int i = 0; i < 4; i++) {
			for(int j = i * (button.length / 4); j <= (i * (button.length / 4)) + 7; j ++){
				button[j] = new JButton(new ImageIcon("C:/workspace/MarbleProject/image/" + (j + 1) + ".png"));
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
				add(button[j]);
			}
		}
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
