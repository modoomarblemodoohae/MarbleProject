package kr.marble.goldcard;

import kr.marble.building.Building;

public class RemoveBuilding extends GoldCard{		//건물 철거
	public RemoveBuilding() {
		super("건물 철거!", true, false);
	}
	
	@Override	//건물에게만 사용
	public void usingGoldCard(Building building) {
		building.setLevel(building.getLevel()*0);
	}

}


