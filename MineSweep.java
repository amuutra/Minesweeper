package eraldiKollekshon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MineSweep extends JPanel implements ActionListener, MouseListener {

	private int boardWidth = 10;
	private int boardHeight = 10;
	private int mineCount = 10;

	private JFrame parentFrame;
	private JPanel sidePanel;
	
	private boolean clickable = true;
	
	private int[][] correctBoard;
	private int[][] currentBoard;
	
	private int mine = 1;
	private int oneMines = 10;
	private int twoMines = 11;
	private int threeMines = 12;
	private int fourMines = 13;
	private int fiveMines = 14;
	private int sixMines = 15;
	private int sevenMines = 16;
	private int eightMines = 17;
	
	private int squareHeight;
	private int squareWidth;
	
	private JLabel bonusInfo;

	JButton newSmallGame;
	JButton newMediumGame;
	JButton newLargeGame;
		
	public void setFrameSize() {
		int a = Toolkit.getDefaultToolkit().getScreenResolution();	
		this.setSize((boardWidth * (a/3)), (boardHeight * (a/3)));
		parentFrame.setSize(this.getWidth() + sidePanel.getWidth(), this.getHeight());
	}
	
	public MineSweep(JFrame raam) {
		parentFrame = raam;
		setUpBoard(mineCount);
		
		bonusInfo = new JLabel();
		
		addSidePanel();
		this.addMouseListener(this);
		
		bonusInfo.setText("Sweep mines!!");
		setFrameSize();
	}

	public void setUpBoard(int mineCount) {
		
		Random a = new Random();
		int randomNum;
		int randomNum2;
		correctBoard = new int[boardHeight][boardWidth];
		currentBoard = new int[boardHeight][boardWidth];
		
		//Add mines to the board
		for(int i=0; i<mineCount; i++) {
			
			randomNum = a.nextInt((boardHeight - 1) + 1);
			randomNum2 = a.nextInt((boardWidth - 1) + 1);
			
			if(correctBoard[randomNum][randomNum2] == 0) {
				correctBoard[randomNum][randomNum2] = mine;
			} else {
				i = i - 1;
			}	
		}
		
		//Adds squares around the mine with the correct information
		for(int i=0; i<boardHeight; i++) {
			
			for(int j=0; j<boardWidth; j++) {
				
				if (correctBoard[i][j] == 0) {
					
					if(!(j==boardWidth - 1) && correctBoard[i][j+1] == 1) {
						correctBoard[i][j] = 9;}			
					if(!(i==boardHeight - 1) &&  correctBoard[i + 1][j] == 1) {
						correctBoard[i][j] = 9;}
					if(!(i==0) && correctBoard[i - 1][j] == 1) {
						correctBoard[i][j] = 9;}				
					if(!(j==0) && correctBoard[i][j - 1] == 1) {
						correctBoard[i][j] = 9;}			
					if(!(i==boardHeight - 1) && !(j==boardWidth - 1) && correctBoard[i + 1][j + 1] == 1) {
						correctBoard[i][j] = 9;}			
					if(!(i==boardHeight - 1) && !(j==0) && correctBoard[i + 1][j - 1] == 1) {
						correctBoard[i][j] = 9;}		
					if(!(i==0) && !(j==boardWidth - 1) && correctBoard[i - 1][j + 1] == 1) {
						correctBoard[i][j] = 9;}		
					if(!(i==0) && !(j==0) && correctBoard[i - 1][j - 1] == 1) {
						correctBoard[i][j] = 9;}
				} 
			}		
		}
		
		for(int i1 = 0; i1<boardHeight; i1++) {
			
			for(int j1 = 0; j1<boardWidth; j1++) {
				
				if(correctBoard[i1][j1] == 9) {
					
					if(!(j1==boardWidth - 1) && correctBoard[i1][j1 + 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}	
					if(!(j1==0) && correctBoard[i1][j1 - 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}
					if(!(i1==boardHeight - 1) && correctBoard[i1 + 1][j1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}
					if(!(i1==0) && correctBoard[i1 - 1][j1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}		
					if(!(i1==boardHeight - 1) && !(j1==boardWidth - 1) && correctBoard[i1 + 1][j1 + 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}
					if(!(i1==0) && !(j1==0) && correctBoard[i1 - 1][j1 - 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}			
					if(!(i1==boardHeight - 1) && !(j1==0) && correctBoard[i1 + 1][j1 - 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}	
					if(!(i1==0) && !(j1==boardWidth - 1) && correctBoard[i1 - 1][j1 + 1] == 1) {
						correctBoard[i1][j1] = correctBoard[i1][j1] + 1;}
				}	
			}			
		}

		for(int i1 = 0; i1<boardHeight; i1++) {
			for(int j1 = 0; j1<boardWidth; j1++) {
				
				if (correctBoard[i1][j1] == 0) {
					correctBoard[i1][j1] = 20;
				}
			}
		}
				
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		Font a = new Font("TimesRoman", Font.BOLD, squareHeight); 
		g.setFont(a);
		
		for(int i = 0; i<boardHeight; i++) {
			for(int j = 0; j<boardWidth; j++) {
				
				if(currentBoard[i][j] == mine) {
					g.setColor(Color.BLACK);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
				}
				
				if(currentBoard[i][j] == 20) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
				}
				
				if(currentBoard[i][j] == oneMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("1", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == twoMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("2", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == threeMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("3", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == fourMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("4", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == fiveMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("5", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == sixMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("6", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == sevenMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("7", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == eightMines) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * squareWidth,  i * squareHeight, squareWidth, squareHeight);
					g.setColor(Color.BLUE);
					g.drawString("8", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}
				
				if(currentBoard[i][j] == 99) {
					g.setColor(Color.CYAN);
					g.drawString("F", j*squareWidth + squareWidth/3, i*squareHeight + squareHeight - squareHeight/5);
				}	
			}	
		}
		
		//Draws the board
		squareHeight = this.getHeight()/boardHeight;
		squareWidth = this.getWidth()/boardWidth;
		g.setColor(Color.BLACK);
		
		for(int i = 0; i<boardWidth + 1; i++) {
			g.drawLine( i * squareWidth, 0, i * squareWidth, squareHeight * boardHeight);			
		}
		
		for(int j = 0; j<boardHeight + 1; j++) {
			g.drawLine(0, j*squareHeight, squareWidth * boardWidth, j * squareHeight);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		
	int x = Toolkit.getDefaultToolkit().getScreenResolution();
		
	if(evt.getSource() == newSmallGame) {
		boardWidth = 10;
		boardHeight = 10;
		setUpBoard(15);	
		parentFrame.setSize(x * 5, x * 5);
	} else if (evt.getSource() == newMediumGame) {
		boardWidth = 25;
		boardHeight = 25;
		setUpBoard(75);
		parentFrame.setSize(x*6, x*6);
	} else if (evt.getSource() == newLargeGame) {
		boardWidth = 50;
		boardHeight = 50;
		parentFrame.setSize(x*7 + x/5, x*7 + x/5);
		setUpBoard(310);
	}	
		parentFrame.setLocationRelativeTo(null);
		clickable = true;
		bonusInfo.setText("Sweep mines!");
		this.repaint();
	}
	
	//------------------ADD SIDE PANEL------------------------//
		public void addSidePanel() {
			
			sidePanel = new JPanel();
			
			GridBagLayout layout = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			sidePanel.setLayout(layout);
			
			c.gridx = 1;
			c.gridy = 0;
			
			sidePanel.add(bonusInfo, c);
			
			c.gridx = 0;
			c.gridy = 1;
			
			newSmallGame = new JButton("New game (10X10)");
			sidePanel.add(newSmallGame, c);
			newSmallGame.addActionListener(this);
			
			c.gridx = 1;
			c.gridy = 1;
			
			newMediumGame = new JButton("New game (25X25)");
			sidePanel.add(newMediumGame, c);
			newMediumGame.addActionListener(this);
			
			c.gridx = 2;
			c.gridy = 1;
			newLargeGame = new JButton("New game (50X50)");
			sidePanel.add(newLargeGame, c);
			newLargeGame.addActionListener(this);
		}
	//----------------------------------------------------------//	
		public void gameOver() {
			clickable = false;
			
			if(checkVictory() == false) {
				bonusInfo.setText("You screwed up");
			} else {
				bonusInfo.setText("You win!");
			}
			
		}
		
		public void revealMines() {
			
			for(int i = 0; i<boardHeight; i++) {
				for(int j = 0; j<boardWidth; j++) {
					if (correctBoard[i][j] == mine) {
						currentBoard[i][j] = mine;
					}
				}		
			}
			clickable = false;
			bonusInfo.setText("You screwed up");
			this.repaint();
		}
		
		public void addEmpties(int i, int j) {

				int x = 100;
						
				while(x>0) {
					
					for(int ii = 0; ii < boardHeight; ii++) {
						for(int jj = 0; jj < boardWidth; jj++) {
							
							if (currentBoard[ii][jj] == 20) {
			
								if(ii < boardHeight - 1 && correctBoard[ii + 1][jj] == 20) {
									currentBoard[ii + 1][jj] = 20;
								} else if (ii < boardHeight - 1 && correctBoard[ii + 1][jj] >= 10 && correctBoard[ii + 1][jj] <= 17) {
									currentBoard[ii+1][jj] = correctBoard[ii+1][jj];
								}
								
								if(ii > 0 && correctBoard[ii - 1][jj] == 20) {
									currentBoard[ii - 1][jj] = 20;
								}  else if (ii > 0 && correctBoard[ii - 1][jj] >= 10 && correctBoard[ii - 1][jj] <= 17) {
									currentBoard[ii-1][jj] = correctBoard[ii-1][jj];
								}
								
								if(jj < boardWidth - 1 && correctBoard[ii][jj + 1] == 20) {
									currentBoard[ii][jj + 1] = 20;
								}  else if (jj < boardWidth - 1 && correctBoard[ii][jj + 1] >= 10 && correctBoard[ii][jj + 1] <= 17) {
									currentBoard[ii][jj + 1] = correctBoard[ii][jj + 1];
								}
								
								if(jj > 0 && correctBoard[ii][jj - 1] == 20) {
									currentBoard[ii][jj - 1] = 20;
								}  else if (jj > 0 && correctBoard[ii][jj - 1] >= 10 && correctBoard[ii][jj - 1] <= 17) {
									currentBoard[ii][jj - 1] = correctBoard[ii][jj - 1];
								}											
							}			
						}}		
					x = x - 1;			
				}					
		}
	
		@Override
		public void mouseClicked(MouseEvent evt) {

			int x = evt.getX()/squareWidth;
			int y = evt.getY()/squareHeight;
			
			if(clickable) {
				
				if(evt.getButton() == MouseEvent.BUTTON1) {
					
					if(correctBoard[y][x] == 20) {
						currentBoard[y][x] = 20;
						addEmpties(y, x);
						
						if(checkVictory() == true) {
							bonusInfo.setText("YOU WINNER YOU!!");
							gameOver();
						}
						
					} else if(correctBoard[y][x] == mine) {
						revealMines();
					} else {
						currentBoard[y][x] = correctBoard[y][x];
						
						if(checkVictory() == true) {
							bonusInfo.setText("YOU WIN");
							gameOver();
						}	
					}	
				}
								
				if(evt.getButton() == MouseEvent.BUTTON3) {
					currentBoard[y][x] = 99;
					
					if(checkVictory() == true) {
						bonusInfo.setText("YOU WIN");
						gameOver();
					}
				}
				this.repaint();	
			}	
		}

		public boolean checkVictory() {
			boolean winning = true;
							
				for(int i = 0; i<boardHeight; i++) {
					for(int j = 0; j<boardWidth; j++) {
						
						if (currentBoard[i][j] == correctBoard[i][j]) {
							
						} else if (currentBoard[i][j] == 99 && correctBoard[i][j] == mine) {
						} else {
							winning = false;
						}	
					}		
				}	
				return winning;			
		}
		
		public JPanel getSidePanel() {
			return sidePanel;
		}
		
		public static void main(String[] args) {
			
			JFrame raam = new JFrame("Minesweeper");
			MineSweep mine = new MineSweep(raam);
			
			raam.add(mine);
			raam.add(mine.getSidePanel(), BorderLayout.SOUTH);
			
			raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			raam.setSize(Toolkit.getDefaultToolkit().getScreenResolution() * 5, Toolkit.getDefaultToolkit().getScreenResolution() * 5);
			raam.setLocationRelativeTo(null);
			raam.setVisible(true);
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}	
}