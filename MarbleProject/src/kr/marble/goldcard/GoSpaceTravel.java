package kr.marble.goldcard;

import kr.marble.Manager;
import kr.marble.Player;
import kr.marble.gui.GameMap;

public class GoSpaceTravel extends GoldCard{		
	public GoSpaceTravel() {
		super("���ֿ���", false,true,true);
	}
		
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(24);
		Manager.getInstance().progressGame(player);
	}

}


