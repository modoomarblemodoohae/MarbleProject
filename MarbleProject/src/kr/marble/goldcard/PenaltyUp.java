package kr.marble.goldcard;

import kr.marble.building.Building;

public class PenaltyUp extends GoldCard{	// ����� 2�� ����
		
	public PenaltyUp() {
		super("����� ����!", true, false);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setPenalty(building.getPenalty() * 2);
	}
}

