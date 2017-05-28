package kr.marble;

import java.util.ArrayList;
import kr.marble.building.Building;
 
public class Player {
	private Money money; //돈
	private String name; //이름
	private int location; //위치
	private boolean noMoney = false; //플레이어 파산여부
	private int status = 0;
	private ArrayList<Building> buildings = new ArrayList<>(); //건물들
	
	public static final int MORMAL = 0;
	public static final int ISLAND = 1;
	public static final int SPACE_TRAVEL = 2;
	
  
	public Player(String name){ 
		this.name = name; 
	} //Player생성자 
  
	public void noMoney(){ // 파산
		this.noMoney = true;
	} 
	public Money getMoney(){ //돈
		return money;
	}
  
	public boolean isNoMoney(){ // 파산여부
		return noMoney;
	} // 플레이어 파산 여부
  
	public String getName(){ //이름
		return name;
	} //이름 리턴
  
	public void setName(String name){ //이름설정
		this.name = name;
	} //이름 입력 받음
  
	public void move(int count){ //이동
		location += count;
	} //플레이어 count 값에 따라 이동
	
	public int getLocation() { // 위치반환
		return location;
	}
	
	public double getProperty() {//재산
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
