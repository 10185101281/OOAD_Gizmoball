package component;

import main.Board;

import javax.swing.*;

public class XStraightPipe extends XComponent{
    public static final String picturePath = "gizmoball/src/picture/component/straightpipe.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
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

            if(ball.getVy()!=0){
                ball.setY(y+length/2);
                ball.setVx(2);
                ball.setVy(0);
            }

        }
        else if(status==2){//right

            if(ball.getVy()!=0){
                ball.setY(y+length/2);
                ball.setVx(-2);
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
        }

        return 0;
    }

}
