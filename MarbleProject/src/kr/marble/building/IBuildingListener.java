package kr.marble.building;

import kr.marble.Player;

public interface IBuildingListener {
	public void onBuyingBuilding(Building building, Player player);
	public void onJoinWho(Building building, Player player);
	public void onUpgrade(Building building, Player player);
}
