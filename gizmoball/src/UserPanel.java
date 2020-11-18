import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class UserPanel extends JFrame{
    private Container contentPane;
    //private Board  boardPanel;
    private JPanel boardPanel, sideBarPanel;
    private JPanel titlePanel, modeControlPanel, layoutConsolePanel;
    private JPanel validLayoutConsolePanel, forbidLayoutConsolePanel;
    private JPanel specialComponentPanel, componentPanel, toolPanel;
    private Border[] linerBorders = new Border[]{
            BorderFactory.createLineBorder(new Color(0xD3D3D3),5),
            BorderFactory.createLineBorder(new Color(0x708090),3),
            BorderFactory.createLineBorder(new Color(0x000000),2),
    };
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化BoardPanel。
     * 设置BordPanel为绝对布局，为其渲染背景。
     */
    private void initBoardPanel(){
        ControlSystem.createBoard();
        boardPanel = ControlSystem.getBoard();
    }

    private String[] characterNames = new String[]{
            "Qiqi","Jean","Amber","Lisa","Barbara","Venti",
            "Klee","Noelle","Fischl","Sucrose","Mona","Xiao",
            "Ningguang","Beidou","Keqing"
    };
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 16:00
     * @Version 1.0
     * 随机生成头像。
     * @param width 头像的宽度
     * @param height 头像的高度
     * @return 随机生成的头像
     */
    private ImageIcon getRandomAvatar(int width,int height){
        Integer randomInteger = new Random().nextInt(characterNames.length);
        ImageIcon imageIcon = new ImageIcon("gizmoball/src/picture/avatar/"+characterNames[randomInteger]+".png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        return imageIcon;
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 16:00
     * @Version 1.0
     * 初始化titlePanel。
     */
    private void initTitlePanel(){
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.setPreferredSize(new Dimension(310,60));
        titlePanel.setBackground(new Color(0x778899));
        titlePanel.setBorder(linerBorders[1]);

        JLabel titleText = new JLabel("GIZMO BALL");
        titleText.setPreferredSize(new Dimension(300,55));
        titleText.setFont(new Font("Times New Roman",Font.BOLD,45));
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        titleText.setVerticalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleText);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 20:30
     * @Version 1.0
     * 初始化modeControlPanel。
     * 设置为流布局。
     * 设置两个按钮，layoutMode和playMode，控制两个模式。
     */
    private void initModeControlPanel(){
        modeControlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        modeControlPanel.setPreferredSize(new Dimension(310, 70));
        modeControlPanel.setBackground(new Color(0x1E90FF));
        modeControlPanel.setBorder(linerBorders[1]);

        JButton layoutMode = new JButton("Layout Mode");
        layoutMode.setPreferredSize(new Dimension(100, 60));
        layoutMode.setBackground(new Color(0xBEBEBE));
        layoutMode.setOpaque(true);
        JButton playMode = new JButton("Play Mode");
        playMode.setPreferredSize(new Dimension(100,60));
        playMode.setBackground(new Color(0xBEBEBE));
        playMode.setOpaque(true);
        modeControlPanel.add(layoutMode);
        modeControlPanel.add(playMode);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化specialComponentPanel
     */
    private void initSpecialComponentPanel(){
        specialComponentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        specialComponentPanel.setPreferredSize(new Dimension(300,95));
        specialComponentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Special Component"));
        specialComponentPanel.setBackground(new Color(0x00CED1));

        JLabel avatar = new JLabel(getRandomAvatar(50,50));
        avatar.setBorder(BorderFactory.createLineBorder(Color.RED,2));
        specialComponentPanel.add(avatar);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化componentPanel
     */
    private void initComponentPanel(){
        componentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        componentPanel.setPreferredSize(new Dimension(300,295));
        componentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Component"));
        componentPanel.setBackground(new Color(0x00CED1));

        JRadioButton[] jrs = new JRadioButton[]{
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
        };
        ButtonGroup buttonGroup = new ButtonGroup();
        for(int i=0; i<3; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(componentPanel.getPreferredSize().width-20,(componentPanel.getPreferredSize().height-20)/3-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                buttonGroup.add(jrs[i*2+j]);
                tJPanel.add(jrs[i*2+j]);
                tJPanel.add(new JLabel(getRandomAvatar(70,70)));
            }
            componentPanel.add(tJPanel);
        }
        jrs[0].setSelected(true);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化toolPanel
     */
    private void initToolPanel(){
        toolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        toolPanel.setPreferredSize(new Dimension(300,195));
        toolPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Tool"));
        toolPanel.setBackground(new Color(0x00CED1));

        for(int i=0; i<2; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(toolPanel.getPreferredSize().width-20,(toolPanel.getPreferredSize().height-20)/2-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                JButton tJButton = new JButton(getRandomAvatar(70,70));
                tJButton.setPreferredSize(new Dimension(70,70));
                tJButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                tJPanel.add(tJButton);
            }
            toolPanel.add(tJPanel);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 初始化forbidLayoutConsolePanel
     */
    private void initForbidLayoutConsolePanel(){

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 11:30
     * @Version 1.0
     * 初始化validLayoutConsolePanel
     */
    private void initValidLayoutConsolePanel(){
        validLayoutConsolePanel = new JPanel(new BorderLayout());
        validLayoutConsolePanel.setPreferredSize(new Dimension(310, 630));
        validLayoutConsolePanel.setBorder(linerBorders[1]);
        validLayoutConsolePanel.setBackground(new Color(0x00CED1));
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化LayoutConsolePanel。
     * 设置整体布局为Border布局，其中speceialComponentPanel居上，componentPanel居中，toolPanel居下。
     */
    private void initLayoutConsolePanel(){
        layoutConsolePanel = new JPanel(new BorderLayout());
        layoutConsolePanel.setPreferredSize(new Dimension(310, 630));
        layoutConsolePanel.setBorder(linerBorders[1]);
        layoutConsolePanel.setBackground(new Color(0x00CED1));

        initSpecialComponentPanel();
        initComponentPanel();
        initToolPanel();
        layoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
        layoutConsolePanel.add(componentPanel,BorderLayout.CENTER);
        layoutConsolePanel.add(toolPanel,BorderLayout.SOUTH);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:45
     * @Version 1.0
     * 初始化SideBarPanel。
     * 设置整体布局为Border布局，其中titlePanel居上，modeControlPanel居中，layoutConsolePanel居下。
     */
    private void initSideBarPanel(){
        sideBarPanel = new JPanel(new BorderLayout());
        sideBarPanel.setPreferredSize(new Dimension(320, 800));
        sideBarPanel.setBorder(linerBorders[0]);
        sideBarPanel.setBackground(Color.GRAY);

        initTitlePanel();
        initModeControlPanel();
        initLayoutConsolePanel();
        sideBarPanel.add(titlePanel,BorderLayout.NORTH);
        sideBarPanel.add(modeControlPanel,BorderLayout.CENTER);
        sideBarPanel.add(layoutConsolePanel,BorderLayout.SOUTH);
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化ContentPane，即初始化整个UserPanel。
     * 设置ContentPane为Border布局，其中boardPanel居中部，sideBarPanel居右部。
     */
    private void initContentPane(){
        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1105,800));

        initBoardPanel();
        initSideBarPanel();
        contentPane.add(boardPanel,BorderLayout.CENTER);
        contentPane.add(sideBarPanel,BorderLayout.EAST);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:00
     * @Version 1.0
     * 设置整体UserPanel在屏幕居中
     */
    private void setCenter(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth/6, screenHeight/12);

    }
    public UserPanel(String title){
        super(title);
        setCenter();
        initContentPane();
    }
}
