package Component;

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


    /**
     * @param scaler 放大倍数
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 放大控件
     */
    public void enlarge(Integer scaler) {

    }


    /**
     * @param scaler 放大倍数
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 缩小控件
     */
    public void shrink(Integer scaler) {

    }

    /**
     * @param angle 放大倍数
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 旋转控件
     */
    public void rotate(Integer angle) {

    }
    public void judge()

}
