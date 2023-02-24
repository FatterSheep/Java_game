import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class Canvas extends JPanel {
	
	public int size = 9;
	Image flag = null;
	Image bong = null;
	Font font;
	public Canvas(int size) {
		this.size = size;
		flag = new ImageIcon("flag.png").getImage();
		bong = new ImageIcon("bong.jpg").getImage();
		font = new Font("flag.jpg",Font.BOLD,30);
		initMap();
		
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//super.mouseClicked(e);
				int c = (e.getX()-pw/2)/pw;
				int r = (e.getY()-pw/2)/pw;
				System.out.println(e.getX()+", "+e.getY() +"["+r+","+c+"]");

				System.out.println(e.getButton());
				if(e.getButton() == MouseEvent.BUTTON3){
					System.out.println("button 3 ");
					
					if(mask[r][c] != 0) return;
					
					if(map[r][c] > 900){
						map[r][c] -= 1000;
					}else{
						map[r][c] += 1000;
					}
					repaint();
					return;
				}

				if(map[r][c] > 900){
					return;
				}
				
				if(map[r][c] == BONG || map[r][c] == BONG+1000){
					JOptionPane.showMessageDialog(Canvas.this, "Game Over");
					//initMap();
					openAll();
					repaint();
					return;
				}
				
				
				mask[r][c] = 1;
				spread(r,c);
				repaint();
				
				if(checkSucc()){
					JOptionPane.showMessageDialog(Canvas.this, "You Win");
					openAll();
					repaint();
					return;
				}
			}
		});
	}
	
	
	public boolean checkSucc(){

		

		int c = 0;
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				c += mask[i][j];
			}
		}
		
		return c  ==  size*size - 10;	
		
	}
	
	
	protected void openAll() {
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				mask[i][j] = 1;
			}
		}
	}

	//�ڲ���
	public static class MyPoint{
		public int r;
		public int c;
		public MyPoint(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	

	public void spread(int r,int c){

		HashSet<String> visited = new HashSet<String>();
		Queue<MyPoint> queue = new LinkedList<MyPoint>();
		//p.offer(e)
		//p.poll
		
		
		queue.offer(new MyPoint(r,c));
		while(!queue.isEmpty()){
			MyPoint p = queue.poll();

			visited.add(p.r+"_"+p.c);
			 
			if( map[p.r][p.c] == BONG || map[p.r][p.c] == BONG+1000){
				continue; 
			}
			
			mask[p.r][p.c] = 1;
			if(map[p.r][p.c] != 0){
				continue;
			}

			for(int i=0;i<cha.length;i++){
				int nr = p.r+cha[i][0];
				int nc = p.c+cha[i][1];
				if(nr < 0 || nr >= size || nc < 0 || nc >=size){
					continue;
				}
				if(visited.contains(nr+"_"+nc)){
					continue;
				}
				queue.offer(new MyPoint(nr,nc));				
			}
			
		}
		
		
		
		
	}
	
	static final int[][] cha ={
			{-1,-1},
			{-1,0},
			{-1,1},
			{0,-1},
			{0,1},
			{1,-1},
			{1,0},
			{1,1}
	};
	
	
	
	/**
	 * ��ʼ����ͼ
	 */
	public void initMap(){
		int c = 0;
		map = new int[size][size];
		while(c<10){
			int r = (int)(Math.random()*size); // [0,1) * 9 -> [0,9) --> 0-8
			int l = (int)(Math.random()*size); // [0,1) * 9 -> [0,9) --> 0-8
			if(map[r][l] == -2){
				continue;
			}
			
			map[r][l] = -2;
			c++;
		}
		//��������
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(map[i][j] == 0){
					map[i][j] = countOne(i, j);
				}
			}
		}
		
		
		mask = new int[size][size];
		
	}
	
	public int countOne(int r,int l){
		int  c= 0;
		
		
		for(int i=0;i<cha.length;i++){
			int nr = r+cha[i][0];
			int nc = l+cha[i][1];
			if(nr < 0 || nr >= size || nc < 0 || nc >=size){
				continue;
			}
			if(map[nr][nc] == BONG){
				c++;
			}
		}
		
		return c;
	}

	int[][] map = new int[size][size];
	int[][] mask = new int[size][size];
	
	
	public static final int UNKNOWN=0;
	public static final int BONG=-2;
	public static final int FLAG=-3;
	public static final int BLANK=-1;
	
	

	int pw;
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(font);
		

//		map[1][1] = 3;
//		map[2][2] = -1;
//		map[2][3] = -2;
//		map[2][4] = -3;
		
		int w = this.getWidth();
		int h = this.getHeight();
		
		w = w>h? h:w; //��Сֵ
		pw = w / (size+1);
		int gw = (int)(pw*0.9);
		for(int i=0;i<size;i++){
			for(int j = 0;j<size;j++){
				int nx = pw/2+j*pw;
				int ny = pw/2+i*pw;
				
				int mv = map[i][j];
				boolean hasFlag =mv> 900;
				if(mv > 900) mv -= 1000;
				
				
				if( mask[i][j] == 0){ // for DEBUG
					g.setColor(new Color(100,100,100));
					g.fillRect(nx,ny, gw, gw);
				}else if(mv == BLANK){
					g.setColor(Color.WHITE);
					g.fillRect(nx, ny, gw, gw);
					
				}else if(mv == BONG){
					g.drawImage(bong, nx, ny , gw, gw, null);
				}else if(mv == FLAG){
					g.drawImage(flag, nx, ny, gw, gw, null);
				}else{
					g.draw3DRect(nx, ny, gw, gw,false);
					if(mv == 0)continue;
					g.drawString(mv+"", (int)(nx+pw*0.3), (int)(ny+pw*0.7));
				}
				
				
				if(hasFlag){
					g.drawImage(flag,nx,ny,gw,gw,null);
				}
				
			}
		}
		
	}


	public void restart() {
		initMap();
		repaint();
	}
}
