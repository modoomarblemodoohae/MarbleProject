package kr.marble.goldcard;

import kr.marble.Manager;
import kr.marble.Player;

public class GoIsland extends GoldCard{
	public GoIsland() {		//���ε� ������
		super("���ε� ������", false, true, false);
	}

	
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(8);
		Manager.getInstance().progressGame(player);
	}

}


