package kr.marble.building;

import kr.marble.Player;
import kr.marble.goldcard.GoldCard;

public class Building { // �ǹ� Ŭ����
	
	private double buyMoney; // �ǹ� �ݾ� 
	private int level = 1; // �ǹ� ����
	private GoldCard card; // ī��
	private String buildingName; // �ǹ� �̸�
	private Player who; // ������
	
	public static final int LEVEL_1 = 1;
	public static final int LEVEL_2 = 2;
	public static final int LEVEL_3 = 3;
	
	public Building(String buildingName, double buyMoney) {
		this.buyMoney = buyMoney;
	}
	
	public void onBuyingBuilding(Player player) {
		
	}
	
	public void onJoinWho(Player player) {
		
	}
	
	public void onUsingFromBuilding(GoldCard card) {
		card.usingGoldCard(this);
		this.card = card;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public double getPenalty() {
		return buyMoney * 0.85;
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getBuyMoney() {
		return buyMoney;
	}
	
	public void setWho(Player who) {
		this.who = who;
	}
	
	public Player getWho() {
		return who;
	}
	
	public boolean levelUp() {
		if(level == LEVEL_3) return false;
		
		level++;
		buyMoney *= 1.5;
		return true;
	}
	
	public void setBuyMoney(double buyMoney) {
		this.buyMoney = buyMoney;
	}
	
	public GoldCard getLastGoldCard() {
		return card;
	}
	
	
	
}
