package kr.marble;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.marble.building.*;
import kr.marble.goldcard.*;
import kr.marble.gui.GameMap;

public class Manager { // ���� ���� Ŭ����

	private Player[] players = new Player[4];
	
	private int turn = 0;
	
	private double donateMoney = 0;
	private Map<Integer, Building> builds = new HashMap<>();
	private List<GoldCard> goldcards = new ArrayList<>();
	
	private IGoldCardListener goldListener = null;
	private IBuildingListener buildingListener = null;
	private IManagerListener managerListener = null;
	
	private static Manager instance = null;
	
	public static final double BASE_PLAYER_MONEY = 2000000; // �⺻ �÷��̾� ��
	public static final double BASE_DONATE_MONEY = 2000000;
	public static final double START_LOCATION_MONEY = 200000;
	
	public static final int START_LOCATION = 0;
	public static final int NO_PEOPLE_ISLAND_LOCATION = 8; // ���ε� ����
	public static final int DONATE_LOCATION = 16; // �������
	public static final int SPACE_TRAVEL_LOCATION = 24; // ���ֿ��� ����
	public static final int[] GOLD_CARD_LOCATION = {4, 12, 20, 28}; // ���ī�� �޴� ����
	public static final int PAY_DONATE_LOCATION = 30; // ����ϴ� ����
	
	public static final int TURN_LIMIT = 5;
	public static final int WAIT_TURN_LIMIT = 3;
	
	public static Manager getInstance() {
		if(instance == null) instance = new Manager();
		return instance;
	}
	
	private Manager() {
	}
	
	public void init() { // ������ ����Ǹ� ȣ���
		builds.clear();
		goldcards.clear();
		
		builds.put(1, new City01_Mogadishu());
		builds.put(2, new City02_Lusaka());
		builds.put(3, new City03_Abuja());
		builds.put(5, new City04_Aljae());
		builds.put(6, new City05_Kapetown());
		builds.put(7, new City06_Kairo());
		builds.put(9, new City07_Karakas());
		builds.put(10, new City08_Bogota());
		builds.put(11, new City09_Lima());
		builds.put(13, new City10_Santiago());
		builds.put(14, new City11_Kito());
		builds.put(15, new City12_Brazilia());
		builds.put(17, new City13_Hanoi());
		builds.put(18, new City14_Manila());
		builds.put(19, new City15_Newdally());
		builds.put(21, new City16_Zakarta());
		builds.put(22, new City17_Beijing());
		builds.put(23, new City18_Tokyo());
		builds.put(25, new City19_Moskba());
		builds.put(26, new City20_Bellin());
		builds.put(27, new City21_Pari());
		builds.put(29, new City22_Newyork());
		builds.put(31, new City23_Seoul());
		
		goldcards.add(new BuildingLevelDown());
		goldcards.add(new BuildingLevelUp());
		goldcards.add(new FreeMoney());
		goldcards.add(new GoIsland());
		goldcards.add(new GoSpaceTravel());
		goldcards.add(new GoStart());
		goldcards.add(new PenaltyDown());
		goldcards.add(new PenaltyUp());
		goldcards.add(new RandomLocation());
		goldcards.add(new RemoveBuilding());
	}
	
	public void initPlayer(String[] name) {
		for(int i = 0; i < name.length; i++) {
			players[i] = new Player(name[i]);
			players[i].getMoney().setMoney(BASE_PLAYER_MONEY);
		}
	}
	
	public void setGoldCardEventListener(IGoldCardListener listener) { // ��� ī�� �̾������� ������ 
		this.goldListener = listener;
	}
	
	public void setBuildingEventListener(IBuildingListener listener) {
		this.buildingListener = listener;
	}
	
	public void setManagerEventListener(IManagerListener listener) {
		this.managerListener = listener;
	}
	
	public IGoldCardListener getGoldCardEventListener() {
		return goldListener;
	}
	
	public IBuildingListener getBuildingEventListener() {
		return buildingListener;
	}
	
	public IManagerListener getManagerEventListener() {
		return managerListener;
	}
	
	public int getDice() {
		int rand = (int)((Math.random() * 11) + 1);
		
		return rand;
	}
	
	public void addDonate(double donateMoney) {
		this.donateMoney += donateMoney;
	}
	
	public double getDonateMoney() {
		return donateMoney;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public Player getTurnWho() { // ������ ���ΰ�
		return players[turn % 4];
	}
	
	public int getTurn() {
		return turn % 4;
	}
	
	public void addTurn() {
		turn++;
	}
	
	public boolean isFillTurn() {
		return turn >= TURN_LIMIT;
	}
	
	public Collection<Building> getAllCity() {
		return builds.values();
	}
	
	public List<GoldCard> getAllCard() {
		return goldcards;
	}
	
	public Building getBuilding(int idx) {
		return builds.containsKey(idx) ? builds.get(idx) : null;
	}
	
	public void progressGame(Player player) {
		int location = player.getLocation();
		
		if(location == NO_PEOPLE_ISLAND_LOCATION) {
			managerListener.onJoinIsLand(player);
			player.addWaitTurn();
			if(player.getWaitTurn() == WAIT_TURN_LIMIT) {
				player.setStatus(Player.NORMAL);
				player.setWaitTurn(0);
			}
			else player.setStatus(Player.ISLAND);
		}else if(location == DONATE_LOCATION) {
			managerListener.onReceiveDonate(player, donateMoney);
			donateMoney = 0;
		}else if(location == SPACE_TRAVEL_LOCATION) {
			managerListener.onSpaceTravel(player);
		}else if(location == GOLD_CARD_LOCATION[0] || location == GOLD_CARD_LOCATION[1] ||
				location == GOLD_CARD_LOCATION[2] || location == GOLD_CARD_LOCATION[3]) {
			goldListener.onSelectGoldCard(player);
		}else if(location == PAY_DONATE_LOCATION) {
			if(player.getMoney().getMoney() < BASE_DONATE_MONEY) {
				player.setStatus(Player.NO_MONEY);
				managerListener.onNoMoneyPlayer(player, BASE_DONATE_MONEY);
			}else{
				managerListener.onGiveDonate(player);
				player.getMoney().minusMoney(BASE_DONATE_MONEY);
				donateMoney += BASE_DONATE_MONEY;
			}
		}else if(location == START_LOCATION) {
			managerListener.onStartLocation(player);
		}else{
			if(builds.containsKey(location)) 
				buildingListener.onJoinWho(builds.get(location), player);
		}
	}
	
	
	
	public GoldCard getSelectGoldCard(Player player) {
		int random = (int) ((Math.random() * goldcards.size()) - 1);
		
		return goldcards.get(random);
	}
}
