package kr.marble.goldcard;

import kr.marble.Player;

public class GoIsland extends GoldCard{
	public GoIsland() {		//���ε� ������
		super("���ε� ������", true, false);
	}

	
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(8);
	}

}


