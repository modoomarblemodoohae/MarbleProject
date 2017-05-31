package kr.marble.gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import kr.marble.Player;
import kr.marble.building.Building;

public class PlayerDialog extends JDialog {
	
	public PlayerDialog(GameMap frame, Player player) {
		
		super(frame, player.getName() + "의 정보");
		
		Font font = new Font("돋움체", Font.PLAIN, 12);
		
		setSize(300, 300);
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		setLocationRelativeTo(null);
		setResizable(false);
		
		Building[] buildings = player.getBuildings();
		
		JLabel name = new JLabel("플레이어  이름 : " + player.getName());
		name.setFont(font);
		JLabel money = new JLabel("플레이어  자금  : " +player.getMoney().getMoney()+"원");
		money.setFont(font);
		JLabel property = new JLabel("플레이어 재산 : " + player.getProperty() + "원");
		property.setFont(font);
		JLabel card = new JLabel("현재 플레이어 상태 : ");
		if(player.getStatus() == Player.ISLAND) card.setText(card.getText() + " 무인도 표류");
		else if(player.getStatus() == Player.NO_MONEY) card.setText(card.getText() + " 파산");
		else if(player.getStatus() == Player.NORMAL) card.setText(card.getText() + "정상");
		card.setFont(font);
		
		JLabel builds = new JLabel("소유하고 있는 건물 : ");
		String output = "";
		if(buildings == null) builds.setText(builds.getText() + " 없음");
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
