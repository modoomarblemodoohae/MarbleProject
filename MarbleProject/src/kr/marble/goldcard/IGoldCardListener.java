package kr.marble.goldcard;

import kr.marble.Player;

public interface IGoldCardListener {
	public void onSelectGoldCard(Player player);
	public void onPlayGoldCard(GoldCard card); // ī�尡 �ߵ��Ǿ�����
}
