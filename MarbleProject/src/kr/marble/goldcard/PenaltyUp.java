package kr.marble.goldcard;

import kr.marble.building.Building;

public class PenaltyUp extends GoldCard{	// 통행료 2배 증가
		
	public PenaltyUp() {
		super("통행료 증가!", true, false);
	}
	
	@Override	//건물에게만 사용
	public void usingGoldCard(Building building) {
		building.setPenalty(building.getPenalty() * 2);
	}
}

