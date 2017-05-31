package kr.marble.gui;

import java.awt.Color; 
import java.awt.Font;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private int idx = 0;
	public static boolean isBreak = false;
	
	private Font baseFont = new Font("����ü", Font.PLAIN, 15);
	
	private JLabel winnerLabel = new JLabel("�¸�!");
	
	private JButton button[] = new JButton[32];
	private JButton playerBtn[] = new JButton[4];
	private JButton infoBtn[] = new JButton[4];
	private JLabel imageLabel = new JLabel();
	private JLabel playerDisplay[] = new JLabel[4];
	private Color color[] = {
		Color.RED,
		Color.BLUE,
		Color.GRAY,
		Color.PINK
	};
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
		
		createPlayerDisplay();
		createMap();
		createRight();

		setVisible(true);
	}
	
	public void createMap() {
		for(int i = 0; i < 4; i++) {
			for(int j = i * (button.length / 4); j <= (i * (button.length / 4)) + 7; j ++){
				button[j] = new JButton(new ImageIcon("image/map/" + j + ".png"));
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
	
	public void stopGame(Player winner) {
		winnerLabel.setText(winner.getName() + " " + winnerLabel.getText()); 
		winnerLabel.setFont(new Font("����ü", Font.BOLD, 50));
		winnerLabel.setForeground(Color.RED);
		
		for(int i = 0; i < button.length; i ++)
			remove(button[i]);
		for(int i = 0; i < playerBtn.length; i ++) {
			playerBtn[i].setEnabled(false);
			remove(playerDisplay[i]);
		}
		
		int startX = ((button[12].getX() + button[28].getX()) / 2) - 100;
		int startY = ((button[4].getY() + button[20].getY()) / 2) - 100;
		
		winnerLabel.setBounds(startX, startY, 200, 200);
		add(winnerLabel);
		log(winner.getName() + "���� �¸�!");
		
		repaint();
		new PlayerDialog(this, winner);
	}
	
	public void moveDisplay(int idx) {
		
		int number = players[idx].getLocation();
		if(number == -1) {
			players[idx].setLocation(0);
			number = 0;
		}
		int startX =  (button[number].getWidth() / 2 + button[number].getX()) - 25;
		int startY =  (button[number].getHeight() / 2 + button[number].getY()) - 25;
		
		playerDisplay[idx].setLocation(startX, startY);
	}
	
	public void createPlayerDisplay() {
		
		int startX =  (WIDTH / 2 + x) - 25;
		int startY =  (HEIGHT / 2 + y) - 25;
		
		for(int i = 0; i < playerDisplay.length; i ++) {
			playerDisplay[i] = new JLabel();
			playerDisplay[i].setBounds(startX, startY, 50, 50);
			playerDisplay[i].setOpaque(true);
			playerDisplay[i].setBackground(color[i]);
			add(playerDisplay[i]);
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
	
	public boolean isSelectCity(Player player_) {
		Set<Player> players = selectCity.keySet();
		for(Player player : players) {
			if(player.equals(player_)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void move() {
		playerBtn[idx].setEnabled(false);
		int nextTurn = idx;
		manager.addTurn();
		
		while(true) {
			nextTurn = (nextTurn >= playerBtn.length - 1) ? 0 : nextTurn + 1;
			if(players[nextTurn].getStatus() == Player.NO_MONEY) continue;
			else break;
		}
		idx = nextTurn;
		playerBtn[nextTurn].setEnabled(true);
	}
	
	class EventListener implements IManagerListener, IBuildingListener, IGoldCardListener {

		@Override
		public void onSelectGoldCard(Player player) { // Ȳ��ī�带 �������� ������
			GoldCard card = manager.getSelectGoldCard(player);
			player.setHasCard(card);
			log(player.getName() + "���� "+ card.getName() +"ī�带 �̾ҽ��ϴ�.");
			
			int startWidth = button[12].getX() + button[12].getWidth() + 110;
			int startHeight = button[24].getY() + button[24].getHeight() + 130;
			
			imageLabel.setIcon(new ImageIcon("image/goldcard/" + card.getName() + ".png"));
			imageLabel.setBounds(startWidth, startHeight, 220, 260);
			
			add(imageLabel);
			repaint();
			
			if(card.isNeedBuilding()) {
				if(player.getBuildings() == null) {
					log(player.getName() + "���� �����ϰ� �ִ� �ǹ��� �������� �ʽ��ϴ�.");
					remove(imageLabel);
					repaint();
				}else{
					player.setStatus(Player.STOP);
					GameMap.isBreak = true;
					GameMap.setSelectCity(player, GameMap.MODE_GOLDCARD_SELECT_CITY);
					log("�ǹ��� �����Ͽ� �ش� ī�带 ��ġ�Ͻʽÿ�");
				}
			}else {
				imageLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						if(isBreak && !card.isMove()) {
							remove(imageLabel);
							repaint();
						}
					}
				});
				card.usingGoldCard(player);
				player.removeCard();
				moveDisplay(manager.getTurn());
				remove(imageLabel);
				repaint();
			}
		}

		@Override
		public void onBuyingBuilding(Building building, Player player) { // �ǹ��� ����������
			 log(player.getName()+"���� "+building.getBuildingName()+"�� �����߽��ϴ�.");
			 log(player.getName()+"�� ��� "+player.getMoney().getMoney()+" ���� ���ҽ��ϴ�.");
				
		}

		@Override
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
						building.getWho().getMoney().addMoney(building.getPenalty());
						log(player.getName() + "���� ������ �½��ϴ� ���� �ݾ�  : " + player.getMoney().getMoney() + "��");
					}
						
				}else{
					log(player.getName() + " : ����� ������ �����ϴ�");
					if(building.getLevel() < Building.LEVEL_3) {
						if(JOptionPane.showConfirmDialog(null, building.getBuildingName() + " �ǹ��� ���׷��̵� �Ͻǰǰ���?", "Ȯ��", JOptionPane.YES_NO_OPTION) == 0)
							manager.getBuildingEventListener().onUpgrade(building, player);
					}
				}
				if(building.getGoldCard() != null) {
					building.getGoldCard().returnCard(building);
					building.removeGoldCard();
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
			Building[] buildings = player.getBuildings();
			
			for(Building building : buildings) {
				player.removeBuilding(building);
			}
			
			int idx = manager.getTurn();
			remove(playerDisplay[idx]);
			
			int cnt = 0, notNo = 0;
			for(int i = 0; i < players.length; i ++) {
				if(players[i].getStatus() == Player.NO_MONEY) cnt++;
				else notNo = i;
			}
			
			if(cnt == players.length - 2) 
				stopGame(players[notNo]);
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

		@Override
		public void onSpaceTravel(Player player) {
			GameMap.isBreak = true;
			GameMap.setSelectCity(player, GameMap.MODE_TRAVEL_TARGET);
			player.setStatus(Player.STOP);
			
			log(player.getName() + " : ���ֿ������� �̵��� ������ �����Ͻʽÿ�");
		}
		
	}
	
	class PlayerButtonListener implements ActionListener {

		private GameMap frame;
		private int idx;
		
		public PlayerButtonListener(GameMap frame, int idx) {
			this.frame = frame;
			this.idx = idx;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isBreak) return;
				if(players[idx].getStatus() == Player.ISLAND) {
					manager.progressGame(players[idx]);
				}else{
					int dice = manager.getDice();
					log(players[idx].getName() + " : �ֻ��� ��ȣ " + dice);
					players[idx].move(dice);
					moveDisplay(idx);
					manager.progressGame(players[idx]);
					
					if(manager.isFillTurn()) {
						log(Manager.TURN_LIMIT + "���� ���� �������踦 �����մϴ�...");
						double max = 0;
						Player who = null;
						for(int i = 0; i < players.length; i ++) {
							if(players[i].getStatus() == Player.NO_MONEY) continue;
							if(max < players[i].getProperty()) {
								who = players[i];
								max = who.getProperty();
							}
						}
						
						if(who == null) 
							log("Error");
						else
							stopGame(who);
					}
				}
				if(players[idx].getStatus() != Player.STOP) 
					move();
				
				
			}
		}
	
	class PlayerInfoButtonListener implements ActionListener {
		
		private GameMap frame;
		private int idx;
		
		public PlayerInfoButtonListener(GameMap frame, int idx) {
			this.frame = frame;
			this.idx = idx;
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
			if(isSelectCity(player)) {
				int mode = selectCity.get(player);
				if(mode == MODE_TRAVEL_TARGET) {
					if(isBreak) {
						int location = building.getLocation();
						if(location < player.getLocation()) {
							manager.getManagerEventListener().onStartLocation(player);
						}
						player.setLocation(location);
						manager.progressGame(player);
					}
				}else if(mode == MODE_GOLDCARD_SELECT_CITY) {
					if(player.getHasCard() == null) return;
					if(isBreak) {
						if(building.getGoldCard() != null) {
							log("�� �ǹ����� �̹� Ȳ��ī�尡 ��ġ�Ǿ� �ֽ��ϴ�.");
							return;
						}
						if(building.getWho() == null) {
							log("�� �ǹ��� �����ڴ� �������� �ʽ��ϴ�.");
							return;
						}
						building.setGoldCard(player.getHasCard());
						player.removeCard();
						selectCity.remove(player);
						log(player.getName() + " ���� " + building.getBuildingName() + " �ǹ��� Ȳ��ī�带 ��ġ�Ͽ����ϴ�.");
					}
				}
				remove(imageLabel);
				selectCity.remove(player);
				isBreak = false;
				player.setStatus(Player.NORMAL);
				move();
				
				
			}else new BuildingDialog(frame, building);
		}
		
	}
}
