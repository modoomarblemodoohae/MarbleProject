package kr.marble.goldcard;

import kr.marble.Manager;
import kr.marble.Player;

public class GoStart extends GoldCard{		
	public GoStart() {
		super("������� �̵�", false,true,false);
	}

	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(0);
		Manager.getInstance().progressGame(player);
	}

}


