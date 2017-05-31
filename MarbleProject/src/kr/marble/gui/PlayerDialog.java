package kr.marble.gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import kr.marble.Player;
import kr.marble.building.Building;

public class PlayerDialog extends JDialog {
	
	public PlayerDialog(GameMap frame, Player player) {
		
		super(frame, player.getName() + "�� ����");
		
		Font font = new Font("����ü", Font.PLAIN, 12);
		
		setSize(300, 300);
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		setLocationRelativeTo(null);
		setResizable(false);
		
		Building[] buildings = player.getBuildings();
		
		JLabel name = new JLabel("�÷��̾�  �̸� : " + player.getName());
		name.setFont(font);
		JLabel money = new JLabel("�÷��̾�  �ڱ�  : " +player.getMoney().getMoney()+"��");
		money.setFont(font);
		JLabel property = new JLabel("�÷��̾� ��� : " + player.getProperty() + "��");
		property.setFont(font);
		JLabel card = new JLabel("���� �÷��̾� ���� : ");
		if(player.getStatus() == Player.ISLAND) card.setText(card.getText() + " ���ε� ǥ��");
		else if(player.getStatus() == Player.NO_MONEY) card.setText(card.getText() + " �Ļ�");
		else if(player.getStatus() == Player.NORMAL) card.setText(card.getText() + "����");
		card.setFont(font);
		
		JLabel builds = new JLabel("�����ϰ� �ִ� �ǹ� : ");
		String output = "";
		if(buildings == null) builds.setText(builds.getText() + " ����");
		else{
			for(Building building : buildings) 
				output += building.getBuildingName() + ", ";
			
			output = output.substring(0, output.length() - 2);
		}
		
		builds.setText(builds.getText() + output);
		builds.setFont(font);
		
		add(name);
		add(money);
		add(property);
		add(card);
		add(builds);
		
		setVisible(true);
	}
}
