package kr.marble.goldcard;

import kr.marble.Player;

public class GoIsland extends GoldCard{
	public GoIsland() {		//무인도 보내기
		super("무인도 보내기", true, false);
	}

	
	@Override	//유저에게만 사용
	public void usingGoldCard(Player player) {
		player.setLocation(8);
	}

}


