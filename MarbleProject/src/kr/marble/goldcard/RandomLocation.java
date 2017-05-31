package kr.marble.goldcard;

import java.util.Random;

import kr.marble.Manager;
import kr.marble.Player;

public class RandomLocation extends GoldCard{	
	Random rd = new Random();
	int RL = rd.nextInt(32);
	public RandomLocation() {
		super("�����Ѱ����� �̵��ϱ�", false,true, false);
	}
	
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		player.setLocation(RL);
		Manager.getInstance().progressGame(player);
	}

}