package Java;
import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //头部图片 URL:定位图片 ImageIcon:图片
    public  static URL headerurl = Data.class.getResource("/statistics/top.png");
    public  static ImageIcon header = new ImageIcon(headerurl);

    //图片载入
    public  static  URL topUrl = Data.class.getResource("/statistics/top.png");
    public  static  URL downUrl = Data.class.getResource("/statistics/down.png");
    public  static  URL leftUrl = Data.class.getResource("/statistics/left.png");
    public  static  URL rightUrl = Data.class.getResource("/statistics/right.png");

    public  static ImageIcon top = new ImageIcon(topUrl);
    public  static ImageIcon down = new ImageIcon(downUrl);
    public  static ImageIcon left = new ImageIcon(leftUrl);
    public  static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public  static URL bodyUrl = Data.class.getResource("/statistics/body.png");
    public  static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public  static URL foodUrl = Data.class.getResource("/statistics/food.png");
    public  static ImageIcon food = new ImageIcon(foodUrl);
    //炸弹
    public  static URL boomUrl = Data.class.getResource("/statistics/boom.png");
    public  static ImageIcon boom = new ImageIcon(boomUrl);
}
