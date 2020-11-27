package component;

import javax.swing.*;
import java.awt.*;
import main.Board;

public class XTriangle extends XComponent {
    public static final String picturePath = "gizmoball/src/picture/component/triangle.png";
    public static final ImageIcon picture = new ImageIcon(picturePath);
    /**
     * @variable rotPosition
     * 记录三角形旋转到的位置，0~3对应直角顶点在左下，左上，右上，右下
     */
    private Integer rotPosition=0;
    /**
     * @variable boundEdge
     * 0~2对应斜边，水平边，垂直边
     */
    private Integer boundEdge=0;
    public XTriangle(Integer x, Integer y,Board board){
        super(x,y,board);
        paintPicturePath = picturePath;
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/24 21:00
     * @Version 1.0
     * @param g
     */
    @Override
    public void paint(Graphics g){
        /*
        g.setColor(Color.BLACK);
        g.fill3DRect(x,y,length,length,true);
        */
        try {
            Image image;
            image = Toolkit.getDefaultToolkit().getImage(picturePath);
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

    /**
     * @Author Kinghao
     * @Date 2020/11/26 18:00
     * @Version 1.0
     */
    public void rotate() {
        rotPosition=(rotPosition+1)%4;
    }

    /**
     * @Author Kinghao
     * @Date 2020/11/26 19:25
     * @Version 1.0
     */
    @Override
    public void judge(BouncingBall ball) {

    }

    @Override
    public void react(BouncingBall ball) {
        if(is_collision(ball)==1){
            switch (boundEdge){
                case 0:
                    if(rotPosition%2==0){
                        Integer vx=ball.getVx();
                        ball.setVx(-ball.getVy());
                        ball.setVy(-vx);
                    }else{
                        Integer vx=ball.getVx();
                        ball.setVx(ball.getVy());
                        ball.setVy(-vx);
                    }
                    break;
                case 1:
                    ball.setVx(-ball.getVx());
                    break;
                case 2:
                    ball.setVy(-ball.getVy());
                    break;
                default:
                    throw new RuntimeException("Invalid BoundEdge");

            }
        }
    }

    /**
     * @Author Kinghao
     * @Date 2020/11/26 18:00
     * @Version 1.0
     */
    public Integer getRotPosition() {
        return rotPosition;
    }

    /**
     * @Author Kinghao
     * @Date 2020/11/27 19:00
     * @Version 1.0
     */
    @Override
    public Integer is_collision(BouncingBall ball) {
        int ball_x=ball.getX(),ball_y=ball.getY(),radius=ball.getRadius();
        switch (rotPosition){
            //基本位置
            case 0:
                if(ball_x>=x-radius&&ball_x<=x//横向下一步碰左边
                        &&ball_y>=y&&ball_y<=y+length) {//纵向在范围内
                    boundEdge=1;
                    ball.setX(x-radius);//放置到碰上时的位置
                }
                else if(ball_x>=x&&ball_x<=x+radius
                        &&(y+length)-ball_y<=radius){
                    boundEdge=2;
                    ball.setY(y-length-radius);
                }
                else if(ball_y>x+y-ball_x//在斜边上方
                        &&Math.abs(ball_x+ball_y-x-y)/Math.sqrt(2)<=radius){//距离小于半径
                    boundEdge=0;
                    /**
                     * 用方程求解新的定位点，已知圆心应落在直线y-ball_y=x-ball_x上，设为(a,a-ball_x+ball_y)
                     * 则|a+a-ball_x+ball_y-x-y|/sqrt(2)=radius
                     * 根据在上方，开绝对值2*a=sqrt(2)*radius+x+y+ball_x-ball_y
                     */
                    double tmpX=(Math.sqrt(2)*radius+x+y+ball_x-ball_y)/2.0;
                    double tmpY=tmpX-ball_x+ball_y;
                    ball.setX((int)tmpX);
                    ball.setY((int)tmpY);
                }else{
                    return 0;
                }
                break;
            //顺时针旋转90度
            case 1:
                if(ball_x>=x-radius&&ball_x<=x
                        &&ball_y>=y&&ball_y<=y+length) {
                    boundEdge=1;
                    ball.setX(x-radius);
                }
                else if(ball_x>=x&&ball_x<=x+radius
                        &&ball_y-y<=radius){
                    boundEdge=2;
                    ball.setY(y+radius);
                }
                else if(ball_y<ball_x-x+(y-length)//在斜边下方
                        &&Math.abs(ball_x-ball_y-x+y-length)/Math.sqrt(2)<=radius){//距离小于半径
                    boundEdge=0;
                    /**
                     * 用方程求解新的定位点，已知圆心应落在直线y-ball_y=-x+ball_x上，设为(a,-a+ball_x+ball_y)
                     * 则|a+a-ball_x-ball_y-x+y-length|/sqrt(2)=radius
                     * 根据在下方，开绝对值2*a=ball_x+ball_y+x-y+length-sqrt(2)*radius
                     */
                    double tmpX=(ball_x+ball_y+x-y+length-Math.sqrt(2)*radius)/2.0;
                    double tmpY=-tmpX+ball_x+ball_y;
                    ball.setX((int)tmpX);
                    ball.setY((int)tmpY);
                }else{
                    return 0;
                }
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                throw new RuntimeException("Invalid Rotation");
        }
        return 1;
    }
}
