import java.util.Scanner;

public class Player
	{
		static int playerLocation;
		static boolean inJail = false;
		static int playerMoney = 1500;
		static boolean stillPlaying = true;
		static String playerName;
		static int menuInput;
		static int numberOfPropertiesOwned;
		static int numberOfUtilitiesOwned;
		static int numberOfRailroadsOwned;
		static int freeParkingMoney;
		static int timesRolledDoubles = 0;
		static Scanner userInput = new Scanner(System.in);
		
		public static void greetPlayer()
		{
			System.out.println("What is your name?");
			playerName = userInput.nextLine();
			System.out.println("Welcome, " + playerName + "!");
		}
		
		public static void turnMenu()
		{
			System.out.println(playerName + ", it's your turn!\nWould you like to...\n1) Roll the dice\n2) View your stats");
			menuInput = userInput.nextInt();
			
			if(menuInput == 1)
				{
					movePlayer();
				}
			else if(menuInput == 2)
				{
					displayPlayerStats();
				}
			else
				{
					System.out.println("That's not an option.\nPlease try again.");
					turnMenu();
				}
		}
		
		public static void displayPlayerStats()
		{
			System.out.println("Your name is: " + playerName);
			System.out.println("Current balance: $" + playerMoney);
			System.out.println("Number of properties owned: " + numberOfPropertiesOwned);
			System.out.println("Number of utilities owned: " + numberOfUtilitiesOwned);
			System.out.println("Number of railroads owned: " + numberOfRailroadsOwned);
		}
		
		public static void movePlayer()
		{
			System.out.println("Press space to roll the dice.");
			String rollDiceInput = userInput.nextLine();
			
			int playerRoll = DiceRoller.rollDice(2,6);
			
			if((playerLocation + playerRoll) < 39)
				{
					playerLocation += playerRoll;
				}
			else
				{
					playerLocation = (playerLocation + playerRoll) - 40;
					playerMoney += 200;
					System.out.println("You passsed GO and collected $200");
				}
			
			System.out.println("You rolled: " + playerRoll + "\nYou landed on " + MonopRun.board[playerLocation].getName());
			
			landOnSquare();
			
			if(DiceRoller.doubles == true)
				{
					timesRolledDoubles++;
					
					if(timesRolledDoubles == 3)
						{
							goToJail();
						}
					else
						{
							System.out.println("You rolled doubles, so you get to roll again!");
							movePlayer();
						}
				}
			else
				{
					
				}
		}

		public static void checkForBankruptcy()
			{
				if(playerMoney <= 0)
					{
						System.out.println("You've gone bankrupt!");
						stillPlaying = false;
					}		
			}
		
		public static void goToJail()
			{
				playerLocation = 10;
				System.out.println("You are now in jail");
				inJail = true;
			}
		
		public static void landOnSquare()
			{
				if(MonopRun.board[playerLocation].getType().equals("Property"))
					{
						if(MonopRun.board[playerLocation].getOwner().equals("none"))
							{
							System.out.println("This location is not owned, would you like to buy it?\n1) Yes\n2) No");
							menuInput = userInput.nextInt();
							
							if(menuInput == 1)
								{
									MonopRun.board[playerLocation].setOwner(playerName);
									playerMoney -= MonopRun.board[playerLocation].getCost();
									checkForBankruptcy();
									numberOfPropertiesOwned++;
								}
							//need to implement the 'developing' feature (where you can only buy houses if you own all of that color)
							}
						else if(MonopRun.board[playerLocation].getOwner().equals(playerName))
							{
								System.out.println("You already own this property...\nWould you like to buy houses or a hotel?\n1) Yes\n2) No");
								menuInput = userInput.nextInt();
								
								if(menuInput == 1)
									{
										if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() >= 4)
											{
												System.out.println("Sorry, you have already bought the maximum amount of property on this space.");
											}
										else
											{
											//this is broken and I don't know how to fix it at this time
											//(Properties) MonopDriver.board[playerLocation]).setNumberOfHousesOwned(((Properties) MonopDriver.board[playerLocation]).getNumberOfHousesOwned()+1);
											}
									}
							}
						else
							{
								System.out.println("This property is already owned by" + MonopRun.board[playerLocation].getOwner() + ", you now must pay rent.");
								if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() == 0)
									{
										playerMoney -= ((Properties) MonopRun.board[playerLocation]).getBasicRent();
									}
								else if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() == 1)
									{
										playerMoney -= ((Properties) MonopRun.board[playerLocation]).getOneHouseRent();
									}
								else if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() == 2)
									{
										playerMoney -= ((Properties) MonopRun.board[playerLocation]).getTwoHouseRent();
									}
								else if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() == 3)
									{
										playerMoney -= ((Properties) MonopRun.board[playerLocation]).getThreeHouseRent();
									}
								else if(((Properties) MonopRun.board[playerLocation]).getNumberOfHousesOwned() == 4)
									{
										playerMoney -= ((Properties) MonopRun.board[playerLocation]).getHotelRent();
									}
								
								checkForBankruptcy();
							}
					}
				else if(MonopRun.board[playerLocation].getType().equals("Utility"))
					{
						if(MonopRun.board[playerLocation].getOwner().equals("none"))
							{
							System.out.println("This location is not owned, would you like to buy it?\n1) Yes\n2) No");
							menuInput = userInput.nextInt();
							
							if(menuInput == 1)
								{
									MonopRun.board[playerLocation].setOwner(playerName);
									playerMoney -= MonopRun.board[playerLocation].getCost();
									checkForBankruptcy();
									numberOfUtilitiesOwned++;
								}
							}
						else if(MonopRun.board[playerLocation].getOwner().equals(playerName))
							{
								System.out.println("You already own this utility");
							}
						else
							{
								System.out.println("This utility is already owned by " + MonopRun.board[playerLocation].getOwner() + ", you now must pay for the service.");
								//need to figure out how to search the array for everything that has one specific field
							}
					}
				else if(MonopRun.board[playerLocation].getType().equals("Railroad"))
					{
						if(MonopRun.board[playerLocation].getOwner().equals("none"))
							{
							System.out.println("This location is not owned, would you like to buy it?\n1) Yes\n2) No");
							menuInput = userInput.nextInt();
							
							if(menuInput == 1)
								{
									MonopRun.board[playerLocation].setOwner(playerName);
									playerMoney -= MonopRun.board[playerLocation].getCost();
									checkForBankruptcy();
									numberOfRailroadsOwned++;
								}
							}
						else if(MonopRun.board[playerLocation].getOwner().equals(playerName))
							{
								System.out.println("You already own this railroad");
							}
						else
							{
								//once again need to figure the comment from above out
								
								/*System.out.println("This property is already owned by" + MonopDriver.board[playerLocation].getOwner() + ", you now must pay rent.");
								if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 1)
									{
										playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getOneOwnedRent();
									}
								else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 2)
									{
										playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getTwoOwnedRent();
									}
								else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 3)
									{
										playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getThreeOwnedRent();
									}
								else if(((Railroads) MonopDriver.board[playerLocation]).getNumberOfHousesOwned() == 4)
									{
										playerMoney -= ((Railroads) MonopDriver.board[playerLocation]).getFourOwnedRent();
									}*/
								
								checkForBankruptcy();
							}
					}
				else
					{
						if(MonopRun.board[playerLocation].getName().equals("GO"))
							{
								playerMoney += MonopRun.board[playerLocation].getCost();
								System.out.println("You collected $" + MonopRun.board[playerLocation].getCost());
							}
						else if(MonopRun.board[playerLocation].getName().equals("Go_To_Jail"))
							{
								goToJail();
							}
						else if(MonopRun.board[playerLocation].getName().equals("Free_Parking"))
							{
								System.out.println("You won " + freeParkingMoney + "!");
								playerMoney += freeParkingMoney;
								freeParkingMoney = 0;
							}
						else if(MonopRun.board[playerLocation].getName().equals("Income_Tax") || MonopRun.board[playerLocation].getName().equals("Luxury_Tax"))
							{
								System.out.println("You have been taxed $" + MonopRun.board[playerLocation].getCost());
								freeParkingMoney += MonopRun.board[playerLocation].getCost();
								playerMoney -= MonopRun.board[playerLocation].getCost();;
							}
						else {	
						if (MonopRun.board[playerLocation].getName().equals("Chance"))
							{
								int chanceNumber = (int) (Math.random() * 3) + 1;
								if (chanceNumber == 1)
									{
										System.out.println(
												"You're wife left you. Give 50% of your money to her.");
										playerMoney = playerMoney / 2;
									}

								else
									if (chanceNumber == 2)
										{
											System.out.println(
													"You got your secretary pregnant. Pay $50.");
											playerMoney = playerMoney - 50;
										} else
										{
											System.out.println("You found $100 on the street.");
											playerMoney = playerMoney + 100;
										}
							}
						
						// community chest
						else
							if (MonopRun.board[playerLocation].getName()
									.equals("Community Chest"))
								{
									int chestNumber = (int) (Math.random() * 3) + 1;
									if (chestNumber == 1)
										{
											System.out.println(
													"Your dog needs a surgery. Pay the vet $100.");
											playerMoney = playerMoney / 2;
										}

									else
										if (chestNumber == 2)
											{
												System.out.println(
														"You have turned to a life of crime. You mugged a teenage girl for $35.");
												playerMoney = playerMoney + 35;
											} else
											{
												System.out.println(
														"You bought the Fortnite battle pass ofr $10.");
												playerMoney = playerMoney - 10;
											}
								}

						
					
				
			}
					}
			}
		public static void inJailTurn()
			{
				//need to implement cards soon
				System.out.println("You are in jail\nWould you like to...\n1) Pay the $50 fee\n2) Roll for doubles");
				menuInput = userInput.nextInt();
				
				if(menuInput == 1)
					{
						freeParkingMoney += 50;
						playerMoney -= 50;
						System.out.println("You have now payed the fee and are free to go.");
						inJail = false;
					}
				else
					{
						DiceRoller.rollDice(2,6);
						if(DiceRoller.doubles == true)
							{
								System.out.println("You rolled doubles and are free to go.");
								inJail = false;
							}
						else
							{
								System.out.println("You failed to roll doubles.");
							}
					}
			}
	}
