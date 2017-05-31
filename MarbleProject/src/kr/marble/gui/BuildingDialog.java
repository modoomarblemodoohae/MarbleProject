package kr.marble.gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

import kr.marble.building.Building;

public class BuildingDialog extends JDialog {
	
	public BuildingDialog(MainFrame frame, Building building) {
		
		super(frame, building.getBuildingName() + "�� ����");
		
		Font font = new Font("����ü", Font.PLAIN, 12);
		
		setSize(250, 230);
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel name = new JLabel("����  �̸� : " + building.getBuildingName());
		name.setFont(font);
		JLabel buymoney = new JLabel("���� ����  ����  : " +building.getBuyMoney()+"��");
		buymoney.setFont(font);
		
		add(name);
		add(buymoney);
		
		if (building.getWho() != null) {
			JLabel who = new JLabel("���� ������ : " + building.getWho().getName());
			JLabel panelty = new JLabel("����  ����  : " + building.getPenalty() + "��");
			JLabel level = new JLabel();
			if (building.getLevel() == 0) 
				level.setText("����  ����  : ����");
			else if (building.getLevel() == 1) 
				level.setText("����  ����  : ����");
			else if (building.getLevel() == 2) 
				level.setText("����  ����  : ����");
			else
				level.setText("����  ����  : ȣ��");
			
			who.setFont(font);
			panelty.setFont(font);
			level.setFont(font);
			
			add(who);
			add(panelty);
			add(level);
		}
		JLabel goldcard = new JLabel("�ֱ� ���� Ȳ��ī��  : " + ( building.getLastGoldCard() == null ? " ����" : building.getLastGoldCard().getName() + " �ο�" ));
		goldcard.setFont(font);
		add(goldcard);
		
		setVisible(true);
	}

}
