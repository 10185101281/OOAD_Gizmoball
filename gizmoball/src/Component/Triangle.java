package Component;

public class Triangle implements Component{
    Integer origin_x;   //外接正方形左上角坐标
    Integer origin_y;
    Integer length;     //边长
    Integer angle;
    
    public Triangle(Integer x,Integer y){
        this.origin_x = x;
        this.origin_y = y;
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
