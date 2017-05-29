package kr.marble;

import java.util.HashMap; 
import java.util.Map;

import kr.marble.building.*;
import kr.marble.goldcard.*;

public class Manager { // 게임 관리 클래스
	
	private Player winner;
	private Player[] players = new Player[4];
	
	private int turn = 0;
	
	private double donateMoney = 0;
	private Map<String, Building> builds = new HashMap<>();
	private Map<String, GoldCard> goldcards = new HashMap<>();
	
	private static Manager instance = null;
	
	public static final double BASE_PLAYER_MONEY = 0; // 기본 플레이어 돈
	public static final double BASE_DONATE_MONEY = 100000;
	
	public static final int NO_PEOPLE_ISLAND_LOCATION = 8; // 무인도 지점
	public static final int DONATE_LOCATION = 16; // 기부지점
	public static final int SPACE_TRAVEL_LOCATION = 24; // 우주여행 지점
	public static final int[] GOLD_CARD_LOCATION = {4, 12, 20, 28}; // 골드카드 받는 지점
	public static final int PAY_DONATE_LOCATION = 30; // 기부하는 지점
	
	public static final int TURN_LIMIT = 50;
	public static final int WAIT_TURN_LIMIT = 3;
	
	public static Manager getInstance() {
		if(instance == null) instance = new Manager();
		return instance;
	}
	
	private Manager() {}
	
	public void init(String[] name) { // 게임이 실행되면 호출됨
		builds.clear();
		goldcards.clear();
		
		builds.put("모가디슈", new City01_Mogadishu());
		builds.put("루사카", new City02_Lusaka());
		builds.put("아부자", new City03_Abuja());
		builds.put("알제", new City04_Aljae());
		builds.put("케이프타운", new City05_Kapetown());
		builds.put("카이로", new City06_Kairo());
		builds.put("카라카스", new City07_Karakas());
		builds.put("보고타", new City08_Bogota());
		builds.put("리마", new City09_Lima());
		builds.put("산티아고", new City10_Santiago());
		builds.put("키토", new City11_Kito());
		builds.put("브라질리아", new City12_Brazilia());
		builds.put("하노이", new City13_Hanoi());
		builds.put("마닐라", new City14_Manila());
		builds.put("뉴델리", new City15_Newdally());
		builds.put("자카르타", new City16_Zakarta());
		builds.put("베이징", new City17_Beijing());
		builds.put("도쿄", new City18_Tokyo());
		builds.put("모스크바", new City19_Moskba());
		builds.put("베를린", new City20_Bellin());
		builds.put("파리", new City21_Pari());
		builds.put("뉴욕", new City22_Newyork());
		builds.put("서울", new City23_Seoul());
		
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
	
	public Player getTurnWho() { // 누구의 턴인가
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
