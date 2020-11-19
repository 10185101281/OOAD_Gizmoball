package Component;

public class Triangle extends Component{

    
    public Triangle(Integer x,Integer y){
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
