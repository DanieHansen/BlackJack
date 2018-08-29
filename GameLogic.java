import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
	
	//Classes
	Deck deck = new Deck();
	
	//ArrayLists
	ArrayList<Player> humans = new ArrayList<Player>();
	ArrayList<Player> computers = new ArrayList<Player>();
	ArrayList<Player> combined = new ArrayList<Player>();
	
	//Variables
	int computerPlayersAmount;
	int humanPlayersAmount;
	int tableTotal = 0;
	
	//Scanner
	Scanner keyboard = new Scanner(System.in);
	
	//start, "Constructor"
	public void setUp()
	{		
		while(true)
		{
			System.out.println("Everyone starts with $1000, bet wisely\n");
			/*
			 * future project
			while(true)
			{
				String message = "";
				System.out.println("How many computers would you like?");
				
				try
				{
					computerPlayersAmount = keyboard.nextInt();
				}
				catch(InputMismatchException e)
				{
					message = "Please input a number.";
					System.out.println(message);
					keyboard.next();
				}
				
				if(!message.contains("Please input a number"))
				{
					break;
				}
			}
			*/
			while(true)
			{
				String message = "";
				System.out.println("How many human players are there?");
				
				try
				{
					humanPlayersAmount = keyboard.nextInt();
				}
				catch(InputMismatchException e)
				{
					message = "Please input a number.";
					System.out.println(message);
					keyboard.next();
				}
				
				boolean noException =!message.contains("Please input a number");
				boolean doesNotExceed7 = (humanPlayersAmount + computerPlayersAmount) < 7;
				
				if(noException && doesNotExceed7)
				{
					break;
				}
				else if(!doesNotExceed7)
				{
					System.out.println("There's a maximum of 7 players. You've input too many players.");
				}
			}
			
			
			if(computerPlayersAmount == 0 && (humanPlayersAmount == 0 || humanPlayersAmount == 1))
			{
				System.out.println("Invalid input");
				continue;
			}
			else
			{
				break;
			}
		}
		
		for(int j = 0; j < computerPlayersAmount; j++)
		{
			computers.add(new Player("Computer " + (j + 1)));
			combined.add(computers.get(j));
			
			for(int i = 0; i < 2; i++)
			{
				computers.get(j).addCard(deck.pullRandomCard());
			}
		}
		
		for(int j = 0; j < humanPlayersAmount; j++)
		{
			humans.add(new Player("Player " + (j + 1)));
			combined.add(humans.get(j));
			
			for(int i = 0; i < 2; i++)
			{
				humans.get(j).addCard(deck.pullRandomCard());
			}			
		}

		if(computerPlayersAmount == 1 && humanPlayersAmount == 1)
		{
			//System.out.println("There is " + computerPlayersAmount + " computer and " + humanPlayersAmount + " human player.");
		}
		else if(computerPlayersAmount == 1 && humanPlayersAmount != 1)
		{
			//System.out.println("There is " + computerPlayersAmount + " computer and " + humanPlayersAmount + " human players.");
		}
		else if(computerPlayersAmount != 1 && humanPlayersAmount == 1)
		{
			//System.out.println("There are " + computerPlayersAmount + " computers and " + humanPlayersAmount + " human player.");
		}
		else
		{
			//System.out.println("There are " + computerPlayersAmount + " computers and " + humanPlayersAmount + " human players.");
			System.out.println("There are " + humanPlayersAmount + " human players.");
		}
		
		System.out.println("\nHumans will be referred to as 'Player 1', 'Player 2', and so on.");
		System.out.println("You can only change it now.");
		
	}
	//setters
	public void setTableTotal(int tableTotal)
	{
		this.tableTotal = tableTotal;
	}
	
	//getters
	public int getTableTotal()
	{
		return tableTotal;
	}
	
	public int getTotalPlayerAmount()
	{
		return computerPlayersAmount + humanPlayersAmount;
	}
	
	public int getHumanPlayerAmount()
	{
		return humanPlayersAmount;
	}
	
	public int getComputerPlayerAmount()
	{
		return computerPlayersAmount;
	}
	
	public ArrayList<String> getAllPlayerNameList()
	{
		ArrayList<String> allPlayersNames = new ArrayList<String>();
		for(int i = 0; i < humanPlayersAmount; i++)
		{
			allPlayersNames.add(humans.get(i).getName());
		}
		
		for(int i = 0; i < computerPlayersAmount; i++)
		{
			allPlayersNames.add(computers.get(i).getName());
		}
		
		return allPlayersNames;
	}
	
	public ArrayList<String> getHumanPlayersNameList()
	{
		int amount = humanPlayersAmount;
		ArrayList<String> humanPlayerNames = new ArrayList<String>();
		for(int i = 0; i < amount; i++)
		{
			humanPlayerNames.add(humans.get(i).getName());
		}
		return humanPlayerNames;
	}
	
	public ArrayList<String> getInPlayersNameList()
	{
		int amount = humanPlayersAmount;
		ArrayList<String> humanPlayerNames = new ArrayList<String>();
		for(int i = 0; i < amount; i++)
		{
			if(humans.get(i).getCardTotal() < 22)
			{
				humanPlayerNames.add(humans.get(i).getName());
			}
		}
		return humanPlayerNames;
	}
	
	public ArrayList<String> getOutPlayersNameList()
	{
		int amount = humanPlayersAmount;
		ArrayList<String> humanPlayerNames = new ArrayList<String>();
		for(int i = 0; i < amount; i++)
		{
			if(!(humans.get(i).getCardTotal() < 22))
			{
				humanPlayerNames.add(humans.get(i).getName());
			}
		}
		return humanPlayerNames;
	}
	
	public Player getHuman(int position)
	{
		return humans.get(position);
	}
	
	public Player getComputer(int position)
	{
		return computers.get(position);
	}
	
	public Player getPlayer(int position)
	{
		return combined.get(position);
	}
	
	public ArrayList<Player> getAllPlayers()
	{
		return humans;
	}
	
	public int calculateCardTotal(ArrayList<String> hand, int player)
	{
		Scanner input = new Scanner(System.in);
		int total = 0;
		String answer = "";
		
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
			if(hand.get(i).isEmpty())
			{
				total = 0;
			}
			if(hand.get(i).contains("Ace"))
			{
				String message = humans.get(player).getName() + ", do you want your Ace to be worth 1 or 11?";
				System.out.print(message);
				answer = input.next();
				if(answer.equals("1"))
				{
					total += 1;
				}
				else if(answer.equals("11"))
				{
					total += 11;
				}
			}
		}
		
		if(total > 21)
		{
			System.out.println("You've exceeded 21! You're out.");
		}
		humans.get(player).setCardTotal(total);
		return total;
	}
	
	public Deck getDeck()
	{
		return deck;
	}
	
	//mutators
	public void playerChoice(int player)
	{
		int total = this.calculateCardTotal(humans.get(player).getHand(), player);
		while(true)
		{
			Scanner input = new Scanner(System.in);
			System.out.println("\nWould you like to hit, stand/pass, or surrender");
			String choice = keyboard.next();
			if(choice.toLowerCase().contains("hit"))
			{
				while(true)
				{
					if(total < 22)
					{
						String newCard = deck.pullCard(0);
						humans.get(player).addCard(newCard);
						System.out.println("You got " + newCard);
					}
					
					total = this.calculateCardTotal(humans.get(player).getHand(), player);
					System.out.print( "\nNew Total: " + total + "\n");
					
					if(total < 22)
					{
						System.out.println("\nDo you want to hit again?");
						String hit = input.next();
	
						if(hit.toLowerCase().contains("y") || hit.toLowerCase().contains("hit"))
						{
							continue;
						}
						else if(hit.toLowerCase().contains("n") || hit.toLowerCase().contains("s") || hit.toLowerCase().contains("p"))
						{
							break;
						}
						else
						{
							System.out.println("Unknown input.");
							break;
						}
						
					}
					else
					{
						break;
					}
				}
				break;
			}
			else if(choice.toLowerCase().contains("stand") || choice.toLowerCase().contains("pass"))
			{
				System.out.println("Card Total: ");
				System.out.print(this.calculateCardTotal(humans.get(player).getHand(),player));
				break;
			}
			else if(choice.toLowerCase().contains("surrender"))
			{
				humans.get(player).removeAllCards();
				break;
			}
			else
			{
				System.out.println("Unknown input.");
			}
		}
	}
	
	public void placeBets(int humanPosition)
	{
		int amount = 0;
		
		while(true)
		{
			System.out.println("How much are you betting?");
			String message = "";
			
			try
			{
				amount = keyboard.nextInt();
			}
			catch(InputMismatchException e)
			{
				message = "Please input a number.";
				System.out.println(message);
				keyboard.next();
			}
			
			boolean noException =!message.contains("Please input a number");
			boolean doesNotExceedAmount = amount <= this.getHuman(humanPosition).getCash();
			
			if(noException && doesNotExceedAmount)
			{
				break;
			}
		}
		
		tableTotal += amount;
		this.getHuman(humanPosition).removeCash(amount);
	}

	public void changeName(int player)
	{	
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to change your name " + humans.get(player).getName() + "?");
		String answer = input.next();
		while(true)
		{	
			if(answer.toLowerCase().contains("y"))
			{
				System.out.println("What name do you want to change it to?");
				String newName = input.next();
				humans.get(player).changeName(newName);
			}
			else
			{
				System.out.println("Okay, you will be known as " + humans.get(player).getName());
			}
			
			System.out.println("Are you happy with the name: " + humans.get(player).getName() + "?");
			String changeNameAgain = input.next();
			
			if(changeNameAgain.toLowerCase().contains("y"))
			{
				break;
			}
			else if(changeNameAgain.toLowerCase().contains("n"))
			{
				System.out.println("What name do you want to change it to?");
				String newName = input.next();
				humans.get(player).changeName(newName);
				continue;
			}
			else
			{
				System.out.println("Unknown input.");
				continue;
			}
		}
		
	}
	
}
