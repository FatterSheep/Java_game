package model;
import java.util.Random;
public class Tetromino extends Cell{
    public Cell[] cells = new Cell[4];
	public void moveDown() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveDown();
		}
	}
	public void moveLeft() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveLeft();
		}
	}
	public void moveRight() {
		for(int i = 0; i < cells.length; i++) {
			cells[i].moveRight();
		}
	}
	public Cell[] spin()
	{
		if(this.getClass().equals(new O().getClass())) return null; //若为O型则返回null
		Cell []icells=new Cell[4];
		int iRow=this.cells[2].getRow();
		int iCol=this.cells[2].getCol();
		for(int i=0;i<this.cells.length;i++)
		{
			int nRow=this.cells[i].getRow();
			int nCol=this.cells[i].getCol();
			icells[i]=new Cell(iRow-iCol+nCol,iRow+iCol-nRow,this.cells[i].getImage());//旋转后的坐标
		}
		return icells;
	}
	public static Tetromino ranShape() {//随机生成组合方块
		Random random = new Random();
		int index = random.nextInt(7);
		switch (index) {
		case 0:return new J();
		case 1:return new L();
		case 2:return new O();
		case 3:return new Z();
		case 4:return new S();
		case 5:return new I();
		case 6:return new T();
		}
		return null;
		
	}
}
//七种组合方块
class J extends Tetromino {
	public J() {
		cells[0] = new Cell(2, 5,view.Tetris.J);
		cells[1] = new Cell(0, 6,view.Tetris.J);
		cells[2] = new Cell(2, 6,view.Tetris.J);
		cells[3] = new Cell(1,6,view.Tetris.J);
	}
}
class L extends Tetromino {
	
	public L() {
		cells[0] = new Cell(2, 6, view.Tetris.L);
		cells[1] = new Cell(0, 5, view.Tetris.L);
		cells[2] = new Cell(2, 5, view.Tetris.L);
		cells[3] = new Cell(1, 5, view.Tetris.L);
	}
}
class O extends Tetromino {
	public O() {
		cells[0] = new Cell(0, 5, view.Tetris.O);
		cells[1] = new Cell(0, 6, view.Tetris.O);
		cells[2] = new Cell(1, 5, view.Tetris.O);
		cells[3] = new Cell(1, 6, view.Tetris.O);
	}
}
class Z extends Tetromino {
	public Z() {
		cells[0] = new Cell(0, 4, view.Tetris.Z);
		cells[1] = new Cell(0, 5, view.Tetris.Z);
		cells[2] = new Cell(1, 5, view.Tetris.Z);
		cells[3] = new Cell(1, 6, view.Tetris.Z);
	}
}
class S extends Tetromino {
	public S() {
		cells[0] = new Cell(1, 4, view.Tetris.S);
		cells[1] = new Cell(1, 5, view.Tetris.S);
		cells[2] = new Cell(0, 5, view.Tetris.S);
		cells[3] = new Cell(0, 6, view.Tetris.S);
	}
}
class I extends Tetromino {
	public I() {
		cells[0] = new Cell(0, 5, view.Tetris.I);
		cells[1] = new Cell(1, 5, view.Tetris.I);
		cells[2] = new Cell(2, 5, view.Tetris.I);
		cells[3] = new Cell(3, 5, view.Tetris.I);
	}
}
class T extends Tetromino {
	public T() {
		cells[0] = new Cell(0, 4, view.Tetris.T);
		cells[1] = new Cell(0, 6, view.Tetris.T);
		cells[2] = new Cell(0, 5, view.Tetris.T);
		cells[3] = new Cell(1, 5, view.Tetris.T);
	}
}