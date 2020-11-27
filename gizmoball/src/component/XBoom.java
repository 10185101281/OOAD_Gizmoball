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
}
