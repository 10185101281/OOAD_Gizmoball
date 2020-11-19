package component;

public class Rectangle extends Component{

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
