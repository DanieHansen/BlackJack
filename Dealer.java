import java.util.ArrayList;
import java.util.Scanner;

public class Dealer {

	Player dealer = new Player("Dealer");
	Deck deck;
	
	public Dealer(Deck deck)
	{
		this.deck = deck;
		dealer.addCard(deck.pullRandomCard());
		dealer.addCard(deck.pullRandomCard());
		this.calculateDealerCardTotal();
	}
	
	public void restartDealer(Deck deck)
	{
		this.deck = deck;
		dealer.addCard(deck.pullRandomCard());
		dealer.addCard(deck.pullRandomCard());
		this.calculateDealerCardTotal();
	}

	public int calculateDealerCardTotal()
	{
		ArrayList<String> hand = dealer.getHand();
		Scanner input = new Scanner(System.in);
		int total = 0;
		for(int i = 0; i < hand.size(); i++)
		{
			if(hand.get(i).contains("King"))
			{
				total += 10;
			}
			if(hand.get(i).contains("Queen"))
			{
				total += 10;
			}
			if(hand.get(i).contains("Jack"))
			{
				total += 10;
			}
			if(hand.get(i).contains("10"))
			{
				total += 10;
			}
			if(hand.get(i).contains("9"))
			{
				total += 9;
			}
			if(hand.get(i).contains("8"))
			{
				total += 8;
			}
			if(hand.get(i).contains("7"))
			{
				total += 7;
			}
			if(hand.get(i).contains("6"))
			{
				total += 6;
			}
			if(hand.get(i).contains("5"))
			{
				total += 5;
			}
			if(hand.get(i).contains("4"))
			{
				total += 4;
			}
			if(hand.get(i).contains("3"))
			{
				total += 3;
			}
			if(hand.get(i).contains("2"))
			{
				total += 2;
			}
			if(hand.get(i).contains("Ace"))
			{
				if(total < 11)
				{
					total += 11;
				}
				else
				{
					total += 1;
				}
			}
		}
		
		
		dealer.setCardTotal(total);
		return total;
	}
	
	public Boolean checkDealersTotal()
	{
		if(dealer.cardTotal > 21)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public String getDealerFirstCard()
	{
		String result = "Dealers first card: " + dealer.getCard(0);
		return result;
	}
	
	public ArrayList<String> getDealerHand()
	{
		return dealer.getHand();
	}
	
	public void updateDeck(Deck deck)
	{
		this.deck = deck;
	}
	
}