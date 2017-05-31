package kr.marble.goldcard;

import java.util.Random;

import kr.marble.Player;

public class RandomLocation extends GoldCard{	
	Random rd = new Random();
	int RL = rd.nextInt(32)+1;
	public RandomLocation() {
		super("랜덤한곳으로 이동하기", false,true);
	}
	
	@Override	//유저에게만 사용
	public void usingGoldCard(Player player) {
		player.setLocation(RL);
	}

}