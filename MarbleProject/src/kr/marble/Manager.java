package kr.marble;

import java.util.HashMap; 
import java.util.Map;

import kr.marble.building.*;
import kr.marble.goldcard.*;

public class Manager { // ���� ���� Ŭ����
	
	private Player winner;
	private Player[] players = new Player[4];
	
	private int turn = 0;
	
	private double donateMoney = 0;
	private Map<String, Building> builds = new HashMap<>();
	private Map<String, GoldCard> goldcards = new HashMap<>();
	
	private static Manager instance = null;
	
	public static final double BASE_PLAYER_MONEY = 0; // �⺻ �÷��̾� ��
	public static final double BASE_DONATE_MONEY = 100000;
	
	public static final int NO_PEOPLE_ISLAND_LOCATION = 8; // ���ε� ����
	public static final int DONATE_LOCATION = 16; // �������
	public static final int SPACE_TRAVEL_LOCATION = 24; // ���ֿ��� ����
	public static final int[] GOLD_CARD_LOCATION = {4, 12, 20, 28}; // ���ī�� �޴� ����
	public static final int PAY_DONATE_LOCATION = 30; // ����ϴ� ����
	
	public static final int TURN_LIMIT = 50;
	public static final int WAIT_TURN_LIMIT = 3;
	
	public static Manager getInstance() {
		if(instance == null) instance = new Manager();
		return instance;
	}
	
	private Manager() {}
	
	public void init(String[] name) { // ������ ����Ǹ� ȣ���
		builds.clear();
		goldcards.clear();
		
		builds.put("�𰡵�", new City01_Mogadishu());
		builds.put("���ī", new City02_Lusaka());
		builds.put("�ƺ���", new City03_Abuja());
		builds.put("����", new City04_Aljae());
		builds.put("������Ÿ��", new City05_Kapetown());
		builds.put("ī�̷�", new City06_Kairo());
		builds.put("ī��ī��", new City07_Karakas());
		builds.put("����Ÿ", new City08_Bogota());
		builds.put("����", new City09_Lima());
		builds.put("��Ƽ�ư�", new City10_Santiago());
		builds.put("Ű��", new City11_Kito());
		builds.put("���������", new City12_Brazilia());
		builds.put("�ϳ���", new City13_Hanoi());
		builds.put("���Ҷ�", new City14_Manila());
		builds.put("������", new City15_Newdally());
		builds.put("��ī��Ÿ", new City16_Zakarta());
		builds.put("����¡", new City17_Beijing());
		builds.put("����", new City18_Tokyo());
		builds.put("��ũ��", new City19_Moskba());
		builds.put("������", new City20_Bellin());
		builds.put("�ĸ�", new City21_Pari());
		builds.put("����", new City22_Newyork());
		builds.put("����", new City23_Seoul());
		
		for(int i = 0; i < name.length; i++)
			players[i] = new Player(name[i]);
	}
	
	public int getDice(Player player) {
		int rand = (int)((Math.random() * 11) + 1);
		player.move(rand);
		
		return rand;
	}
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public Player getWinner() {
		return winner;
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
	
	public Building[] getPlayerHasBuildings(Player player) {
		return null;
	}
	
	public Player whoTurn() {
		Player who = null;
		for(int i = 0; i < 4; i ++) {
			if(players[i].getStatus() != Player.ISLAND) who = players[i];
			else turn++;
		}
		
		return who;
	}
	
	public void progressGame(Player player) {
		int location = player.getLocation();
		
		if(location == NO_PEOPLE_ISLAND_LOCATION) {
			player.addWaitTurn();
			if(player.getWaitTurn() == WAIT_TURN_LIMIT) {
				player.setStatus(Player.NORMAL);
				player.setWaitTurn(0);
			}
			else player.setStatus(Player.ISLAND);
		}else if(location == DONATE_LOCATION) {
			player.getMoney().addMoney(donateMoney);
			donateMoney = 0;
		}else if(location == SPACE_TRAVEL_LOCATION) {
			player.setStatus(Player.SPACE_TRAVEL);
		}else if(location == GOLD_CARD_LOCATION[0] || location == GOLD_CARD_LOCATION[1] ||
				location == GOLD_CARD_LOCATION[2] || location == GOLD_CARD_LOCATION[3]) {
			
		}else if(location == PAY_DONATE_LOCATION) {
			if(player.getMoney().getMoney() < BASE_DONATE_MONEY) {
				player.setStatus(Player.NO_MONEY);
			}else{
				player.getMoney().minusMoney(BASE_DONATE_MONEY);
				donateMoney += BASE_DONATE_MONEY;
			}
		}else{
			player.setStatus(Player.NORMAL);
		}
	}
}
