package kr.marble.goldcard;

import kr.marble.Player;

public interface IGoldCardListener {
	public void onSelectGoldCard(Player player);
	public void onPlayGoldCard(GoldCard card); // 카드가 발동되었을때
}
