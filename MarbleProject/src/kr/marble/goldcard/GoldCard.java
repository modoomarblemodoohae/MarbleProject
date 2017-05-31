package kr.marble.goldcard;

import kr.marble.Player;
import kr.marble.building.Building;

public class GoldCard { // 골드 카드 추상클래스
	
	private String name; // 카드  이름
	private boolean needBuilding = false; // 건물이 이용되는가
	private boolean needPlayer = false;
	
	public GoldCard(String name, boolean needBuilding, boolean needPlayer) {
		this.name = name;
		this.needBuilding = needBuilding;
		this.needPlayer = needPlayer;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isNeedBuilding() {
		return needBuilding;
	}
	
	public boolean isNeedPlayer() {
		return needPlayer;
	}
	
	public void usingGoldCard(Building building) {}
	public void usingGoldCard(Player player) {}
	public void usingGoldCard(Building building, Player player) {}
	
	public void returnCard(Building building){} // 카드의 효과가 사라졌을때
}
