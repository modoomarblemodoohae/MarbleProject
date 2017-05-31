package kr.marble.gui;

import java.awt.Font;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.marble.IManagerListener;
import kr.marble.Manager;
import kr.marble.Player;
import kr.marble.building.Building;
import kr.marble.building.IBuildingListener;
import kr.marble.goldcard.GoldCard;
import kr.marble.goldcard.IGoldCardListener;

public class GameMap extends JFrame {
	
	private boolean isStart = false;
	public static boolean isBreak = false;
	
	private Font baseFont = new Font("����ü", Font.PLAIN, 15);
	
	private JButton button[] = new JButton[32];
	private JButton playerBtn[] = new JButton[4];
	private JButton infoBtn[] = new JButton[4];
	private Player[] players;
	
	private JTextArea area = new JTextArea();
	private JScrollPane pane;
	private ButtonGroup group = new ButtonGroup();
	private Manager manager = Manager.getInstance();
	private EventListener listener = new EventListener();
	
	private final static int WIDTH = 75;
	private final static int HEIGHT = 133;
	
	private static Map<Player, Integer> selectCity = new HashMap<>();
	
	public final static int MODE_GOLDCARD_SELECT_CITY = 0;
	public final static int MODE_TRAVEL_TARGET = 1;
	
	private int x = 700;
	private int y = 680; 
	
	public static void setSelectCity(Player player, int mode) {
		selectCity.put(player, mode);
	}
	
	public GameMap(){
		button = new JButton[32];
		setTitle("Marble Project");
		setSize(1500,900);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		manager.setBuildingEventListener(listener);
		manager.setGoldCardEventListener(listener);
		manager.setManagerEventListener(listener);
		players = manager.getPlayers();
		
		createMap();
		createRight();

		setVisible(true);
	}
	
	public void createMap() {
		for(int i = 0; i < 4; i++) {
			for(int j = i * (button.length / 4); j <= (i * (button.length / 4)) + 7; j ++){
				button[j] = new JButton(new ImageIcon("C:/workspace/MarbleProject/image/map/" + j + ".png"));
				button[j].setBorderPainted(false);
				button[j].setContentAreaFilled(false);
				if(i == 0) {
					if(j == 0)
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
					else
						button[j].setBounds(x, y, WIDTH, HEIGHT);
					if(j == 7)
						x -= HEIGHT;
					else
						x -= WIDTH;
				} else if(i == 1) {
					if(j == 8)
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
					else 
						button[j].setBounds(x, y, HEIGHT, WIDTH);
					if(j == 15) 
						y -= HEIGHT;
					else
						y -= WIDTH;
				} else if(i == 2) {
					if(j == 16) {
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
						x += HEIGHT;
					} else {
						button[j].setBounds(x, y, WIDTH, HEIGHT);
						x += WIDTH;
					}
				} else {
					if(j == 24) {
						button[j].setBounds(x, y, HEIGHT, HEIGHT);
						y += HEIGHT;
					} else {
						button[j].setBounds(x, y, HEIGHT, WIDTH);
						y += WIDTH;
					}
				}
				button[j].addActionListener(new CityButtonListener(this, j));
				add(button[j]);
			}
		}
	}
	
	public void createRight() {
		
		int x = 850;
		int y = 350;
		
		pane = new JScrollPane(area);
		pane.setBounds(850, 30, 600, 300);
		
		area.setEditable(false);
		area.setFont(baseFont);
		
		for(int i = 0; i < playerBtn.length; i ++) {
			playerBtn[i] = new JButton(players[i].getName());
			infoBtn[i] = new JButton("����");
			
			if(i > 0) playerBtn[i].setEnabled(false);
			infoBtn[i].setBounds(x, y, 100, 100);
			playerBtn[i].setBounds(x + 110, y, 300, 100);
			
			y += 105;
			add(infoBtn[i]);
			add(playerBtn[i]);
			
			infoBtn[i].addActionListener(new PlayerInfoButtonListener(this, i));
			playerBtn[i].addActionListener(new PlayerButtonListener(this, i));
		}
		
		add(pane);
	}
	
	public void log(String str) {
		Calendar calendar = Calendar.getInstance();
		String month = String.format("[%02d��%02d�� %02d:%02d:%02d] %s\n", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR)
				, calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), str);
		area.append(month);
	}
	
	class EventListener implements IManagerListener, IBuildingListener, IGoldCardListener {

		@Override
		public void onSelectGoldCard(Player player) { // Ȳ��ī�带 �������� ������
			GoldCard card = manager.getSelectGoldCard(player);
			player.setHasCard(card);
			GameMap.setSelectCity(player, GameMap.MODE_GOLDCARD_SELECT_CITY);
			log(player.getName() + "���� "+ card.getName() +"ī�带 �̾ҽ��ϴ�.");
			
			if(card.isNeedBuilding()) {
				GameMap.isBreak = true;
				log("�ǹ��� �����Ͽ� �ش� ī�带 ��ġ�Ͻʽÿ�");
			}
			else
				card.usingGoldCard(player);
		}

		@Override
		public void onBuyingBuilding(Building building, Player player) { // �ǹ��� ����������
			 log(player.getName()+"���� "+building.getBuildingName()+"�� �����߽��ϴ�.");
			 log(player.getName()+"�� ��� "+player.getMoney().getMoney()+" ���� ���ҽ��ϴ�.");
				
		}

		
		public void onJoinWho(Building building, Player player) { // �ǹ��� ���� ��������
			if(building.getWho() != null) {
				if(building.getGoldCard() != null) {
					GoldCard card = building.getGoldCard();
					if(card.isNeedPlayer()) card.usingGoldCard(building, player);
					else card.usingGoldCard(player);
					
					log("�̸� ��ġ�� " + card.getName() + " ī�尡 �ߵ��Ǿ����ϴ�.");
				}
				if(!building.getWho().equals(player)) {
					log(player.getName() + " ���� " + building.getWho().getName()
							+ "���� ������ ���̽��ϴ�. ���� : " + building.getPenalty()); 
					if(player.getMoney().getMoney() < building.getPenalty()) {
						player.setStatus(Player.NO_MONEY);
						log(player.getName() + "���� �Ļ���߽��ϴ� ������ �ݾ� : " + Math.abs(player.getMoney().getMoney() - building.getPenalty()));
					}else{
						player.getMoney().minusMoney(building.getPenalty());
						log(player.getName() + "���� ������ �½��ϴ� ���� �ݾ�  : " + player.getMoney().getMoney() + "��");
					}
						
				}else{
					log(player.getName() + " : ����� ������ �����ϴ�");
					if(building.getLevel() < Building.LEVEL_3) {
						if(JOptionPane.showConfirmDialog(null, building.getBuildingName() + " �ǹ��� ���׷��̵� �Ͻǰǰ���?", "Ȯ��", JOptionPane.YES_NO_OPTION) == 0)
							manager.getBuildingEventListener().onUpgrade(building, player);
					}
				}
				
			}else{
				log("���ξ��� ������ ���̽��ϴ�.");
				if(JOptionPane.showConfirmDialog(null, "�� ������ �����Ͻǰǰ���?", "Ȯ��", JOptionPane.YES_NO_OPTION) == 0) {
					player.addBuilding(building);
				}
			}
			
		}

		@Override
		public void onJoinIsLand(Player player) { // ���ε��� ��������
			log(player.getName() + " : ���ε� �Լ� " + player.getWaitTurn() + "��....");
			if(player.getWaitTurn() == Manager.WAIT_TURN_LIMIT)
				log(player.getName() + " : �����Ͽ� ���ε��� Ż���Ҽ� �ֽ��ϴ�.");
		}

		@Override
		public void onGiveDonate(Player player) { // ��α��� �޾�����
			log(player.getName() + " : ��θ� �Ͽ����ϴ�.");
		}

		@Override
		public void onReceiveDonate(Player player, double donate) { // ��α� ������
			player.getMoney().addMoney(donate);
			log(player.getName() + " : " + donate + "�� ��ŭ ���� ��α��� �޾ҽ��ϴ�.");
		}

		@Override
		public void onPlayGoldCard(GoldCard card) {
			log(card.getName() + " ī�尡 �ߵ��Ǿ����ϴ�.");
		}

		@Override
		public void onStartLocation(Player player) {
			player.getMoney().addMoney(Manager.START_LOCATION_MONEY);
			log(player.getName() + " ���� ������������ ������ �޾ҽ��ϴ�.");
		}

		@Override
		public void onNoMoneyPlayer(Player player, double money) {
			log(player.getName() + "���� �Ļ� ���߽��ϴ� ������ �ݾ� : " + Math.abs(player.getMoney().getMoney() - money) + "��");
		}

		@Override
		public void onUpgrade(Building building, Player player) {
			if(building.getLevel() == Building.LEVEL_3) log(player.getName() + " : �̹� �ְ� �������� �ö� �ǹ��Դϴ�.");
			else {
				if(player.getMoney().getMoney() < building.getUpgradeMoney()) {
					log(player.getName() + " : ���� ��� �ǹ��� ������ �ø��� ���մϴ�.");
					return;
				}
				player.getMoney().minusMoney(building.getUpgradeMoney());
				building.levelUp();
				log(player.getName() + " : �ǹ��� ���׷��̵� �Ͽ����ϴ�");
			}
		}
		
	}
	
	class PlayerButtonListener implements ActionListener {

		private int idx;
		private GameMap frame;
		
		public PlayerButtonListener(GameMap frame, int idx) {
			this.idx = idx;
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isBreak) return;
			if(playerBtn[idx].isEnabled()) {
				if(players[idx].getStatus() == Player.ISLAND) {
					manager.progressGame(players[idx]);
					return;
				}
				if(players[idx].getStatus() == Player.NO_MONEY) return;
				
				int dice = manager.getDice();
				log(players[idx].getName() + " : �ֻ��� ��ȣ " + dice);
				players[idx].move(dice);
				manager.progressGame(players[idx]);
				
				playerBtn[idx].setEnabled(false);
				playerBtn[idx >= playerBtn.length - 1 ? 0 : idx + 1].setEnabled(true);
			}
		}
		
	}
	
	class PlayerInfoButtonListener implements ActionListener {

		private int idx;
		private GameMap frame;
		
		public PlayerInfoButtonListener(GameMap frame, int idx) {
			this.idx = idx;
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new PlayerDialog(frame, players[idx]);
		}
		
	}
	
	class CityButtonListener implements ActionListener {

		private int idx;
		private GameMap frame;
		
		public CityButtonListener(GameMap frame, int idx) {
			this.idx = idx;
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Player player = manager.getTurnWho();
			Building building = manager.getBuilding(idx);
			
			if(idx % 4 == 0) return;
			if(selectCity.containsKey(player)) {
				int mode = selectCity.get(player);
				if(mode == MODE_TRAVEL_TARGET) {
					if(isBreak) {
						int location = building.getLocation();
						if(location <= player.getLocation()) {
							manager.getManagerEventListener().onStartLocation(player);
						}
						player.setLocation(location);
						Building move = null;
						if((move = manager.getBuilding(location)) != null) {
							if(move.getWho().equals(player)) {
								if(move.getLevel() < Building.LEVEL_3) {
									if(JOptionPane.showConfirmDialog(null, move.getBuildingName() + " �ǹ��� ���׷��̵� �Ͻǰǰ���?", "Ȯ��", JOptionPane.YES_NO_OPTION) == 0)
										manager.getBuildingEventListener().onUpgrade(building, player);
								}
							}else
								manager.getBuildingEventListener().onJoinWho(building, player);
						}
					}
				}else if(mode == MODE_GOLDCARD_SELECT_CITY) {
					if(player.getHasCard() == null) return;
					if(isBreak) {
						if(building.getGoldCard() != null) {
							log("�� �ǹ����� �̹� Ȳ��ī�尡 ��ġ�Ǿ� �ֽ��ϴ�.");
							return;
						}
						building.setGoldCard(player.getHasCard());
						player.removeCard();
						selectCity.remove(player);
						System.out.println(player.getName() + " ���� " + building.getBuildingName() + " �ǹ��� Ȳ��ī�带 ��ġ�Ͽ����ϴ�.");
					}
				}
				
				selectCity.remove(player);
				isBreak = false;
			}else new BuildingDialog(frame, building);
		}
		
	}
}
