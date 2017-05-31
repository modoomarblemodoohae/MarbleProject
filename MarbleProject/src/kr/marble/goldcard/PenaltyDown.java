package kr.marble.goldcard;

import kr.marble.building.Building;

public class PenaltyDown extends GoldCard{
	
	public PenaltyDown() {			//����� ���� ����
		super("����� ���� ����", true, false, false);
	}
		
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setPenalty(building.getPenalty() / 2);
	}
	
	@Override
	public void returnCard(Building building) {
		building.setPenalty(-1);
	}

}


