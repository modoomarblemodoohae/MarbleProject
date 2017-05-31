package kr.marble.gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import kr.marble.building.Building;

public class BuildingDialog extends JDialog {
	
	public BuildingDialog(MainFrame frame, Building building) {
		
		super(frame, building.getBuildingName() + "의 정보");
		
		Font font = new Font("돋움체", Font.PLAIN, 12);
		
		setSize(250, 230);
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel name = new JLabel("빌딩  이름 : " + building.getBuildingName());
		name.setFont(font);
		JLabel buymoney = new JLabel("현재 빌딩  가격  : " +building.getBuyMoney()+"원");
		buymoney.setFont(font);
		
		add(name);
		add(buymoney);
		
		if (building.getWho() != null) {
			JLabel who = new JLabel("빌딩 소유자 : " + building.getWho().getName());
			JLabel panelty = new JLabel("빌딩  벌금  : " + building.getPenalty() + "원");
			JLabel level = new JLabel();
			if (building.getLevel() == 0) 
				level.setText("빌딩  레벨  : 토지");
			else if (building.getLevel() == 1) 
				level.setText("빌딩  레벨  : 주택");
			else if (building.getLevel() == 2) 
				level.setText("빌딩  레벨  : 빌딩");
			else
				level.setText("빌딩  레벨  : 호텔");
			
			who.setFont(font);
			panelty.setFont(font);
			level.setFont(font);
			
			add(who);
			add(panelty);
			add(level);
		}
		JLabel goldcard = new JLabel("최근 사용된 황금카드  : " + ( building.getLastGoldCard() == null ? " 없음" : building.getLastGoldCard().getName() + " 부여" ));
		goldcard.setFont(font);
		add(goldcard);
		
		setVisible(true);
	}

}
