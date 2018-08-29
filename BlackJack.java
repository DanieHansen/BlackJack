import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
	
	public static void main(String args[])
	{		
		//Classes
		GameLogic game = new GameLogic();
		Dealer dealer = new Dealer(game.getDeck());
		
		//Scanner
		Scanner keyboard = new Scanner(System.in);
		
		//Class use
		game.setUp();
		dealer.updateDeck(game.getDeck());
		
		//Variables separated by type
		//int computerPlayers = game.getComputerPlayerAmount();
		int humanPlayers = game.getHumanPlayerAmount();
		int totalPlayers = game.getTotalPlayerAmount();
		int tableTotal = game.getTableTotal();
		
		String message = "";
		
		if(humanPlayers > 0)
		{
			for(int i = 0; i < humanPlayers; i++)
			{
				game.changeName(i);
				game.placeBets(i);
				tableTotal = game.getTableTotal();
			}
	
			int out = 0;
			
			while(true)
			{
				for(int i = 0; i < humanPlayers; i++)
				{
					System.out.println(dealer.getDealerFirstCard());
					boolean emptyHand = game.getHuman(i).getHand().isEmpty();
					
					if(!emptyHand && out != (totalPlayers - 1))
					{
						message = "\nPlayers: " + game.getInPlayersNameList();
						message += "\nTotal amount on the table: " + tableTotal + "\n";
						System.out.println(message);
						
						game.getHuman(i).printName();
						game.getHuman(i).printHand();

						int total = game.calculateCardTotal(game.getHuman(i).getHand(), i);
						
						message = "Your cash: " + game.getHuman(i).getCash();
						message += "\nAll of your cards add up to " + total;
						System.out.println(message);
						
						if(total < 22)
						{
							game.playerChoice(i);
						}
						
						if(game.getHuman(i).getCardTotal() > 21)
						{
							System.out.println("Cards removed.");
							game.getHuman(i).removeAllCards();
							game.calculateCardTotal(game.getHuman(i).getHand(), i);
						}
					}
					
					if(game.getHuman(i).getHand().isEmpty() && out != (totalPlayers - 1))
					{
						System.out.println("\n"+game.getHuman(i).getName() + " has no more cards.");
						out += 1;
					}
					
					if(out == (totalPlayers - 1))
					{
						for(int p = 0; p < totalPlayers; p++)
						{
							if(!game.getHuman(p).getHand().isEmpty())
							{
								message = game.getHuman(p).getName() + " is the only one left. If you beat the dealer, you win.";
								message += "\n" + game.getHuman(p).getName() + "'s hand :" + game.getHuman(p).getHand();
								System.out.println(message);
								break;
							}
						}
					}
				}
				
				dealer.updateDeck(game.getDeck());
				System.out.println("\nDealers hand: " + dealer.getDealerHand());
				dealer.calculateDealerCardTotal();
				
				ArrayList<Player> winners = new ArrayList<Player>();
				for(int i = 0; i < game.getTotalPlayerAmount(); i++)
				{
					winners.add(game.getHuman(i));
				}
				
				int total =  winners.size();
				
				for(int i = 0; i < total; i++)
				{
					game.calculateCardTotal(winners.get(i).getHand(), i);
				}

				winners.add(dealer.dealer);
				
				for(int i = 0; i < total; i++)
				{
					if(winners.get(i).getCardTotal() == 0)
					{
						winners.remove(i);
						total -= 1;
					}
				}
				total = winners.size();
				Player winner = winners.get(0);
				for(int i = 0; i < total; i++)
				{
					if(winner.getCardTotal() < winners.get(i).getCardTotal())
					{
						winner = winners.get(i);
					}
				}
				
				if(!winner.getName().equals("Dealer"))
				{
					System.out.println(winner.getName() + " has won!\n");
					winner.addCash(tableTotal);
				}
				else
				{
					System.out.println("Dealer has won.\n");
				}
				
				for(int j = 0; j < totalPlayers; j++)
				{
					game.getHuman(j).removeAllCards();
				}
				
				for(int j = 0; j < totalPlayers; j++)
				{
					if(game.getDeck().deck.size() > (totalPlayers * 2))
					{
						game.getHuman(j).addCard(game.getDeck().pullRandomCard());
						game.getHuman(j).addCard(game.getDeck().pullRandomCard());
					}
					else
					{
						System.out.println("We're out of cards! Game over.");
						System.exit(0);
					}
				}
				String answer;
				while(true)
				{
					System.out.println("Continue?");
					answer = keyboard.next();
					if(answer.toLowerCase().contains("yes"))
					{
						System.out.println("Continuing.\n");
						tableTotal = 0;
						
						for(int i = 0; i < humanPlayers; i++)
						{
							game.getPlayer(i).printName();
							game.placeBets(i);
							tableTotal = game.getTableTotal();
						}
						
						out = 0;
						dealer.dealer.removeAllCards();
						dealer.restartDealer(game.getDeck());
						dealer.updateDeck(game.getDeck());
						break;
					}
					else if(answer.toLowerCase().contains("no"))
					{
						System.out.println("Thanks for playing.");
						break;
					}
					else
					{
						System.out.println("Invalid response.");
					}
				}
				
				if(answer.toLowerCase().contains("no"))
				{
					break;
				}
				
			}
		}
		/*
		else
		{
		//
			System.out.println("There are no human players so you'll watch computers play");
		//
		}
		*/
	}
//
}