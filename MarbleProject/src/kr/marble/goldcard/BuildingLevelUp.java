package kr.marble.goldcard;

import kr.marble.building.Building;

public class BuildingLevelUp extends GoldCard{		
	public BuildingLevelUp() {
		super("건물레벨 올리기!", false,true);
	}
	
	@Override	//건물에게만 사용
	public void usingGoldCard(Building building) {
		building.setLevel(building.getLevel() +1);
	}

}

