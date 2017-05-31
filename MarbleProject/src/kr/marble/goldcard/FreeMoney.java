package kr.marble.goldcard;

import kr.marble.Manager;
import kr.marble.Player;

public class FreeMoney extends GoldCard{		
	public FreeMoney() {
		super("�ҿ��̿� ����", false, true, false);
	}
	@Override	//�������Ը� ���
	public void usingGoldCard(Player player) {
		Manager manager = Manager.getInstance();
		Player[] players = manager.getPlayers();
		
		double sum = 0;
		
		for(Player p : players) {
			if(p.getName().equalsIgnoreCase(player.getName())) continue;
			double get = p.getMoney().getMoney() * 0.1;
			if(p.getMoney().getMoney() < get) {
				Manager.getInstance().getManagerEventListener().onNoMoneyPlayer(p, get);
				get = p.getMoney().getMoney();
			}
			p.getMoney().minusMoney(get);
			sum += get;
		}
		
		player.getMoney().addMoney(sum);
	}
	
}