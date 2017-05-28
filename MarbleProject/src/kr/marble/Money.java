package kr.marble;

public class Money {
	   private double money;

	   public Money(double money) {
	      this.money = money;
	   }

	   public double getMoney() {
	      return money;
	   }

	   public void setMoney(double money) {
	      this.money = money;
	   }

	   public void minusMoney(double money) {
	      this.money -= money;
	   }

	   public void addMoney(double money) {
	      this.money += money;
	   }
}