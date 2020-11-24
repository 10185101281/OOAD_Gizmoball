package main;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class UserPanel extends JFrame{
    private Container contentPane;
    private Board boardPanel;
    private JPanel sideBarPanel;
    private JPanel titlePanel, modeControlPanel, layoutConsolePanel;
    private JPanel validLayoutConsolePanel, invalidLayoutConsolePanel;
    private JPanel validToolPanel, invalidToolPanel;
    private JPanel specialComponentPanel, componentPanel, toolPanel;
    private static final int FRAMES_PER_SECOND = 1000;
    private Timer timer;

    private ButtonGroup buttonGroup = new ButtonGroup();
    private Border[] linerBorders = new Border[]{
            BorderFactory.createLineBorder(new Color(0xD3D3D3),5),
            BorderFactory.createLineBorder(new Color(0x708090),3),
            BorderFactory.createLineBorder(new Color(0x000000),2),
    };
    private Border[] buttonBorders = new Border[]{
            BorderFactory.createLineBorder(Color.BLACK,1),
            BorderFactory.createLineBorder(new Color(0xBEBEBE), 2),
    };

    /**
     * @Author BaoLiang
     * @Date 2020/11/19 15:25
     * @Version 1.0
     * 刷新Board
     */
    private class RefreshBoard implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            boardPanel.refresh();
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化Timer
     */
    private void initTimer(){
        timer = new Timer(1000/FRAMES_PER_SECOND, new RefreshBoard());
        timer.stop();
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 15:15
     * @Version 1.0
     * 初始化BoardPanel。
     * 设置BordPanel为绝对布局，为其渲染背景。
     */
    private void initBoardPanel(){
        ControlSystem.createBoard();
        ControlSystem.createBall();
        boardPanel = ControlSystem.getBoard();
        initTimer();
    }

    private Integer characterPointer = 0;
    private Character[] characters = new Character[]{
            new Character("Venti",new JRadioButton(""),new JLabel("Black Hole")),
            new Character("Klee",new JRadioButton(""),new JLabel("Boom")),
            new Character("Qiqi",new JRadioButton(""),new JLabel("Ice Ball")),
            new Character("Xiao",new JRadioButton(""),new JLabel("Mask")),
            new Character("Ningguang",new JRadioButton(""),new JLabel("Barrier")),

    };
    private String[] characterNames = new String[]{
            "Venti", "Klee", "Qiqi","Xiao","Ningguang",
            //"Jean","Amber","Lisa","Barbara","Noelle","Fischl","Sucrose","Mona","Beidou","Keqing"
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
     * @Date 2020/11/13 16:00
     * @Version 1.0
     * 生成指定头像。
     * @param width 头像的宽度
     * @param height 头像的高度
     * @param s 角色名称
     * @return 根据角色名称生成的头像
     */
    private ImageIcon getAvatar(int width,int height,String s){
        ImageIcon imageIcon = new ImageIcon("gizmoball/src/picture/avatar/"+s+".png");
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
        layoutMode.setBorder(buttonBorders[1]);
        layoutMode.setOpaque(true);
        JButton playMode = new JButton("Play Mode");
        playMode.setPreferredSize(new Dimension(100,60));
        playMode.setBorder(buttonBorders[1]);
        playMode.setOpaque(true);

        layoutMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)layoutConsolePanel.getLayout();
                cl.show(layoutConsolePanel,"valid");
                timer.stop();
            }
        });
        playMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)layoutConsolePanel.getLayout();
                cl.show(layoutConsolePanel,"invalid");
                timer.start();
            }
        });
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
        specialComponentPanel.setPreferredSize(new Dimension(300,120));
        specialComponentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Special component"));
        specialComponentPanel.setBackground(new Color(0x00CED1));

        JButton avatarButton = new JButton(getAvatar(70, 70, characters[characterPointer].getName()));
        avatarButton.setPreferredSize(new Dimension(70,70));
        avatarButton.setBorder(buttonBorders[1]);
        specialComponentPanel.add(avatarButton);

        JRadioButton specialComponentButton = characters[characterPointer].getButton();
        buttonGroup.add(specialComponentButton);
        specialComponentPanel.add(specialComponentButton);

        JLabel specialComponent = characters[characterPointer].getComponent();
        specialComponent.setPreferredSize(new Dimension(70, 70));
        specialComponentPanel.add(specialComponent);

        avatarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterPointer = (characterPointer+1)%characters.length;
                validLayoutConsolePanel.remove(specialComponentPanel);
                initSpecialComponentPanel();
                validLayoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
                //validLayoutConsolePanel.repaint();
                validLayoutConsolePanel.updateUI();
            }
        });

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
        componentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2], "component"));
        componentPanel.setBackground(new Color(0x00CED1));

        JRadioButton[] componentButtons = new JRadioButton[]{
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
                new JRadioButton(), new JRadioButton(),
        };
        JLabel[] component = new JLabel[]{
                new JLabel("Move"), new JLabel("Ball"),
                new JLabel("Triangle"), new JLabel("Rectange"),
                new JLabel("Straight Pipe"), new JLabel("Curved Pipe"),
        };
        for(int i=0; i<3; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(componentPanel.getPreferredSize().width-20,(componentPanel.getPreferredSize().height-20)/3-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                buttonGroup.add(componentButtons[i*2+j]);
                tJPanel.add(componentButtons[i*2+j]);
                component[i*2+j].setPreferredSize(new Dimension(70,70));
                tJPanel.add(component[i*2+j]);
            }
            componentPanel.add(tJPanel);
        }
        componentButtons[0].setSelected(true);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/24 17:00
     * @Version 1.0
     * 初始化invalidToolPanel。
     */
    private void initInvalidToolPanel(){
        invalidToolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        invalidToolPanel.setPreferredSize(new Dimension(300,195));
        invalidToolPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Tool"));
        invalidToolPanel.setBackground(new Color(0x8B8B7A));

        JLabel [] texts = new JLabel[]{
                new JLabel("ONLY IN"),
                new JLabel("PLACEMENT STATE"),
                new JLabel("CAN USE TOOL.")
        };
        for(int i=0; i<3; i++){
            texts[i].setPreferredSize(new Dimension(300,60));
            texts[i].setFont(new Font("Arial",Font.BOLD,30));
            texts[i].setHorizontalAlignment(SwingConstants.CENTER);
            texts[i].setVerticalAlignment(SwingConstants.CENTER);
            invalidToolPanel.add(texts[i]);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/24 17:00
     * @Version 1.0
     * 初始化validToolPanel。
     */
    private void initValidToolPanel(){
        validToolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        validToolPanel.setPreferredSize(new Dimension(300,195));
        validToolPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Tool"));
        validToolPanel.setBackground(new Color(0x00CED1));

        JButton spinButton = new JButton("Spin");
        JButton deleteButton = new JButton("Delete");
        JButton zoominButton = new JButton("Zoom in");
        JButton zoomoutButton = new JButton("Zoom out");
        JButton[] toolButtons = new JButton[]{
                spinButton, deleteButton,
                zoominButton, zoomoutButton,
        };
        for(int i=0; i<2; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(toolPanel.getPreferredSize().width-20,(toolPanel.getPreferredSize().height-20)/2-5));
            tJPanel.setBackground(new Color(0x54FF9F));
            for(int j=0; j<2; j++){
                toolButtons[i*2+j].setPreferredSize(new Dimension(70,70));
                toolButtons[i*2+j].setBorder(buttonBorders[0]);
                tJPanel.add(toolButtons[i*2+j]);
            }
            validToolPanel.add(tJPanel);
        }
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        zoominButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        zoomoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化toolPanel
     */
    private void initToolPanel(){
        toolPanel = new JPanel(new CardLayout());
        toolPanel.setPreferredSize(new Dimension(300,195));
        initInvalidToolPanel();
        initValidToolPanel();
        toolPanel.add(validToolPanel,"valid");
        toolPanel.add(invalidToolPanel,"invalid");
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/18 18:00
     * @Version 1.0
     * 初始化validLayoutConsolePanel。
     * 设置整体布局为Board布局，其中speceialComponentPanel居上，componentPanel居中，toolPanel居下。
     */
    private void initValidLayoutConsolePanel(){
        validLayoutConsolePanel = new JPanel(new BorderLayout());
        validLayoutConsolePanel.setPreferredSize(new Dimension(310, 630));
        validLayoutConsolePanel.setBorder(linerBorders[1]);
        validLayoutConsolePanel.setBackground(new Color(0x00CED1));

        initSpecialComponentPanel();
        initComponentPanel();
        initToolPanel();
        validLayoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
        validLayoutConsolePanel.add(componentPanel,BorderLayout.CENTER);
        validLayoutConsolePanel.add(toolPanel,BorderLayout.SOUTH);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 18:00
     * @Version 1.0
     * 初始化invalidLayoutConsolePanel。
     * 设置整体布局为Flow布局，显示不可以用提示信息。
     */
    private void initInvalidLayoutConsolePanel(){
        invalidLayoutConsolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        invalidLayoutConsolePanel.setPreferredSize(new Dimension(310, 630));
        invalidLayoutConsolePanel.setBorder(linerBorders[1]);
        invalidLayoutConsolePanel.setBackground(new Color(0x8B8B7A));

        JLabel [] texts = new JLabel[]{
                new JLabel("IN PLAY MODE,"),
                new JLabel("CAN NOT"),
                new JLabel("LAY OUT.")
        };
        for(int i=0; i<3; i++){
            texts[i].setPreferredSize(new Dimension(300,60));
            texts[i].setFont(new Font("Arial",Font.BOLD,30));
            texts[i].setHorizontalAlignment(SwingConstants.CENTER);
            texts[i].setVerticalAlignment(SwingConstants.CENTER);
            invalidLayoutConsolePanel.add(texts[i]);
        }
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/18 18:00
     * @Version 1.0
     * 初始化LayoutConsolePanel。
     * 设置整体布局为Card布局，包括validLayoutConsolePanel与invalidConsolePanel两个状态。
     */
    private void initLayoutConsolePanel(){
        layoutConsolePanel = new JPanel(new CardLayout());
        layoutConsolePanel.setPreferredSize(new Dimension(310, 630));
        layoutConsolePanel.setBorder(linerBorders[1]);
        layoutConsolePanel.setBackground(new Color(0x00CED1));
        initValidLayoutConsolePanel();
        initInvalidLayoutConsolePanel();
        layoutConsolePanel.add(validLayoutConsolePanel, "valid");
        layoutConsolePanel.add(invalidLayoutConsolePanel,"invalid");
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
