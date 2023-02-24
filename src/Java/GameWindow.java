package Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//建一个窗口
    public class GameWindow extends JFrame {
        JFrame a = new JFrame("Ww And Yy GameMenu");//窗口名字
        JLayeredPane pane = new JLayeredPane();  // 分层网格
        JLabel label;
        JPanel panel1 = new JPanel();//添加标签
    //添加按钮
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();
        //鼠标监听
        MousePolice1 police1;
        MousePolice2 police2;
        MousePolice3 police3;
        MousePolice4 police4;

//窗口内容
        public GameWindow() {
            init();
            a.setBounds(50, 50, 850, 365);//窗口位置
            //按钮出现的位置及大小
            button1.setBounds(25, 25, 300, 100);
            button2.setBounds(500, 25, 300, 100);
            button3.setBounds(25, 200, 300, 100);
            button4.setBounds(500, 200, 300, 100);
            //添加按钮到窗口中
            a.add(button1);
            a.add(button2);
            a.add(button3);
            a.add(button4);
            //设置窗口的布局
            a.setLayout(null);
            a.setVisible(true);
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          //  getContentPane().setLayout(null);
          //  ImageIcon img = new ImageIcon("A.png");
//            button1.setOpaque(false);
           // panel1.setSize(img.getIconWidth(),img.getIconHeight());
           // panel1 = (JPanel)this.getContentPane();
           // panel1.add(label);
          //  pane.add(panel1,JLayeredPane.DEFAULT_LAYER);
          //  pane.add(label, JLayeredPane.MODAL_LAYER);
            // JPanel pan =(JPanel)a.getContentPane();
            //pan.setLayout(new FlowLayout());
          //  a.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));

        }
//各个按钮的监听以及按钮文字内容
    //监听见文件MousePolice
        void init() {
            setLayout(new FlowLayout());
            button1 = new JButton("Hua");
            police1 = new MousePolice1();
            button1.addMouseListener(police1);
            addMouseListener(police1);

            police2 = new MousePolice2();
            button2 = new JButton("Snake");
            button2.addMouseListener(police2);
            addMouseListener(police2);

            police3 = new MousePolice3();
            button3 = new JButton("Boom");
            button3.addMouseListener(police3);
            addMouseListener(police3);

            police4 = new MousePolice4();
            button4 = new JButton("Russia");
            button4.addMouseListener(police4);
            addMouseListener(police4);


        }
    }