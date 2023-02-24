package model;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Cell {
   public Cell() {
	}
public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setCol(int col) {
		this.col = col;
	}
public Cell(int row, int col, BufferedImage image) {
		this.row = row;
		this.col = col;
		this.image = image;
	}
private int row;
   private int col;
   private BufferedImage image;
   public void moveDown() {
	   row++;
   }
   public void moveLeft() {
	   col--;
   }
   public void moveRight() {
	   col++;
  }
}
