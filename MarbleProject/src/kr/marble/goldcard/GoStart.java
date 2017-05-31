package kr.marble.goldcard;

import kr.marble.Manager;
import kr.marble.Player;

public class GoStart extends GoldCard{		
	public GoStart() {
		super("출발지로 이동", false,true,false);
	}

	@Override	//유저에게만 사용
	public void usingGoldCard(Player player) {
		player.setLocation(0);
		Manager.getInstance().progressGame(player);
	}

}


