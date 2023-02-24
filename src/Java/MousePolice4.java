package Java;
import javax.swing.*;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//按钮4,利用main null函数打开另外一个main函数
public class MousePolice4 implements MouseListener {
    public void mouseReleased (MouseEvent e) {
        startTetris.main(null);
    }
    public void mouseEntered (MouseEvent e) {}
    public void mouseExited (MouseEvent e) {}
    public void mouseMoved (MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }
}