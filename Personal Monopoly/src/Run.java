import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Run
	{
static BoardSpace[] board = new BoardSpace[40];
static Scanner file;		
static Scanner b;
static Scanner z;

		public static void main(String[] args) throws IOException
			{
				//greeting();
//				for(BoardSpace b : board)
//					{
//						System.out.println(b.getType());
//					}
//				
			selectVersion();
			
			}	
		public static void greeting()
		{
			System.out.println("Hello, welcome to the world of Monopoly!");
			System.out.println("What is player one's name?");
			System.out.println("What is player two's name?");
		}
		public static void selectVersion() throws IOException
		{
				
				Scanner tanner = new Scanner(System.in);
				System.out.println("What theme would you like?");
				System.out.println("1. Original/American Monopoly");
				System.out.println("2. Fortnite Monopoly");
				int choice = tanner.nextInt();
				
				if(choice == 1)
					{
						file = new Scanner(new File("AmericanMonopoly.txt"));
						americanBoard(b);
					}
				else
					{
						file = new Scanner(new File("FortniteMonopoly.txt"));
						fortniteBoard(b);
					}
					
		}
				public static void americanBoard(Scanner b)
				{
					Player.greetPlayer();
				      while(Player.stillPlaying == true)
				    	  {
				    		  if(Player.inJail == false)
				    			  {
				    				  Player.turnMenu();
				    			  }
				    		  else
				    			  {
				    				  Player.inJailTurn();
				    			  }
				    	  }
				
		
				int numberOfLines = b.nextInt();
			     
			      for( int i = 0; i < numberOfLines; i++ )
			          {
			        	   String type = b.next();
			        	   System.out.println(type);
			        	  /*the txt file will be structured as follows:
			        	   * the first line will be an int that represents the amount of lines that follow it (think A+)
			        	   *each line will be one square
			        	   *type cost owner location
			        	   *then whatever applies to each thing (properties or taxes etc.)
			        	  */
			        	  if(type.equals("Property"))
			        		  {
			        			  		
			        					  String n = b.next(); 
			        					  String o = b.next(); 
			        					  int c = b.nextInt(); 
			        					  int l = b.nextInt(); 
			        					  boolean d = b.nextBoolean();
			        					  int r = b.nextInt(); 
			        					  int h = b.nextInt(); 
			        					  int or = b.nextInt(); 
			        					  int tr = b.nextInt(); 
			        					  int thr = b.nextInt(); 
			        					  int hr = b.nextInt();
			        					  int hn = b.nextInt();
			        					  String co = b.next();
			        			  board[i] = new Properties("Property", n, o, c, l, d, r, h, or, tr, thr, hr, hn, co);
			        		  }
			        	  
			        	  else if(type.equals("Railroad"))
			        		  {
			        			  
			        					 String rn = b.next(); 
			        					 String ro = b.next(); 
			        					  int rc = b.nextInt(); 
			        					  int r1 = b.nextInt(); 
			        					  int rr1 = b.nextInt(); 
			        					  int rr2 = b.nextInt(); 
			        					  int rr3 = b.nextInt();
			        					  int rr4 = b.nextInt();
			        			  board[i] = new Railroads("Railroad", rn, ro, rc, r1, rr1, rr2, rr3, rr4);
			        		  }
			        	  
			        	  else if(type.equals("Utility"))
			        		  {
			        			  
			        					  String un = b.next();
			        					  String uo = b.next();
			        					  int uc = b.nextInt();
			        					  int ul = b.nextInt();
			        					  int r1 = DiceRoller.rollDice(2,6)*4;
			        					  int r2 = DiceRoller.rollDice(2,6)*10;
			        			  board[i] = new Utilities("Utility", un, uo, uc, ul, r1, r2);
			        		  }
			        	  else if(type.equals("Other"))
			        		  {
			        			 
			        					  String name = b.next();
			        					  String owner = b.next();
			        					  int cost = b.nextInt();
			        					 int location = b.nextInt();
			        			   board[i] = new BoardSpace("Other", name, owner, cost, location);
			        		  }
			          }
			      for(BoardSpace s: board)
			    	  {
			    		  System.out.println(s);
			    	  }
			    	 
			    	  }
				public static void fortniteBoard(Scanner z)
					{
						Player.greetPlayer();
					      while(Player.stillPlaying == true)
					    	  {
					    		  if(Player.inJail == false)
					    			  {
					    				  Player.turnMenu();
					    			  }
					    		  else
					    			  {
					    				  Player.inJailTurn();
					    			  }
					    	  }
					
			
					int numberOfLines = z.nextInt();
				     
				      for( int i = 0; i < numberOfLines; i++ )
				          {
				        	   String type = z.nextLine();
				        	  /*the txt file will be structured as follows:
				        	   * the first line will be an int that represents the amount of lines that follow it (think A+)
				        	   *each line will be one square
				        	   *type cost owner location
				        	   *then whatever applies to each thing (properties or taxes etc.)
				        	  */
				        	  if(type.equals("Property"))
				        		  {
				        			  
				        					  String n = z.next(); 
				        					  String o = z.next(); 
				        					  int c = z.nextInt(); 
				        					  int l = z.nextInt(); 
				        					  boolean d = z.nextBoolean();
				        					  int r = z.nextInt(); 
				        					  int h = z.nextInt(); 
				        					  int or = z.nextInt(); 
				        					  int tr = z.nextInt(); 
				        					  int thr = z.nextInt(); 
				        					  int hr = z.nextInt();
				        					  int hn = z.nextInt();
				        					  String co = z.next();
				        			  board[i] = new Properties("Property", n, o, c, l, d, r, h, or, tr, thr, hr, hn, co);
				        		  }
				        	  
				        	  else if(type.equals("Gas_Station"))
				        		  {
				        			  
				        					 String rn = z.next(); 
				        					 String ro = z.next(); 
				        					  int rc = z.nextInt(); 
				        					  int r1 = z.nextInt(); 
				        					  int rr1 = z.nextInt(); 
				        					  int rr2 = z.nextInt(); 
				        					  int rr3 = z.nextInt();
				        					  int rr4 = z.nextInt();
				        			  board[i] = new Railroads("Gas_station", rn, ro, rc, r1, rr1, rr2, rr3, rr4);
				        		  }
				        	  
				        	  else if(file.next().equals("Utility"))
				        		  {
				        			  
				        					  String un = z.next();
				        					  String uo = z.next();
				        					  int uc = z.nextInt();
				        					  int ul = z.nextInt();
				        					  int r1 = DiceRoller.rollDice(2,6)*4;
				        					  int r2 = DiceRoller.rollDice(2,6)*10;
				        			  board[i] = new Utilities("Utility", un, uo, uc, ul, r1, r2);
				        		  }
				        	  else if(type.equals("Other"))
				        		  {
				        			 
				        					  String name = z.next();
				        					  String owner = z.next();
				        					  int cost = z.nextInt();
				        					 int location = z.nextInt();
				        			   board[i] = new BoardSpace("Other", name, owner, cost, location);
				        		  }
				          }
				      for(BoardSpace s: board)
				    	  {
				    		  System.out.println(s);
				    	  }
				    	 
				    	  }
	}
			     
