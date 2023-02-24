package view;
import controller.Ruler;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Tetromino;
import view.Tetris;
import model.Cell;

public class Tetris extends JPanel{
	protected static Tetromino nextone;
	protected static Tetromino tetromino;
	protected static final int ROWS = 20;
	protected static final int COLS = 10;
	protected static int score = 0;
	private int level = 5;
	protected static Cell[][] wall = new Cell[ROWS][COLS];
	private boolean STATE = true;//程序状态
	public static final int CELL_SIZE = 26;//长度换算
	public static BufferedImage Z;
	public static BufferedImage S;
	public static BufferedImage J;
	public static BufferedImage L;
	public static BufferedImage O;
	public static BufferedImage I;
	public static BufferedImage T;
	public static BufferedImage bg;
	public static BufferedImage gameover;
	static {//获取静态资源
		try {
			Z = ImageIO.read(Tetris.class.getResource("Z.png"));
			S = ImageIO.read(Tetris.class.getResource("S.png"));
			J = ImageIO.read(Tetris.class.getResource("J.png"));
			T = ImageIO.read(Tetris.class.getResource("T.png"));
			O = ImageIO.read(Tetris.class.getResource("O.png"));
			I = ImageIO.read(Tetris.class.getResource("I.png"));
			L = ImageIO.read(Tetris.class.getResource("L.png"));
			bg= ImageIO.read(Tetris.class.getResource("bg.png"));
			gameover =ImageIO.read(Tetris.class.getResource("gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void startTetris() {//主界面
		JFrame frame = new JFrame();
		Tetris tetris = new Tetris();
		frame.add(tetris);
		frame.setSize(525, 600);
		frame.setLocationRelativeTo(null);//将窗口置于屏幕的中央
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//用户在此窗体上发起 "close" 时默认执行的操作
		frame.setVisible(true);
		tetris.action();
	}
	public void action() {
		tetromino = Tetromino.ranShape();//当前组合方块
		nextone = Tetromino.ranShape();//预览区下一个方块
		KeyListener kl = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				keyMoveAction(k);
				repaint();
			}
		};
		this.addKeyListener(kl);//添加键盘监听器
		this.setFocusable(true);//获取焦点
		this.requestFocus();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		
			int moveIndex = 0;
			int bgiIndex = 0;
			int speed = 5*level;
			@Override
			public void run() {
				if (STATE) {
					//speed = speed*level;
					if (moveIndex % speed == 0) {
						moveDownAction();
						moveIndex = 0;
					}
				}
				//testAction();
				//isBottom();
				moveIndex++;
				bgiIndex++;
				repaint();
			}
		};
		timer.schedule(task, 10, 20);
}
	public void testAction() {
		for (int i = 0; i < tetromino.cells.length; i++) {
			System.out.println(tetromino.cells[i].getRow()+" "+tetromino.cells[i].getCol());
		}
	}
	public void keyMoveAction(int k) {
		switch (k) {
			case KeyEvent.VK_RIGHT:moveRightAction();break;
			case KeyEvent.VK_LEFT:moveLeftAction();break;
			case KeyEvent.VK_DOWN:moveDownAction();break;
			case KeyEvent.VK_UP:spinCellAction();break;
			case KeyEvent.VK_I:moveInitAction();break;
			case KeyEvent.VK_P:STATE = false;break;
			case KeyEvent.VK_C:STATE = true;break; 
			case KeyEvent.VK_E:System.exit(0);break;
			
		}
	}
	private void moveInitAction() {//初始化
		STATE = false;
		wall = new Cell[ROWS][COLS];
		tetromino = Tetromino.ranShape();
		nextone = Tetromino.ranShape();
		score = 0;
	}
	
	private void moveDownAction() {
		if (tetromino == null) return;
		if (!Ruler.isBottom()) 
		tetromino.moveDown();
	}
	private void moveLeftAction() {
		if (Ruler.canLeftMove()&&!Ruler.isBottom()) 
		tetromino.moveLeft();
	}
	private void moveRightAction() {
		if (Ruler.canRightMove()&&!Ruler.isBottom()) 
		tetromino.moveRight();
	}
	private void spinCellAction() {      //旋转
		// TODO Auto-generated method stub
		Cell[] nCells=tetromino.spin();
		if(nCells==null) return;
		for(int i=0;i<nCells.length;i++)
		{
			int nRow=nCells[i].getRow();
			int nCol=nCells[i].getCol();
			if(nRow<0||nRow>= ROWS||nCol<0||nCol>=COLS||wall[nRow][nCol]!=null) return;
		}
		tetromino.cells=nCells;
	}
public void paint(Graphics g) {
	    g.drawImage(bg, 0, 0, null);//画背景
		g.translate(15, 15);//坐标系的原点
		paintWall(g);
		paintTetromino(g);
		paintNextone(g);
		paintTabs(g);//提示器
		paintGamePause(g);
		paintGameOver(g);
	}
public void paintGameOver(Graphics g) {
	if (isGameOver()) {
		tetromino = null;
		g.drawImage(gameover, -15, -15, null);
		Color color = new Color(0,71,157);
		g.setColor(color);
		Font font = new Font(Font.SERIF,Font.BOLD,30);
		g.setFont(font);
		g.drawString(""+score, 260, 207);
		//g.drawString(""+lines, 260, 253);
		g.drawString(""+level, 260, 300);
		STATE = false;
	}
}
private boolean isGameOver() {
	for(int col=0;col<COLS;col++)
	{
		if(wall[0][col]!=null) return true;
	}
	return false;
}
private void paintGamePause(Graphics g) {

}
private void paintTabs(Graphics g) {
	int x = 300;
	int y = 160;
	Color color = new Color(240,234,34);//颜色
	g.setColor(color);
	Font f = new Font(Font.SERIF,Font.BOLD,40);//字体
	g.setFont(f);
	g.drawString("score:   "+score, x, y);
	y+=56;
}
private void paintNextone(Graphics g) {
	if (nextone == null)return;
	 Cell[] cells = nextone.cells;
	 for(int i = 0; i < cells.length; i++){
		 Cell c = cells[i];
		 int row = c.getRow() ;
		 int col = c.getCol() + 9;
		 int x = col * CELL_SIZE;
		 int y = row * CELL_SIZE;
		 g.drawImage(c.getImage(), x, y, null);
	 }
}
private void paintTetromino(Graphics g) {
	if(tetromino == null)return;
	Cell[] cells = tetromino.cells;
	for(int i=0; i < cells.length; i++){
		Cell c = cells[i];
		int col = c.getCol();
		int row = c.getRow();
		int x = col * CELL_SIZE;
		int y = row * CELL_SIZE;
		g.drawImage(c.getImage(), x, y, null);
	}
}
private void paintWall(Graphics g) {
	for(int row = 0; row < ROWS; row++) {
		for(int col = 0; col < COLS; col++) {
			Cell cell = wall[row][col];
			int rows = row * CELL_SIZE;
			int cols = col * CELL_SIZE;
			if (cell == null) {
			}else{
				g.drawImage(cell.getImage(), cols, rows, null);
			}
		}
	}
}
}
