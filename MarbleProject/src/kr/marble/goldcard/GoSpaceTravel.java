package kr.marble.goldcard;

import kr.marble.Player;

public class GoSpaceTravel extends GoldCard{		
	public GoSpaceTravel() {
		super("���ֿ���!", false,true);
	}
		
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(24);
	}

}


