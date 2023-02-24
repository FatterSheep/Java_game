package Java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


//初始化
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int lenth; //蛇的长度
    int[] snakeX = new int[600];//蛇的坐标X
    int[] snakeY = new int[500];//蛇的坐标Y
    String fx;

    boolean isStart = false; //游戏是否开始

    Timer timer = new Timer(100, this);//定时器

    //定义一个食物
    int foodx;
    int foody;

    Random random = new Random();

    //炸弹
    //int boomx;
    //int boomy;

    //判定失败
    boolean isFail = false;

    //积分系统
    int score;

    //客户端
    //public static final String mip = "10.25.50.131";
    //public static boolean isWin = true;


    //public static void main(String[] args) throws IOException {
        //GamePanel gamePanel = new GamePanel();
        //Socket socket = new Socket(mip, MyServer.mdk);
        //OutputStream ou = socket.getOutputStream();
        //InputStream in = socket.getInputStream();
        //Scanner scanner = new Scanner(System.in);
    //}

    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);//焦点
        this.addKeyListener(this);//获取空格
        timer.start();
    }

    //初始化
    public void init() {
        lenth = 3;
        snakeX[0] = 100;
        snakeY[0] = 100; //头部坐标
        snakeX[1] = 75;
        snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;
        snakeY[2] = 100;//第二个身体
        fx = "R";

        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
        score = 0;

        //boomx = 25 + 25 * random.nextInt(34);
        //boomy = 75 + 25 * random.nextInt(24);
    }

    //画板: 画界面,画蛇
    //Graphics : 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏
        this.setBackground(Color.BLACK);//设置背景颜色
        //设置头部广告栏
        Data.header.paintIcon(this, g, 25, 75);
        //绘制游戏区域
        g.fillRect(25, 75, 850, 600);
        //画蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("T")) {
            Data.top.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        Data.body.paintIcon(this, g, snakeX[1], snakeY[1]);
        Data.body.paintIcon(this, g, snakeX[2], snakeY[2]);

        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);//通过lenth控制蛇长
        }

        //画食物
        Data.food.paintIcon(this, g, foodx, foody);

        //画炸弹
        //Data.boom.paintIcon(this, g, boomx, boomy);

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));//设置字体
        g.drawString("lenth" + lenth, 750, 35);
        g.drawString("Score" + score, 750, 15);
        //游戏提示:是否开始
        if (isStart == false) {
            //画一个文字,Sting
            g.setColor(Color.white);//设置画笔颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));//设置字体
            g.drawString("Push Space Start", 300, 300);
        }
        //失败提醒
        else if (isFail == true) {
            g.setColor(Color.RED);//设置画笔颜色
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));//设置字体
            g.drawString("Push Space ReStart", 200, 300);
        }

    }

    ///键盘按下,弹起
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //接收键盘的输入:监听
    //键盘按下,未释放
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();//获取按下的键盘键

        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {//失败,重来
                isFail = false;
                init();//重新初始化
            } else {
                isStart = !isStart;
            }
            repaint();//刷新界面
        }
        //键盘控制走向
        if (keyCode == KeyEvent.VK_W) {
            fx = "T";
        } else if (keyCode == KeyEvent.VK_S) {
            fx = "D";
        } else if (keyCode == KeyEvent.VK_A) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_D) {
            fx = "R";
        }
    }

    //定时器,监听时间,帧,执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态,并且没有结束
        if (isStart && isFail == false) {
            //右移
            for (int i = lenth - 1; i > 0; i--) { //除了脑袋,身体都向前移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过控制方向让头部移动
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25; //头右部移动
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }//边界判断
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;//头部左移动
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }//边界判断
            } else if (fx.equals("T")) {
                snakeY[0] = snakeY[0] - 25;//头部上移动
                if (snakeY[0] < 75) {
                    snakeY[0] = 650;
                }//边界判断
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;//头部下移动
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }//边界判断
            }

            //如果小蛇的头与食物坐标重合
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                //长度加1
                lenth++;

                //分数++
                score = score + 10;

                //重新生成food
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);

                //生成一个炸弹
                  //boomx = 25 + 25* random.nextInt(34);
                  //boomy = 75 + 25* random.nextInt(24);
            }

        //结束判断
        for (int i = 1; i < lenth; i++) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                isFail = true;
            }
        }
        //刷新界面
        repaint();
    }
                timer.start();//让时间动起来
}

    //释放某个键
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setDefaultCloseOperation(int exitOnClose) {
    }
}


