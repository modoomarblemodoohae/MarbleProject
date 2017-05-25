package kr.marble.goldcard;

import kr.marble.building.Building;

public abstract class GoldCard { // 골드 카드 추상클래스
	
	private String name; // 카드  이름
	
	public GoldCard(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void usingGoldCard(Building building);
}
