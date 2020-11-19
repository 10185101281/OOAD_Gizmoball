package Component;

public class Circle implements Component{
    Integer x;  //外切正方形左上角坐标
    Integer y;
    Integer radius;
    public Circle(Integer x,Integer y){
        this.x = x;
        this.y = y;
        radius = 20;
    }

    @Override
    public void enlarge(Integer scale) {

    }

    @Override
    public void shrink(Integer scale) {

    }

    @Override
    public void rotate(Integer scale) {

    }
}
