package kr.marble.goldcard;

import kr.marble.building.Building;

public class BuildingLevelUp extends GoldCard{		
	public BuildingLevelUp() {
		super("�ǹ����� �ø���!", false,true);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setLevel(building.getLevel() +1);
	}

}

