package main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 200;
    final int scale = 200;
    final int tileSize = originalTileSize + scale;
    final int maxScreenCol = 300;
    final int maxScreenRow = 200;
    final int screenWidth = tileSize+maxScreenCol;
    final int screenHeight = tileSize+maxScreenRow;

    int FPS=80;

    KeyHandler keyH=new KeyHandler();
    Thread gameThread;

    int playerX=100;
    int playerY=100;
    int playerSpeed=4;

    Image playerSprite;
    Image backgroundImage;


    public GamePanel() {
        loadSprite();


        this.setPreferredSize(new DimensionUIResource(screenWidth,screenHeight));
        
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void loadSprite() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("sprite.png"));
            playerSprite = icon.getImage();
            ImageIcon bgIcon = new ImageIcon(getClass().getResource("background.png"));
        backgroundImage = bgIcon.getImage();
        } catch (Exception e) {
            System.err.println("Error loading sprite: " + e.getMessage());
            e.printStackTrace(); 
        } 
        }


    @Override
    public void run() {
        double drawInterval=1000000000/FPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        while(gameThread!=null) {
           update();
           repaint();
           try {
            double remainingTime = nextDrawTime-System.nanoTime();
            remainingTime=remainingTime/1000000;
            if(remainingTime<0){
                remainingTime=0;
            }
            Thread.sleep((long)remainingTime);
            nextDrawTime+=drawInterval;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }
    
    public void update() {
        if (keyH.upPressed==true) {
            playerY-=playerSpeed;
        }
        else if (keyH.downPressed==true){
            playerY+=playerSpeed;

        }
        else if (keyH.leftPressed==true){
            playerX-=playerSpeed;

        }
        else if (keyH.rightPressed==true){
            playerX+=playerSpeed;

        }
    }
    
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (backgroundImage != null) {
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, this);
    }

    if (playerSprite != null) {
        g2.drawImage(playerSprite, playerX, playerY, 150, 150, this); 
    }
    g2.dispose();
}
}
