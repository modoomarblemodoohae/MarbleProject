package kr.marble.building;

import kr.marble.Player;
import kr.marble.goldcard.GoldCard;

public interface IBuildingListener {
	public void onBuyingBuilding(Building building, Player player);
	public void onJoinWho(Building building, Player player);
	public void onUsingFromBuilding(Building building, GoldCard card);
}
