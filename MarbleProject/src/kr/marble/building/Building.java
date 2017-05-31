package kr.marble.building;

import kr.marble.Player;   
import kr.marble.goldcard.GoldCard;

public class Building { // 건물 클래스
	
	private double buyMoney; // 건물 금액
	private double penalty = -1;
	
	private int level = LEVEL_0; // 건물 레벨
	private int location;
	private GoldCard card; // 카드
	private String buildingName; // 건물 이름
	private Player who; // 소유자
	
	public static final int LEVEL_0 = 0;
	public static final int LEVEL_1 = 1;
	public static final int LEVEL_2 = 2;
	public static final int LEVEL_3 = 3;
	
	public Building(String buildingName, double buyMoney, int location) {
		this.buyMoney = buyMoney;
		this.buildingName = buildingName;
		this.location = location;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public int getLocation() {
		return location;
	}
	
	public double getPenalty() { //벌금
		if(penalty == -1) {
			double percent = 0;
			if(level==LEVEL_3) percent = 2.5;
			else if(level==LEVEL_2) percent = 2;
			else if(level==LEVEL_1) percent = 1.5;
			else if(level==LEVEL_0) percent = 1.2;
			return (buyMoney / 2) * percent;
		}
		return penalty;
	}
	
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
	public int getLevel() { // 레벨
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
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public GoldCard getGoldCard() {
		return card;
	}
	
	public void setGoldCard(GoldCard card) {
		this.card = card;
	}
	
	public void removeGoldCard() {
		this.card = null;
	}
	
	public double getUpgradeMoney() {
		double pen = getBuyMoney();
		double percent = 0;
		
		
		if(level == 0) percent = 0.2;
		else if(level == 1) percent = 0.4;
		else if(level == 2) percent = 0.6;
		
		return pen * percent;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Building)) return false;
		
		Building build = (Building) obj;
		
		return getBuildingName().equalsIgnoreCase(build.getBuildingName());
	}
	
	
	
}
