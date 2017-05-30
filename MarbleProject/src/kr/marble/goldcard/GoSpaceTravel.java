package kr.marble.goldcard;

import kr.marble.Player;

public class GoSpaceTravel extends GoldCard{		
	public GoSpaceTravel() {
		super("우주여행!", false,true);
	}
		
	@Override	//유저에게만 사용
	public void usingGoldCard(Player player) {
		player.setLocation(24);
	}

}


