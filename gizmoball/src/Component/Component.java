package Component;
/**
 * @Author LiXiang
 * @Date 2020/11/19 15:30
 * @Version 1.0
 */
public interface Component {
    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 放大控件
     * @param scaler 放大倍数
     */
    public void enlarge(Integer scaler);


    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 缩小控件
     * @param scaler 放大倍数
     */
    public void shrink(Integer scaler);

    /**
     * @Author LiXiang
     * @Date 2020/11/19 15:30
     * @Version 1.0
     * 旋转控件
     * @param angle 放大倍数
     */
    public void rotate(Integer angle);

}
