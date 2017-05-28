package kr.marble;

import java.util.ArrayList;
import kr.marble.building.Building;
 
public class Player {
	private Money money; //��
	private String name; //�̸�
	private int location; //��ġ
	private boolean noMoney = false; //�÷��̾� �Ļ꿩��
	private int status = 0;
	private ArrayList<Building> buildings = new ArrayList<>(); //�ǹ���
	
	public static final int MORMAL = 0;
	public static final int ISLAND = 1;
	public static final int SPACE_TRAVEL = 2;
	
  
	public Player(String name){ 
		this.name = name; 
	} //Player������ ////////////////////////////////// ���� 1
  
	public void noMoney(){  ///////////////////////// ���� 2
		this.noMoney = true;
	} 
	public Money getMoney(){ /////////////////////////////// ���� 3
		return money;
	}
  
	public boolean isNoMoney(){
		return noMoney;
	} // �÷��̾� �Ļ� ����
  
	public String getName(){
		return name;
	} //�̸� ����
  
	public void setName(String name){
		this.name = name;
	} //�̸� �Է� ����
  
	public void move(int count){
		location += count;
	} //�÷��̾� count ���� ���� �̵�
	
	public int getLocation() {
		return location;
	}
	
	public double getProperty() {
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
