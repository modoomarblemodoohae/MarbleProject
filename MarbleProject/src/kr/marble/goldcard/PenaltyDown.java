package kr.marble.goldcard;

import kr.marble.building.Building;

public class PenaltyDown extends GoldCard{
	public PenaltyDown() {			//통행료 절반 감소
		super("통행료 절반 감소!", true, false);
	}
		
	@Override	//건물에게만 사용
	public void usingGoldCard(Building building) {
		building.setPenalty(building.getPenalty()/2);
	}


}


