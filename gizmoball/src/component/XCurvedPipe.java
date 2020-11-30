package component;

import main.Board;

public class XCurvedPipe extends XComponent{
    public XCurvedPipe(Integer x, Integer y, Board board){
        super(x, y, board);
    }
    @Override
    public Integer is_collision(BouncingBall ball){
        return 0;
    }
}
