package kr.marble.goldcard;

import kr.marble.building.Building;

public class BuildingLevelDown extends GoldCard{		
	public BuildingLevelDown() {
		super("건물레벨 낮추기", false,true);
	}
	
	@Override	//건물에게만 사용
	public void usingGoldCard(Building building) {
		if(building.getLevel() > Building.LEVEL_0)
			building.setLevel(building.getLevel() - 1);
	}
	
}


