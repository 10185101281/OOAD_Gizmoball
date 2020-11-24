package component;

import main.BouncingBall;

/**
 * @Author LiXiang
 * @Date 2020/11/19 15:30
 * @Version 1.0
 */
public abstract class Component {

    Integer x;  //左上角坐标
    Integer y;
    Integer length;
    Integer angle;
    Integer base = 40;//一个格子40像素
    public Component(Integer x,Integer y){
        this.x = x;
        this.y = y;
        length = base;
        angle = 0;
    }

    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 放大控件
     */
    public void enlarge() {
        length = length+base;
    }


    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 缩小控件
     */
    public void shrink() {
        length = length-base;
    }

    /**
     * @param angle 放大倍数
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 旋转控件
     */
    public void rotate(Integer angle) {
        this.angle = angle;
    }

    /**
     * @param ball 小球
     * @Author LiXiang
     * @Date 2020/11/20 10:00
     * @Version 1.0
     * 判断小球是否与自己碰撞
     */
    public void judge(BouncingBall ball){

    }


    /**
     * @param ball 小球
     * @Author LiXiang
     * @Date 2020/11/20 10:00
     * @Version 1.0
     * 碰撞后作出反应，改变小球状态
     */
    public void react(BouncingBall ball){

    }

}
