package kr.marble;

import java.util.ArrayList;
import kr.marble.building.Building;
 
public class Player {
	private Money money; //�룉
	private String name; //�씠由�
	private int location; //�쐞移�
	private boolean noMoney = false; //�뵆�젅�씠�뼱 �뙆�궛�뿬遺�
	private int status = 0;
	private ArrayList<Building> buildings = new ArrayList<>(); //嫄대Ъ�뱾
	
	public static final int MORMAL = 0;
	public static final int ISLAND = 1;
	public static final int SPACE_TRAVEL = 2;
	
  
	public Player(String name){ 
		this.name = name; 
	} //Player�깮�꽦�옄 
  
	public void noMoney(){ // �뙆�궛
		this.noMoney = true;
	} 
	public Money getMoney(){ //�룉
		return money;
	}
  
	public boolean isNoMoney(){ // �뙆�궛�뿬遺�
		return noMoney;
	} // �뵆�젅�씠�뼱 �뙆�궛 �뿬遺�
  
	public String getName(){ //�씠由�
		return name;
	} //�씠由� 由ы꽩
  
	public void setName(String name){ //�씠由꾩꽕�젙
		this.name = name;
	} //�씠由� �엯�젰 諛쏆쓬
  
	public void move(int count){ //�씠�룞
		location += count;
	} //�뵆�젅�씠�뼱 count 媛믪뿉 �뵲�씪 �씠�룞
	
	public int getLocation() { // �쐞移섎컲�솚
		return location;
	}
	
	public double getProperty() {//�옱�궛
		Building[] builds = getBuildings();
		double sum = 0;
		
		if(builds.length > 0) {
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
		
		build.onBuyingBuilding(this);
		
		if(build.getWho() == null) 
			build.setLevel(Building.LEVEL_0);
		
		build.setWho(this);
		money.minusMoney(build.getBuyMoney());
		buildings.add(build);
		
		return true;
	}
	
	public boolean removeBuilding(Building build) {
		return removeBuilding(build, null);
	}
	
	public boolean removeBuilding(Building build, Player player) {
		if(!hasBuilding(build)) return false;
		
		money.addMoney(build.getBuyMoney());
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
  
 }
