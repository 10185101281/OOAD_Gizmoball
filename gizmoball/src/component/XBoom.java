package component;

import javax.swing.*;
import java.awt.*;
import main.Board;

public class XBoom extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/boom.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    private boolean isExploding;
    private Integer explodeCount;
    public XBoom(Integer x,Integer y,Board board){
        super(x,y,board);
        isExploding = false;
        explodeCount = 50;
    }
    public void explodeStart(){
        isExploding = true;
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/27 21:00
     * @Version 1.0
     * @param g
     */
    @Override
    public void paint(Graphics g){
        if(isExploding){
            if(explodeCount == 0){
                this.delete();
                return ;
            }
            try {
                Image image;
                image = Toolkit.getDefaultToolkit().getImage("gizmoball/src/picture/component/explode.png");
                g.drawImage(image, x, y,length,length,board);
                if(isSelected){
                    Graphics2D g2 = (Graphics2D)g;
                    g2.setColor(Color.RED);
                    //BasicStroke s = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRect(x,y,length,length);
                }
            } catch (Exception e) {
                System.out.println("File Not Found");
            }
            explodeCount--;
        } else {
            try {
                Image image;
                image = Toolkit.getDefaultToolkit().getImage(picturePath);
                Integer tlen = (int)(400.0/(400-90) * length);
                Integer tx = x - (int)(55.0/400 * length);
                Integer ty = y - (int)(115.0/400 * length);
                g.drawImage(image, tx, ty,tlen,tlen,board);
                if(isSelected){
                    Graphics2D g2 = (Graphics2D)g;
                    g2.setColor(Color.RED);
                    //BasicStroke s = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{5.0f}, 0.0f);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRect(x,y,length,length);
                }
            } catch (Exception e) {
                System.out.println("File Not Found");
            }
        }
    }

    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        //控件圆心坐标
        double center_x1 = x.doubleValue() + length.doubleValue()/2;
        double center_y1 = y.doubleValue() + length.doubleValue()/2;
        //小球圆心坐标
        double center_x2 = ball.getX().doubleValue();
        double center_y2 = ball.getY().doubleValue();
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(is_collision(ball)==1) {
            Double new_x2 = center_x1 + (length / 2 + ball.getRadius()) / distance * (center_x2 - center_x1);
            Double new_y2 = center_y1 + (length / 2 + ball.getRadius()) / distance * (center_y2 - center_y1);
            ball.setX(new_x2.intValue());
            ball.setY(new_y2.intValue());
            ball.setVx(-ball.getVx());
            ball.setVy(-ball.getVy());
            explodeStart();

        }

    }

    @Override
    public Integer is_collision(BouncingBall ball) {
        //控件圆心坐标
        Integer center_x1 = x + length/2;
        Integer center_y1 = y + length/2;
        //小球圆心坐标
        Integer center_x2 = ball.getX();
        Integer center_y2 = ball.getY();

        Integer radius = ball.getRadius();
        //圆心间距离
        double distance = Math.sqrt(Math.pow(center_x1-center_x2,2)+Math.pow(center_y1-center_y2,2));
        if(distance<length/2+radius){
           return 1;
        }
        return 0;
    }

}
