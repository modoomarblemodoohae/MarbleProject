package kr.marble.goldcard;

import kr.marble.Player;

public class GoStart extends GoldCard{		
	public GoStart() {
		super("������� �̵�", false,true);
	}

	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(1);
	}

}


