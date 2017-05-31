package kr.marble;

import java.util.ArrayList; 

import kr.marble.building.Building;
import kr.marble.goldcard.GoldCard;
 
public class Player {
	private Money money; //占쎈즷
	private String name; //占쎌뵠�뵳占�
	private int location; //占쎌맄燁삼옙
	private int status = 0;
	
	private GoldCard hasCard;
	private ArrayList<Building> buildings = new ArrayList<>(); //椰꾨�窺占쎈굶
	
	private int waitTurn = 0; // 무인도에서 쉬는 턴
	
	public static final int NORMAL = 0;
	public static final int ISLAND = 1;
	public static final int SPACE_TRAVEL = 2;
	public static final int NO_MONEY = 3;
	public static final int STOP = 4;
	
  
	public Player(String name){ 
		this.name = name; 
		money = new Money(Manager.BASE_PLAYER_MONEY);
	} //Player占쎄문占쎄쉐占쎌쁽 
	
	public Money getMoney(){ //占쎈즷
		return money;
	}
  
	public String getName(){ //占쎌뵠�뵳占�
		return name;
	} //占쎌뵠�뵳占� �뵳�뗪쉘
  
	public void setName(String name){ //占쎌뵠�뵳袁⑷퐬占쎌젟
		this.name = name;
	} //占쎌뵠�뵳占� 占쎌뿯占쎌젾 獄쏆룇�벉
  
	public void move(int count){ //占쎌뵠占쎈짗
		location += count;
		if(location >= 31) {
			Manager.getInstance().getManagerEventListener().onStartLocation(this);
			location -= 32;
		}
	} //占쎈탣占쎌쟿占쎌뵠占쎈선 count 揶쏅�る퓠 占쎈뎡占쎌뵬 占쎌뵠占쎈짗
	
	public int getLocation() { // 占쎌맄燁살꼶而뀐옙�넎
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public double getProperty() {//占쎌삺占쎄텦
		Building[] builds = getBuildings();
		double sum = 0;
		if(builds != null) {
			for(Building build : builds)
				sum += build.getBuyMoney();
		}
		
		sum += money.getMoney();
		
		return sum;
	}
	
	public Building[] getBuildings() {
		Building[] builds = new Building[buildings.size()];
		
		if(builds.length == 0) return null;
		
		return buildings.toArray(builds);
	}
	
	public boolean hasBuilding(Building build) {
		for(Building building : buildings) {
			if(building.equals(build)) return true;
		}
		return false;
	}
	
	public boolean addBuilding(Building build) {
		if(money.getMoney() < build.getBuyMoney()) return false;
		
		if(build.getWho() == null) 
			build.setLevel(Building.LEVEL_0);
		
		build.setWho(this);
		money.minusMoney(build.getBuyMoney());
		buildings.add(build);
		Manager.getInstance().getBuildingEventListener().onBuyingBuilding(build, this);
		
		return true;
	}
	
	public boolean removeBuilding(Building build) {
		return removeBuilding(build, null);
	}
	
	public boolean removeBuilding(Building build, Player player) {
		if(!hasBuilding(build)) return false;
		
		// money.addMoney(build.getBuyMoney() * 0.1); : 매각 기능 X
		build.removeGoldCard();
		build.setWho(null);
		build.setLevel(Building.LEVEL_0);
		buildings.remove(build);
		
		if(player != null) 
			player.addBuilding(build);
		
		return true;
		
	}
	
	public void cleanBuilding() {
		buildings.clear();
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setWaitTurn(int count) {
		waitTurn = count;
	}
	
	public int getWaitTurn() {
		return waitTurn;
	}
	
	public void addWaitTurn() {
		waitTurn++;
	}
	
	public void setHasCard(GoldCard card) {
		this.hasCard = card;
	}
	
	public void removeCard() {
		this.hasCard = null;
	}
	
	public GoldCard getHasCard() {
		return hasCard;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Player)) return false;
		
		Player player = (Player) obj;
		return getName().equalsIgnoreCase(player.getName());
	}
  
 }
