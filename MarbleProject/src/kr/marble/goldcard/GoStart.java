package kr.marble.goldcard;

import kr.marble.Player;

public class GoStart extends GoldCard{		
	public GoStart() {
		super("출발지로 이동", false,true);
	}

	@Override	//유저에게만 사용
	public void usingGoldCard(Player player) {
		player.setLocation(1);
	}

}


