package kr.marble.building;

import kr.marble.Player;
import kr.marble.goldcard.GoldCard;

public class Building { // 건물 클래스
	
	private double buyMoney; // 건물 금액 
	private int level = 1; // 건물 레벨
	private GoldCard card; // 카드
	private String buildingName; // 건물 이름
	private Player who; // 소유자
	
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
