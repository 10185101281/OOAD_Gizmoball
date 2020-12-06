package component;

import main.Board;

import javax.swing.*;
import java.awt.*;

public class XStraightPipe extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/straightpipe.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);

    private String paintPicturePath1 = "gizmoball/src/picture/component/straightpipe-1.png";
    private String paintPicturePath2 = "gizmoball/src/picture/component/straightpipe-2.png";
    private String paintPicturePath3 = "gizmoball/src/picture/component/straightpipe-3.png";
    public XStraightPipe(Integer x,Integer y,Board board){
        super(x, y, board);
        paintPicturePath = picturePath;
    }


    /**
     * @Author Kinghao
     * @Date 2020/12/2 19:37
     * @Version 1.0
     */
    @Override
    public void react(BouncingBall ball) {
        super.react(ball);
        
        Integer ball_x = ball.getX();
        Integer ball_y = ball.getY();
        
        Integer radius = ball.getRadius();
        Integer status = is_collision(ball);
        if(status==1)//left
        {

            if(ball.getVy()>1){
                ball.setY(y+length/2);
                ball.setVx(3);
                ball.setVy(0);
            }

        }
        else if(status==2){//right

            if(ball.getVy()>1){
                ball.setY(y+length/2);
                ball.setVx(-3);
                ball.setVy(0);
            }
        }
        else if(status==3){//top
            ball.setY(-radius+y-1);
            ball.setVy(-ball.getVy());
        }
        else if(status==4){//bottom
            ball.setY(y+length+radius+1);
            ball.setVy(-ball.getVy());
        }else if(status==5){
            ball.setY(y+length/2);
            ball.setVy(0);
        }

    }

    /**
     * @Author Kinghao
     * @Date 2020/12/2 20:02
     * @Version 1.0
     */
    @Override
    public Integer is_collision(BouncingBall ball){
       
        Integer ball_x = ball.getX();
        Integer ball_y = ball.getY();
        Integer radius = ball.getRadius();
        if(ball_x>x-radius&&ball_x<x&&y<=ball_y-radius&&ball_y<=y+length-radius){//左边

            System.out.println("into pipe from left");
            return 1;
        }
        else if(ball_x>x+length&&ball_x<x+length+radius&&y<=ball_y-radius&&ball_y<=y+length-radius){//右边
            System.out.println("into pipe from right");
            return 2;
        }

        else if(ball_y<y&&ball_y>y-radius&&x<=ball_x&&ball_x<=x+length){
            return 3;
        }
        else if(ball_y>y+length&&ball_y<y+length+radius&&x<=ball_x&&ball_x<=x+length){
            return 4;
        }else if(ball_x>x&&ball_x<x+length&&ball_y>y&&ball_y<y+length){
            return 5;
        }

        return 0;
    }


    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     */
    public void rotate() {
        rotPosition=(rotPosition+1)%4;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/30 13:30
     * @Version 1.0
     */
    @Override
    public void paint(Graphics g){
        try {
            Image image;
            if(rotPosition == 0){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath);
            } else if(rotPosition == 1){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath1);
            } else if(rotPosition == 2){
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath2);
            } else {
                image = Toolkit.getDefaultToolkit().getImage(paintPicturePath3);
            }
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
    }
}
