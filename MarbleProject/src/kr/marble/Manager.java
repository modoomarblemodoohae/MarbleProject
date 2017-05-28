package kr.marble;

import kr.marble.building.Building;

public class Manager { // ���� ���� Ŭ����
	
	private Player winner;
	private Player[] players = new Player[4];
	
	private int turn = 0;
	
	private double donateMoney = 0;
	
	private static Manager instance = null;
	
	public static final double BASE_PLAYER_MONEY = 0; // �⺻ �÷��̾� ��
	
	public static final int NO_PEOPLE_ISLAND_LOCATION = 0; // ���ε� ����
	public static final int DONATE_LOCATION = 0; // �������
	public static final int SPACE_TRAVEL_LOCATION = 0; // ���ֿ��� ����
	public static final int GOLD_CARD_LOCATION = 0; // ���ī�� �޴� ����
	public static final int PAY_DONATE_LOCATION = 0; // ����ϴ� ����
	
	public static final int TURN_LIMIT = 0;
	
	public static Manager getInstance() {
		if(instance == null) instance = new Manager();
		return instance;
	}
	
	private Manager() {}
	
	public int getDice(Player player) {
		int rand = (int)((Math.random() * 11) + 1);
		// player.move(rand); : player Ŭ�������� ���� �����ȵ�
		
		return rand;
	}
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void init() { // ������ ����Ǹ� ȣ���
		
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
		
	}
}
