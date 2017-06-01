package kr.marble.gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import kr.marble.Player;
import kr.marble.building.Building;

public class PlayerDialog extends JDialog {
	
	public PlayerDialog(GameMap frame, Player player) {
		
		super(frame, player.getName() + "�� ����");
		
		Font font = new Font("����ü", Font.PLAIN, 12);
		
		setSize(300, 400);
		setLayout(null);
		setLocationRelativeTo(null);
		
		Building[] buildings = player.getBuildings();
		
		JLabel name = new JLabel("�÷��̾�  �̸� : " + player.getName());
		name.setFont(font);
		name.setBounds(10, 30, 280, 20);
		JLabel money = new JLabel("�÷��̾�  �ڱ�  : " +player.getMoney().getMoney()+"��");
		money.setFont(font);
		money.setBounds(10, 55, 280, 20);
		JLabel property = new JLabel("�÷��̾� ��� : " + player.getProperty() + "��");
		property.setFont(font);
		property.setBounds(10, 80, 280, 20);
		JLabel card = new JLabel("���� �÷��̾� ���� : ");
		if(player.getStatus() == Player.ISLAND) card.setText(card.getText() + " ���ε� ǥ��");
		else if(player.getStatus() == Player.NO_MONEY) card.setText(card.getText() + " �Ļ�");
		else if(player.getStatus() == Player.NORMAL) card.setText(card.getText() + "����");
		card.setFont(font);
		card.setBounds(10, 105, 280, 20);
		
		JLabel builds = new JLabel("�����ϰ� �ִ� �ǹ� : ");
		card.setBounds(10, 130, 280, 20);
		
		String[] names = {""};
		if(buildings == null) builds.setText(builds.getText() + " ����");
		else{
			names = new String[buildings.length];
			for(int i = 0; i < names.length; i ++) {
				names[i] = buildings[i].getBuildingName() + ", LV : ";
				if(buildings[i].getLevel() == Building.LEVEL_0) names[i] = names[i] + " ���� \n";
				else if(buildings[i].getLevel() == Building.LEVEL_1) names[i] = names[i] + " ���� \n";
				else if(buildings[i].getLevel() == Building.LEVEL_2) names[i] = names[i] + " ���� \n";
				else if(buildings[i].getLevel() == Building.LEVEL_3) names[i] = names[i] + " ȣ�� \n";
			}
		}
		
		JList<String> list = new JList<>(names);
		JScrollPane pane = new JScrollPane(list);
		pane.setFont(font);
		builds.setFont(font);
		
		pane.setBounds(10, 155, 265, 200);
		
		add(name);
		add(money);
		add(property);
		add(card);
		add(builds);
		add(pane);
		
		setVisible(true);
	}
}
