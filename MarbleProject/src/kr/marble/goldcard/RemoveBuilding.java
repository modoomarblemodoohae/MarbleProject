package kr.marble.goldcard;

import kr.marble.building.Building;

public class RemoveBuilding extends GoldCard{		//�ǹ� ö��
	public RemoveBuilding() {
		super("�ǹ� ö��!", true, false);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		building.setLevel(building.getLevel()*0);
	}

}


