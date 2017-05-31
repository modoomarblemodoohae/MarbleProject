package kr.marble.goldcard;

import kr.marble.Player;
import kr.marble.building.Building;

public class GoldCard { // ��� ī�� �߻�Ŭ����
	
	private String name; // ī��  �̸�
	private boolean needBuilding = false; // �ǹ��� �̿�Ǵ°�
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
	
	public void returnCard(Building building){} // ī���� ȿ���� ���������
}
