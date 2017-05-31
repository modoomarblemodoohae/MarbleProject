package kr.marble;

public interface IManagerListener {
	public void onJoinIsLand(Player player);
	public void onGiveDonate(Player player);
	public void onReceiveDonate(Player player, double donate);
	public void onNoMoneyPlayer(Player player, double money);
	public void onStartLocation(Player player);
	public void onSpaceTravel(Player player);
}
