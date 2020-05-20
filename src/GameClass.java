



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameClass extends JFrame {
	
	boolean zafer = false;
	
	Date baszaman = new Date();
	
	Date kazzaman;
	
	int sure;
	int sayiy;
	int yasio;
	int yasib;
	
	int xYeri = -100;
	int yYeri = -100;
	
	int BayrakX = 500;
	int BayrakY = 25;
	
	int ReYenX = 365;
	int ReYenY = 15;
	
	int saatX = 600;
	int saatY = 25;
	
	int vicX = -250;
	int vicY = 25;
	int vicX2 = -250;
	
	int[][] mayin = new int[16][12];
	int[][] yakin = new int[16][12];
	boolean[][] etkin = new boolean[16][12];
	boolean[][] bayrak = new boolean[16][12];
	
	Random rand = new Random();
	
	boolean isretleyici = false;
	
	boolean gulucuk = true;
	
	public GameClass() {
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				mayin[i][j] = 0;
				yakin[i][j] = 0;
				etkin[i][j] = false;
				bayrak[i][j] = false;
				if (rand.nextInt(100) < 20 && j > 1) {
					mayin[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				for (int m = 0; m < 16; m++) {
					for (int n = 0; n < 12; n++) {
						if (Math.abs(i-m) <= 1 && Math.abs(j-n) <= 1 && (i != m || j != n) && mayin[m][n] == 1) {
							yakin[i][j]++;
						}
					}
				}
				System.out.println(yakin[i][j]);
			}
		}
		
		Board board = new Board();
		Move move = new Move();
		Click click = new Click();
		
		this.setSize(806,630);
		this.setTitle("Mayýn Tarlasý");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setContentPane(board);
		
		board.addMouseMotionListener(move);
		board.addMouseListener(click);
	}
	
	public class Board extends JPanel {
		
		public void paintComponent (Graphics g) {
			g.fillRect(0, 0, 800, 600);
			for (int i = 0; i < 16; i++) {
				for (int j = 2; j < 12; j++) {
					g.setColor(Color.yellow);
					if (etkin[i][j] == true) {
						g.setColor(Color.WHITE);
						if (yakin[i][j] > 0) {
							g.setColor(Color.pink);
						}
						if (mayin[i][j] == 1) {
							g.setColor(Color.RED);
						}
						
					}
					
					
					g.fillRect(2 + 50 * i, 2 + 50 * j, 46, 46);
					
					if (bayrak[i][j] == true) {
						g.setColor(Color.BLACK);
						g.fillRect(i * 50 + 24, j * 50 + 10, 3, 30);
						g.fillRect(i * 50 + 20, j * 50 + 35, 11, 5);
						g.fillRect(i * 50 + 16, j * 50 + 37, 19, 3);
						g.setColor(Color.RED);
						g.fillRect(i * 50 + 15, j * 50 + 10, 9, 9);
					}
					
					if (yakin[i][j] > 0 && mayin[i][j] != 1 && etkin[i][j] == true) {
						int k = yakin[i][j];
						if (k == 1) {
							g.setColor(Color.BLUE);
							g.fillRect(i * 50 + 23, j * 50 + 10, 4, 30);
							g.fillRect(i * 50 + 19, j * 50 + 36, 12, 4);
							g.fillRect(i * 50 + 19, j * 50 + 10, 8, 4);
						} else if (k == 2) {
							g.setColor(new Color(0,180,0));
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 23, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 36, 20, 4);
							g.fillRect(i * 50 + 31, j * 50 + 10, 4, 17);
							g.fillRect(i * 50 + 15, j * 50 + 23, 4, 17);
						} else if (k == 3) {
							g.setColor(Color.RED);
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 23, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 36, 20, 4);
							g.fillRect(i * 50 + 31, j * 50 + 10, 4, 30);
						} else if (k == 4) {
							g.setColor(new Color(15,15,112));
							g.fillRect(i * 50 + 30, j * 50 + 10, 4, 30);
							g.fillRect(i * 50 + 16, j * 50 + 10, 4, 14);
							g.fillRect(i * 50 + 16, j * 50 + 20, 20, 4);
						} else if (k == 5) {
							g.setColor(new Color(165,42,42));
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 23, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 36, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 10, 4, 17);
							g.fillRect(i * 50 + 31, j * 50 + 23, 4, 17);
						} else if (k == 6) {
							g.setColor(new Color(32,178,170));
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 23, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 36, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 10, 4, 17);
							g.fillRect(i * 50 + 31, j * 50 + 23, 4, 17);
							g.fillRect(i * 50 + 15, j * 50 + 23, 4, 17);
						} else if (k == 7) {
							g.setColor(Color.MAGENTA);
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 10, 4, 5);
							g.fillRect(i * 50 + 31, j * 50 + 10, 4, 8);
							g.fillRect(i * 50 + 29, j * 50 + 18, 4, 4);
							g.fillRect(i * 50 + 27, j * 50 + 22, 4, 4);
							g.fillRect(i * 50 + 25, j * 50 + 26, 4, 4);
							g.fillRect(i * 50 + 23, j * 50 + 30, 4, 4);
							g.fillRect(i * 50 + 21, j * 50 + 34, 4, 4);
						} else if (k == 8) {
							g.setColor(new Color(139,139,131));
							g.fillRect(i * 50 + 15, j * 50 + 10, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 23, 20, 4);
							g.fillRect(i * 50 + 15, j * 50 + 36, 20, 4);
							g.fillRect(i * 50 + 31, j * 50 + 10, 4, 30);
							g.fillRect(i * 50 + 15, j * 50 + 10, 4, 30);
						}
					}
				}
			}
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(BayrakX, BayrakY, 50, 50);
			if (isretleyici == true) {
				g.setColor(Color.BLACK);
				g.fillRect(BayrakX + 24, BayrakY + 10, 3, 30);
				g.fillRect(BayrakX + 20, BayrakY + 35, 11, 5);
				g.fillRect(BayrakX + 16, BayrakY + 37, 19, 3);
				g.setColor(Color.RED);
				g.fillRect(BayrakX + 15, BayrakY + 10, 9, 9);
				g.fillRect(BayrakX, BayrakY, 50, 4);
				g.fillRect(BayrakX, BayrakY + 46, 50, 4);
				g.fillRect(BayrakX, BayrakY, 4, 50);
				g.fillRect(BayrakX + 46, BayrakY, 4, 50);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(BayrakX + 24, BayrakY + 10, 3, 30);
				g.fillRect(BayrakX + 20, BayrakY + 35, 11, 5);
				g.fillRect(BayrakX + 16, BayrakY + 37, 19, 3);
				g.setColor(Color.RED);
				g.fillRect(BayrakX + 15, BayrakY + 10, 9, 9);
			}
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(ReYenX, ReYenY, 70, 70);
			g.setColor(Color.YELLOW);
			g.fillOval(ReYenX + 10, ReYenY + 10, 50, 50);
			g.setColor(Color.BLACK);
			g.fillOval(ReYenX + 20, ReYenY + 25, 10, 10);
			g.fillOval(ReYenX + 40, ReYenY + 25, 10, 10);
			if (gulucuk == true) {
				g.fillRect(ReYenX + 26, ReYenY + 46, 19, 4);
				g.fillRect(ReYenX + 23, ReYenY + 42, 5, 5);
				g.fillRect(ReYenX + 43, ReYenY + 42, 5, 5);
			} else {
				g.fillRect(ReYenX + 26, ReYenY + 44, 19, 4);
				g.fillRect(ReYenX + 23, ReYenY + 46, 5, 5);
				g.fillRect(ReYenX + 43, ReYenY + 46, 5, 5);
			}
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(saatX, saatY, 200, 50);
			sure = (int) (new Date().getTime() - baszaman.getTime()) / 1000;
			if (gulucuk == true && zafer == false) {
				yasib = (int) sure % 10;
				yasio = (int) (sure % 100 - yasib) / 10;
				sayiy = (int) (sure % 1000 - sure % 100) / 100;
			}
			g.setColor(Color.BLACK);
			if (gulucuk == false) {
				g.setColor(Color.RED);
			}
			if (zafer == true) {
				g.setColor(Color.GREEN);
			}

			if (sayiy == 0) {
				g.fillRect(saatX + 15, saatY + 10, 4, 30);
				g.fillRect(saatX + 31, saatY + 10, 4, 30);
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
			}
			if (sayiy == 1) {
				g.fillRect(saatX + 23, saatY + 10, 4, 30);
				g.fillRect(saatX + 19, saatY + 36, 12, 4);
				g.fillRect(saatX + 19, saatY + 10, 8, 4);
			} else if (sayiy == 2) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 31, saatY + 10, 4, 17);
				g.fillRect(saatX + 15, saatY + 23, 4, 17);
			} else if (sayiy == 3) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 31, saatY + 10, 4, 30);
			} else if (sayiy == 4) {
				g.fillRect(saatX + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 16, saatY + 10, 4, 14);
				g.fillRect(saatX + 16, saatY + 20, 20, 4);
			} else if (sayiy == 5) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 15, saatY + 10, 4, 17);
				g.fillRect(saatX + 31, saatY + 23, 4, 17);
			} else if (sayiy == 6) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 15, saatY + 10, 4, 17);
				g.fillRect(saatX + 31, saatY + 23, 4, 17);
				g.fillRect(saatX + 15, saatY + 23, 4, 17);
			} else if (sayiy == 7) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 10, 4, 5);
				g.fillRect(saatX + 31, saatY + 10, 4, 8);
				g.fillRect(saatX + 29, saatY + 18, 4, 4);
				g.fillRect(saatX + 27, saatY + 22, 4, 4);
				g.fillRect(saatX + 25, saatY + 26, 4, 4);
				g.fillRect(saatX + 23, saatY + 30, 4, 4);
				g.fillRect(saatX + 21, saatY + 34, 4, 4);
			} else if (sayiy == 8) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 31, saatY + 10, 4, 30);
				g.fillRect(saatX + 15, saatY + 10, 4, 30);
			} else if (sayiy == 9) {
				g.fillRect(saatX + 15, saatY + 10, 20, 4);
				g.fillRect(saatX + 15, saatY + 23, 20, 4);
				g.fillRect(saatX + 15, saatY + 36, 20, 4);
				g.fillRect(saatX + 31, saatY + 10, 4, 30);
				g.fillRect(saatX + 15, saatY + 10, 4, 17);
			}
			
			if (yasio == 0) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
			}
			if (yasio == 1) {
				g.fillRect(saatX + 23 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 19 + 30, saatY + 36, 12, 4);
				g.fillRect(saatX + 19 + 30, saatY + 10, 8, 4);
			} else if (yasio == 2) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 17);
				g.fillRect(saatX + 15 + 30, saatY + 23, 4, 17);
			} else if (yasio == 3) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 30);
			} else if (yasio == 4) {
				g.fillRect(saatX + 30 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 16 + 30, saatY + 10, 4, 14);
				g.fillRect(saatX + 16 + 30, saatY + 20, 20, 4);
			} else if (yasio == 5) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 17);
				g.fillRect(saatX + 31 + 30, saatY + 23, 4, 17);
			} else if (yasio == 6) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 17);
				g.fillRect(saatX + 31 + 30, saatY + 23, 4, 17);
				g.fillRect(saatX + 15 + 30, saatY + 23, 4, 17);
			} else if (yasio == 7) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 5);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 8);
				g.fillRect(saatX + 29 + 30, saatY + 18, 4, 4);
				g.fillRect(saatX + 27 + 30, saatY + 22, 4, 4);
				g.fillRect(saatX + 25 + 30, saatY + 26, 4, 4);
				g.fillRect(saatX + 23 + 30, saatY + 30, 4, 4);
				g.fillRect(saatX + 21 + 30, saatY + 34, 4, 4);
			} else if (yasio == 8) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 30);
			} else if (yasio == 9) {
				g.fillRect(saatX + 15 + 30, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 30, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 30, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 30, saatY + 10, 4, 17);
			}
			if (yasib == 0) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
			}
			if (yasib == 1) {
				g.fillRect(saatX + 23 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 19 + 60, saatY + 36, 12, 4);
				g.fillRect(saatX + 19 + 60, saatY + 10, 8, 4);
			} else if (yasib == 2) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 17);
				g.fillRect(saatX + 15 + 60, saatY + 23, 4, 17);
			} else if (yasib == 3) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 30);
			} else if (yasib == 4) {
				g.fillRect(saatX + 30 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 16 + 60, saatY + 10, 4, 14);
				g.fillRect(saatX + 16 + 60, saatY + 20, 20, 4);
			} else if (yasib == 5) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 17);
				g.fillRect(saatX + 31 + 60, saatY + 23, 4, 17);
			} else if (yasib == 6) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 17);
				g.fillRect(saatX + 31 + 60, saatY + 23, 4, 17);
				g.fillRect(saatX + 15 + 60, saatY + 23, 4, 17);
			} else if (yasib == 7) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 5);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 8);
				g.fillRect(saatX + 29 + 60, saatY + 18, 4, 4);
				g.fillRect(saatX + 27 + 60, saatY + 22, 4, 4);
				g.fillRect(saatX + 25 + 60, saatY + 26, 4, 4);
				g.fillRect(saatX + 23 + 60, saatY + 30, 4, 4);
				g.fillRect(saatX + 21 + 60, saatY + 34, 4, 4);
			} else if (yasib == 8) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 30);
			} else if (yasib == 9) {
				g.fillRect(saatX + 15 + 60, saatY + 10, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 23, 20, 4);
				g.fillRect(saatX + 15 + 60, saatY + 36, 20, 4);
				g.fillRect(saatX + 31 + 60, saatY + 10, 4, 30);
				g.fillRect(saatX + 15 + 60, saatY + 10, 4, 17);
			}
			
			
			if (zafer == true) {
				int L = (int) (-kazzaman.getTime() + new Date().getTime()) / 10;
				if (L > 300) {
					L = 300;
				}
				vicX = L - 250;
				vicX2 = vicX + 150;
				g.setColor(Color.GREEN);
				for (int m = 0; m < 11; m++) {
					g.fillRect(vicX+m+10, vicY+m+10, 7, 5);
					g.fillRect(vicX+33-m, vicY+m+10, 7, 5);
				}
				g.fillRect(vicX+21, vicY+23, 8, 17);
				
				
				g.fillRect(vicX+50, vicY+10, 30, 5);
				g.fillRect(vicX+50, vicY+35, 30, 5);
				g.fillRect(vicX+50, vicY+10, 5, 30);
				g.fillRect(vicX+75, vicY+10, 5, 30);

				g.fillRect(vicX+90, vicY+35, 30, 5);
				g.fillRect(vicX+90, vicY+10, 5, 30);
				g.fillRect(vicX+115, vicY+10, 5, 30);

				for (int m = 0; m < 10; m++) {
					g.fillRect(vicX2+6+m, vicY+10+m*3, 4, 4);
					g.fillRect(vicX2+40-m, vicY+10+m*3, 4, 4);
					if (m < 5) {
						g.fillRect(vicX2+22-m, vicY+22+m*3, 4, 4);
						g.fillRect(vicX2+24+m, vicY+22+m*3, 4, 4);
					}
				}

				g.fillRect(vicX2+60, vicY+10, 5, 30);

				for (int m = 0; m < 27; m++) {
					g.fillRect(vicX2+80+m, vicY+10+m, 4, 4);
					g.fillRect(vicX2+80, vicY+10, 5, 30);
					g.fillRect(vicX2+105, vicY+10, 5, 30);
				}
			}
			
		}
		
	}
	
	public boolean isInBox(int xC, int yC, int cols, int rows) {
		
		if (xC >= cols * 50 + 2 && xC < (cols + 1) * 50 - 2 && yC >= rows * 50 + 2 && yC < (rows + 1) * 50 - 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isIn(int xC, int yC) {
		for (int x = 0; x < 16; x++) {
			for (int y = 2; y < 12; y++) {
				if (xC >= x * 50 + 2 && xC < (x + 1) * 50 - 2 && yC >= y * 50 + 2 && yC < (y + 1) * 50 - 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int getXBox(int xC, int yC) {
		for (int x = 0; x < 16; x++) {
			for (int y = 2; y < 12; y++) {
				if (xC >= x * 50 + 2 && xC < (x + 1) * 50 - 2 && yC >= y * 50 + 2 && yC < (y + 1) * 50 - 2) {
					return x;
				}
			}
		}
		return 0;
	}
	
	public int getYBox(int xC, int yC) {
		for (int x = 0; x < 16; x++) {
			for (int y = 2; y < 12; y++) {
				if (xC >= x * 50 + 2 && xC < (x + 1) * 50 - 2 && yC >= y * 50 + 2 && yC < (y + 1) * 50 - 2) {
					return y;
				}
			}
		}
		return 0;
	}
	
	public void unhappy() {
		for (int x = 0; x < 16; x++) {
			for (int y = 2; y < 12; y++) {
				if (mayin[x][y] == 1) {
					etkin[x][y] = true;
				}
			}
		}
		gulucuk = false;
	}
	
	public void openClear(int a, int b) {
		boolean f = true;
		boolean h = true;
		ArrayList<int[]> C = new ArrayList<int[]>();
		int p[] = new int[2];
		for (int m = 0; m < 16; m++) {
			for (int n = 0; n < 12; n++) {
				if (Math.abs(m-a) <= 1 && Math.abs(n-b) <= 1 && (m != a || n != b)) {
					etkin[m][n] = true;
					bayrak[m][n] = false;
					if (yakin[m][n] == 0) {
						p[0] = m;
						p[1] = n;
						C.add(p);
						p[0] = 0;
						p[1] = 0;
						f = false;
					}
				}
			}
		}
		
	}
	
	public void reset() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				mayin[i][j] = 0;
				yakin[i][j] = 0;
				etkin[i][j] = false;
				bayrak[i][j] = false;
				if (rand.nextInt(10) < 2 && j > 1) {
					mayin[i][j] = 1;
				}
			}
			baszaman = new Date();
		}
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 12; j++) {
				for (int m = 0; m < 16; m++) {
					for (int n = 0; n < 12; n++) {
						if (Math.abs(i-m) <= 1 && Math.abs(j-n) <= 1 && (i != m || j != n) && mayin[m][n] == 1) {
							yakin[i][j]++;
						}
					}
				}
				System.out.println(yakin[i][j]);
			}
		}
		isretleyici = false;
		gulucuk = true;
		zafer = false;
	}
	
	public class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			xYeri = e.getX();
			yYeri = e.getY();
			if (isIn(xYeri, yYeri) == true) {
				if (isretleyici == false) {
					if (bayrak[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] == false) {
						etkin[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] = true;
						bayrak[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] = false;
						if (mayin[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] == 1) {
							gulucuk = false;
							unhappy();
						} else if (yakin[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] == 0) {
							openClear(getXBox(xYeri, yYeri),getYBox(xYeri, yYeri));
						}
					}
				} else {
					if (etkin[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] == false) {
						if(bayrak[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] == false) {
							bayrak[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] = true;
						} else {
							bayrak[getXBox(xYeri, yYeri)][getYBox(xYeri, yYeri)] = false;
						}
					}
				}
			}
			if (xYeri >= BayrakX && xYeri < BayrakX + 50 && yYeri >= BayrakY && yYeri < BayrakY + 50) {
				if (isretleyici == false) {
					isretleyici = true;
				} else {
					isretleyici = false;
				}
			}
			if (xYeri >= ReYenX && xYeri < ReYenX + 70 && yYeri >= ReYenY && yYeri < ReYenY + 70) {
				reset();
			}
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			xYeri = e.getX();
			yYeri = e.getY();
			repaint();
		}
		
	}
	
	public class Move implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			xYeri = e.getX();
			yYeri = e.getY();
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			xYeri = e.getX();
			yYeri = e.getY();
			repaint();
		}
		
	}

	public void doIt() {
		repaint();
	}

	public void winCheck() {
		int mines = 0;
		int reveals = 0;
		for (int a = 0; a < 16; a++) {
			for (int b = 2; b < 12; b++) {
				if (mayin[a][b] == 1) {
					mines++;
				}
				if (etkin[a][b] == true) {
					reveals++;
				}
			}
		}
		if (160 - mines - reveals == 0 && gulucuk == true && zafer == false) {
			kazzaman = new Date();
			zafer = true;
		}
	}
	
}
