package Component;

public class Circle extends Component{
    Integer x;  //外切正方形左上角坐标
    Integer y;
    Integer radius;
    public Circle(Integer x,Integer y){
        this.x = x;
        this.y = y;
        radius = 20;
    }

    @Override
    public void enlarge(Integer scaler) {

    }

    @Override
    public void shrink(Integer scaler) {

    }

    @Override
    public void rotate(Integer scaler) {

    }
}
