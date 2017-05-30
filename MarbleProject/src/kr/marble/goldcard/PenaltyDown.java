package kr.marble.goldcard;

import kr.marble.building.Building;

public class PenaltyDown extends GoldCard{
	public PenaltyDown() {			//����� ���� ����
		super("����� ���� ����!", true, false);
	}
		
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setPenalty(building.getPenalty()/2);
	}


}


