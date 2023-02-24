package Java;
import javax.swing.*;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//监听1 按钮1的作用
//new一个窗口 游戏华容道 游戏没有main函数 所以用new
public class MousePolice1 implements MouseListener {
    public void mouseReleased (MouseEvent e) {
        new Hua_Rong_Road();
    }
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mouseMoved (MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
}