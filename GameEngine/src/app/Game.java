package app;

import java.awt.*;
import java.awt.Dimension;
import java.awt.image.*;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    private final int WIDTH = 160;
    private final int HEIGTH = 120;
    private final int SCALE = 4;
    private BufferedImage image;

    public Game(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH,HEIGTH, BufferedImage.TYPE_INT_RGB);
    }

    public void initFrame(){
        frame = new JFrame("Game #1");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start(){

        thread = new Thread(this);
        isRunning = true;
        thread.start();

    }

    public synchronized void stop(){

    }

   
    public static void main(String[] args) {
        Game game = new Game();
        game.start();   
    }

    public void tick(){
        
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(19,19,19));
        g.fillRect(0,0,WIDTH,HEIGTH);
        g = bs.getDrawGraphics();
        g.drawImage(image,0,0,WIDTH*SCALE, HEIGTH*SCALE, null);
        bs.show();
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >=  1000){
                System.out.println("FPS: "+ frames);
                frames = 0;
                timer += 1000;
            }

        }

    }

}