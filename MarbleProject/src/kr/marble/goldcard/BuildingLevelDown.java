package kr.marble.goldcard;

import kr.marble.building.Building;

public class BuildingLevelDown extends GoldCard{		
	public BuildingLevelDown() {
		super("�ǹ����� ���߱�", false,true);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		if(building.getLevel() > Building.LEVEL_0)
			building.setLevel(building.getLevel() - 1);
	}
	
}


