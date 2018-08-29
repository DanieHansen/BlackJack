import java.util.ArrayList;
public class Player
{
	//Variables
	ArrayList<String> hand = new ArrayList<String>();
	String name;
	int cash = 1000;
	int cardTotal = 0;
	
	//Constructor
	public Player(String name)
	{
		this.name = name;
	}
	
	//getters
	public ArrayList<String> getHand()
	{
		return hand;
	}
	
	public String getCard(int index)
	{
		return hand.get(index);
	}
	
	public String getCard(String index)
	{
		return hand.get(hand.indexOf(index));
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCash()
	{
		return cash;
	}
	
	public int getCardTotal()
	{
		return cardTotal;
	}
	
	//setters	
	public void addCard(String card)
	{
		hand.add(card);
	}
	
	public void printName()
	{
		System.out.println(name);
	}
	
	public void printHand()
	{
		System.out.println(hand);
	}
	
	public void removeCard(int index)
	{
		hand.remove(index);
	}
	
	public void removeCard(String index)
	{
		hand.remove(index);
	}
	
	//Mutators
	public void addCash(int amount)
	{
		cash += amount;
	}
	
	public void removeCash(int amount)
	{
		cash -= amount;
	}
	
	public void removeAllCards()
	{
		hand.clear();
	}
	
	public void changeName(String name)
	{
		this.name = name;
	}
	
	public void setCardTotal(int amount)
	{
		cardTotal = amount;
	}
	
}
