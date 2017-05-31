package kr.marble.goldcard;

import kr.marble.Player;
import kr.marble.building.Building;

public class RemoveBuilding extends GoldCard{		//�ǹ� ö��
	public RemoveBuilding() {
		super("�ǹ� ö��", true, false, false);
	}
	
	@Override	//�ǹ����Ը� ���
	public void usingGoldCard(Building building) {
		if(building.getWho() != null) {
			Player player = building.getWho();
			player.removeBuilding(building);
		}
	}

}


