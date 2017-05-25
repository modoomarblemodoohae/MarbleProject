package kr.marble.goldcard;

import kr.marble.building.Building;

public abstract class GoldCard { // ��� ī�� �߻�Ŭ����
	
	private String name; // ī��  �̸�
	
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
