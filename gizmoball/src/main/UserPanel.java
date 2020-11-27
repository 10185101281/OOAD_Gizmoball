package main;

import component.*;
import gui.*;
import gui.Character;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

/**
 * @Author BaoLiang
 * @Date 2020/11/17 9:30
 * @Version 1.0
 */
public class UserPanel extends JFrame{
    private Container contentPane;
    private Board board;
    private JPanel boardPanel,sideBarPanel;
    private JPanel titlePanel, modeControlPanel, layoutConsolePanel;
    private JPanel validLayoutConsolePanel, invalidLayoutConsolePanel;
    private JPanel validToolPanel, invalidToolPanel;
    private JPanel specialComponentPanel, toolPanel;
    private JPanel mainComponentPanel;
    private JPanel[] componentPanel = new JPanel[2]; private Integer componentPanelPoint;
    private ActionListener toolValidControlListener;
    private static final int FRAMES_PER_SECOND = 100;
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
            board.refresh(true);
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
        boardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        boardPanel.setPreferredSize(new Dimension(830,830));
        ControlSystem.createBoard();
        ControlSystem.createBall();
        board = ControlSystem.getBoard();
        boardPanel.add(board);
        initTimer();
    }

    private Integer characterPointer = 0;
    private Character[] characters = new Character[]{
            new Character("Venti", new JRadioButtonP("blackhole",XBlackHole.picture)),
            new Character("Klee", new JRadioButtonP("boom")),
            new Character("Qiqi", new JRadioButtonP("iceball")),
            new Character("Xiao", new JRadioButtonP("Mask")),
            new Character("Ningguang", new JRadioButtonP("barrier")),
    };
    private String[] characterNames = new String[]{
            "Venti", "Klee", "Qiqi","Xiao","Ningguang",
            //"Jean","Amber","Lisa","Barbara","Noelle","Fischl","Sucrose","Mona","Beidou","Keqing"
    };
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 16:00f
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
        layoutMode.setPreferredSize(new Dimension(100, 45));
        JButton playMode = new JButton("Play Mode");
        playMode.setPreferredSize(new Dimension(100,45));

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
                if(board.getSelectedComponent() != null){
                    board.getSelectedComponent().setSelected(false);
                    board.setSelectedComponent(null);
                }
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
        specialComponentPanel.setPreferredSize(new Dimension(310,120));
        specialComponentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Special component"));
        specialComponentPanel.setBackground(new Color(0x00CED1));

        JButton avatarButton = new JButton(characters[characterPointer].getCharacterAvatar());
        avatarButton.setPreferredSize(new Dimension(70,70));
        avatarButton.setBorder(buttonBorders[1]);
        specialComponentPanel.add(avatarButton);

        JRadioButton specialComponentButton = characters[characterPointer].getButton();
        specialComponentButton.addActionListener(toolValidControlListener);
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
                buttonGroup.remove(specialComponentButton);
                initSpecialComponentPanel();
                validLayoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
                //validLayoutConsolePanel.repaint();
                validLayoutConsolePanel.updateUI();
            }
        });

    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/27 14:00
     * @Version 1.0
     * 初始化第一个componentPanel页
     */
    private void initComponentPanel0(){
        componentPanel[0] = new JPanel(new BorderLayout());
        componentPanel[0].setPreferredSize(new Dimension(310,335));
        componentPanel[0].setBackground(new Color(0x00CED1));
        JButton controlButton = new JButton("Next Page>>");
        controlButton.setPreferredSize(new Dimension(310,30));
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainComponentPanel.getLayout();
                cl.show(mainComponentPanel,"1");
            }
        });
        JPanel componentPage = new JPanel(new FlowLayout(FlowLayout.CENTER));
        componentPage.setPreferredSize(new Dimension(310, 305));
        componentPage.setBackground(new Color(0x00CED1));
        componentPanel[0].add(controlButton,BorderLayout.NORTH);
        componentPanel[0].add(componentPage);

        JRadioButtonP placement = new JRadioButtonP("placement",new ImageIcon("gizmoball/src/picture/component/placement.png"));
        JRadioButtonP rectangle = new JRadioButtonP("rectangle", XRectangle.picture);
        JRadioButtonP triangle = new JRadioButtonP("triangle",XTriangle.picture);
        JRadioButtonP circle = new JRadioButtonP("circle",XCircle.picture);
        JRadioButtonP straightPipe = new JRadioButtonP("straightPipe");
        JRadioButtonP curvedPipe = new JRadioButtonP("curvedPipe");
        JRadioButtonP[] jRadioButtonGroups = new JRadioButtonP[]{
                placement, rectangle,
                triangle, circle,
                straightPipe,curvedPipe
        };
        for(int i=0; i<3; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(componentPage.getPreferredSize().width-20,(componentPage.getPreferredSize().height-20)/3-5));
            tJPanel.setBackground(new Color(0x00CED1));
            for(int j=0; j<2; j++){
                int id = i*2+j;
                jRadioButtonGroups[id].addActionListener(toolValidControlListener);
                JRadioButton button = jRadioButtonGroups[id].getjRadioButton();
                JLabel label = jRadioButtonGroups[id].getjLabel();

                buttonGroup.add(button);
                tJPanel.add(button);
                tJPanel.add(label);
            }
            componentPage.add(tJPanel);
        }
        placement.getjRadioButton().setSelected(true);
        board.setNowComponent("placement");
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/27 14:00
     * @Version 1.0
     * 初始化第二个componentPanel页
     */
    private void initComponentPanel1(){
        componentPanel[1] = new JPanel(new BorderLayout());
        componentPanel[1].setPreferredSize(new Dimension(310,335));
        componentPanel[1].setBackground(new Color(0x00CED1));
        JButton controlButton = new JButton("<<Previous Page");
        controlButton.setPreferredSize(new Dimension(310,30));
        controlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)mainComponentPanel.getLayout();
                cl.show(mainComponentPanel,"0");
            }
        });
        JPanel componentPage = new JPanel(new FlowLayout(FlowLayout.CENTER));
        componentPage.setPreferredSize(new Dimension(310, 305));
        componentPage.setBackground(new Color(0x00CED1));
        componentPanel[1].add(controlButton,BorderLayout.NORTH);
        componentPanel[1].add(componentPage);
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/17 21:00
     * @Version 1.0
     * 初始化componentPanel
     */
    private void initMainComponentPanel(){
        mainComponentPanel = new JPanel(new CardLayout());
        mainComponentPanel.setPreferredSize(new Dimension(310,335));
        mainComponentPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2], "component"));
        mainComponentPanel.setBackground(new Color(0x00CED1));

        initComponentPanel0();
        initComponentPanel1();

        mainComponentPanel.add(componentPanel[0],"0");
        mainComponentPanel.add(componentPanel[1],"1");

        CardLayout cl = (CardLayout)mainComponentPanel.getLayout();
        cl.show(mainComponentPanel,"0");
    }
    /**
     * @Author BaoLiang
     * @Date 2020/11/24 17:00
     * @Version 1.0
     * 初始化invalidToolPanel。
     */
    private void initInvalidToolPanel(){
        invalidToolPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        invalidToolPanel.setPreferredSize(new Dimension(310,205));
        invalidToolPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Tool"));
        invalidToolPanel.setBackground(new Color(0x8B8B7A));

        JLabel [] texts = new JLabel[]{
                new JLabel("ONLY IN"),
                new JLabel("PLACEMENT STATE"),
                new JLabel("CAN USE TOOL.")
        };
        for(int i=0; i<3; i++){
            texts[i].setPreferredSize(new Dimension(300,50));
            texts[i].setFont(new Font("Arial",Font.BOLD,20));
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
        validToolPanel.setPreferredSize(new Dimension(310,205));
        validToolPanel.setBorder(BorderFactory.createTitledBorder(linerBorders[2],"Tool"));
        validToolPanel.setBackground(new Color(0x00CED1));

        int width = 70, height = 70;
        ImageIcon rotateImage = new ImageIcon("gizmoball/src/picture/tool/rotate.png");
        rotateImage.setImage(rotateImage.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        ImageIcon deleteImage = new ImageIcon("gizmoball/src/picture/tool/delete.png");
        deleteImage.setImage(deleteImage.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        ImageIcon enlargeImage = new ImageIcon("gizmoball/src/picture/tool/enlarge.png");
        enlargeImage.setImage(enlargeImage.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
        ImageIcon shrinkImage = new ImageIcon("gizmoball/src/picture/tool/shrink.png");
        shrinkImage.setImage(shrinkImage.getImage().getScaledInstance(width,30,Image.SCALE_DEFAULT));

        JButton rotateButton = new JButton(rotateImage);
        JButton deleteButton = new JButton(deleteImage);
        JButton enlargeButton = new JButton(enlargeImage);
        JButton shrinkButton = new JButton(shrinkImage);
        JButton[] toolButtons = new JButton[]{
                rotateButton, deleteButton,
                enlargeButton, shrinkButton,
        };

        for(int i=0; i<2; i++){
            JPanel tJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            tJPanel.setPreferredSize(new Dimension(toolPanel.getPreferredSize().width-20,(toolPanel.getPreferredSize().height-20)/2-5));
            tJPanel.setBackground(new Color(0x00CED1));
            for(int j=0; j<2; j++){
                int id = i*2+j;
                toolButtons[id].setPreferredSize(new Dimension(70,70));
                toolButtons[id].setBorder(buttonBorders[0]);
                tJPanel.add(toolButtons[id]);
            }
            validToolPanel.add(tJPanel);
        }
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.getSelectedComponent() != null){
                    board.getSelectedComponent().rotate();
                    board.refresh(false);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.getSelectedComponent() != null){
                    board.getSelectedComponent().delete();
                    board.refresh(false);
                }
            }
        });
        enlargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.getSelectedComponent() != null){
                    board.getSelectedComponent().enlarge();
                    board.refresh(false);
                }
            }
        });
        shrinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.getSelectedComponent() != null){
                    board.getSelectedComponent().shrink();
                    board.refresh(false);
                }
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
        toolPanel.setPreferredSize(new Dimension(310,205));

        initInvalidToolPanel();
        initValidToolPanel();
        toolPanel.add(validToolPanel,"valid");
        toolPanel.add(invalidToolPanel,"invalid");

        initToolValidControlListener();
    }

    /**
     * @Author BaoLiang
     * @Date 2020/11/24 17:40
     * @Version 1.0
     * 初始化toolValidControlListener
     */
    private void initToolValidControlListener(){
        toolValidControlListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eActionCommand = e.getActionCommand();
                board.setNowComponent(eActionCommand);
                if(eActionCommand != null && eActionCommand.equals("placement")){
                    CardLayout cl = (CardLayout)toolPanel.getLayout();
                    cl.show(toolPanel,"valid");
                } else {
                    if(board.getSelectedComponent() != null){
                        board.getSelectedComponent().setSelected(false);
                        board.setSelectedComponent(null);
                        board.refresh(false);
                    }
                    CardLayout cl = (CardLayout)toolPanel.getLayout();
                    cl.show(toolPanel,"invalid");
                }
            }
        };
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
        validLayoutConsolePanel.setPreferredSize(new Dimension(310, 675));
        validLayoutConsolePanel.setBorder(linerBorders[1]);
        validLayoutConsolePanel.setBackground(new Color(0x00CED1));

        initToolPanel();
        initSpecialComponentPanel();
        initMainComponentPanel();

        validLayoutConsolePanel.add(specialComponentPanel,BorderLayout.NORTH);
        validLayoutConsolePanel.add(mainComponentPanel,BorderLayout.CENTER);
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
        invalidLayoutConsolePanel.setPreferredSize(new Dimension(310, 675));
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
        layoutConsolePanel.setPreferredSize(new Dimension(310, 675));
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
        sideBarPanel.setPreferredSize(new Dimension(320, 830));
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
        contentPane.setPreferredSize(new Dimension(1150,830));

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
        setLocation(screenWidth/6, screenHeight/16);
    }
    public UserPanel(String title){
        super(title);
        setCenter();
        initContentPane();
    }
}