package kr.marble.goldcard;

import kr.marble.building.Building;

public class BuildingLevelDown extends GoldCard{		
	public BuildingLevelDown() {
		super("�ǹ����� ���߱�!", false,true);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setLevel(building.getLevel()-1);
	}
	
}


