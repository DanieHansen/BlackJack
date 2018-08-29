import java.util.ArrayList;
import java.util.Collections;
public class Deck
{
	
	ArrayList <String> deck = new ArrayList<String>();
	String suit = " of Diamonds";
	public Deck()
	{
		for(int i = 0; i < 4; i++)
		{
			deck.add("Ace" + suit);
			deck.add("2"+ suit);
			deck.add("3"+ suit);
			deck.add("4"+ suit);
			deck.add("5"+ suit);
			deck.add("6"+ suit);
			deck.add("7"+ suit);
			deck.add("8"+ suit);
			deck.add("9"+ suit);
			deck.add("10"+ suit);
			deck.add("Jack"+ suit);
			deck.add("Queen"+ suit);
			deck.add("King"+ suit);
			if(i == 0)
			{
				suit = " of Clubs";
			}
			if(i == 1)
			{
				suit = " of Spades";
			}
			if(i == 2)
			{
				suit = " of Hearts";
			}
		}
		Collections.shuffle(deck);
	}
	
	public ArrayList<String> getDeck()
	{
		return deck;
	}
	
	public String getCard(int index)
	{
		return deck.get(index);
	}
	public String getCard(String index)
	{
		return deck.get(deck.indexOf(index));
	}
	public String pullCard(String index)
	{
		String card = deck.get(deck.indexOf(index));
		deck.remove(index);
		return card;
	}
	
	public String pullCard(int index)
	{
		String card = deck.get(index);
		deck.remove(index);
		return card;
	}
	
	public String pullRandomCard()
	{
		int random = (int)(Math.random()*(deck.size()-1) +1);
		String card = deck.get(random);
		deck.remove(card);
		return card;
	}
	
	public void reshuffle()
	{
		Collections.shuffle(deck);
	}
}