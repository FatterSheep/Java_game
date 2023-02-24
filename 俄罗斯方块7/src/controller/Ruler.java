package controller;
import model.Tetromino;
import view.Tetris;
import model.Cell;
import model.Tetromino;
import view.Tetris;
import model.Cell;
public class Ruler extends Tetris{
      public static boolean isBottom() {
		if (tetromino == null) return false;
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			int col = c.getCol();
			int row = c.getRow();
			if ((row+1) == ROWS || wall[row+1][col] != null) {
				//�������߽�����·��Ѿ������Ѵ����������������
				for(int j = 0; j < cells.length; j++) {
					//����Ϸ������ǽ
					Cell cell = cells[j];
					int col1 = cell.getCol();
					int row1 = cell.getRow();
					wall[row1][col1] = cell;
				}
				removeLine();
				tetromino = nextone;
				nextone = Tetromino.ranShape();
				return true;
			}  
      }
		return false;
}
      public static boolean canRightMove() {
  		if (tetromino == null) return false;
  		Cell[] cells = tetromino.cells;
  		for (int i = 0; i < cells.length; i++) {
  			Cell c = cells[i];
  			int row = c.getRow();
  			int col = c.getCol();
  			if ((col+1) == COLS || wall[row][col+1] != null) return false;//����߽���ѱ�ռ
  		}
  		return true;
  	}
  	public static boolean canLeftMove() {
  		if (tetromino == null) return false;
  		Cell[] cells = tetromino.cells;
  		for (int i = 0; i < cells.length; i++) {
  			Cell c = cells[i];
  			int row = c.getRow();
  			int col = c.getCol();
  			if (col == 0 || wall[row][col-1] != null) return false;//����߽���ѱ�ռ
  		}
  		return true;
  	}
  	public static boolean isGameOver() {
  		for(int col=0;col<COLS;col++)
  		{
  			if(wall[0][col]!=null) return true;
  		}
  		return false;
  	}
	private static void removeLine() {
		boolean flag = true;
		int rowStart = 20;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (wall[row][col] == null) {//�����п���������
					flag = false;
					break;
				}
			}
			if (flag) {//����һ�з���
				for (int col = 0; col < COLS; col++) {
					wall[row][col] = null;
				} 
				rowStart = row;
				score += 10;
				for (int row1 = rowStart; row1 > 0; row1--) {//�������Ϸ���������һ��
					for (int col1 = 0; col1 < COLS; col1++) {
						wall[row1][col1] = wall[row1-1][col1];
					}
				}
			}else {
				flag = true;
			}
		}
	}
}