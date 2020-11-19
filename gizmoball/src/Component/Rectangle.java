package Component;

public class Rectangle implements Component{
    Integer x;  //左上角坐标
    Integer y;
    Integer length;
    Integer angle;

    public void Rectangle(Integer x,Integer y){
        this.x = x;
        this.y = y;
        length = 20;
        angle = 0;
    }
    @Override
    public void enlarge(Integer scaler) {
        length = length*scaler;
    }

    @Override
    public void shrink(Integer scaler) {
        length = length/scaler;
    }

    @Override
    public void rotate(Integer angle) {

    }
}
