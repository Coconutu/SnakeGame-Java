package com.daniel;
/**
 * Snake Game!
 * // https://www.youtube.com/watch?v=Y62MJny9LHg   min 28:48 :)
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;

public class SnakeGame extends JPanel implements ActionListener,KeyListener{


    private class Tile{
        int x;
        int y;

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int boardWidth;
    int boardHeight;
    int tileSize=25;

    Tile snakeHead;
    Tile food;
    Random random;
    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;

    SnakeGame(int boardWidth,int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.BLACK);
        snakeHead=new Tile(5,5);
        food =new Tile(10,10);
        random=new Random();
        placeFood();
        gameLoop=new Timer(100,this);
        gameLoop.start();
        velocityX=0;
        velocityY=0;
        addKeyListener(this);
        setFocusable(true);

    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //Grid
        for(int i=0;i<boardWidth/tileSize;i++){
            g.drawLine(i*tileSize,0,i*tileSize,boardHeight); //vertical lines
            g.drawLine(0,i*tileSize,boardWidth,i*tileSize); //horizontal lines
        }
        //Food
        g.setColor(Color.RED);
        g.fillRect(food.x*tileSize, food.y*tileSize,tileSize,tileSize);


        //Snake
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize,tileSize,tileSize);
    }
        public void placeFood( )
        {
            food.x=random.nextInt(boardWidth/tileSize); //600/25 =24
            food.y=random.nextInt(boardHeight/tileSize);

        }
    public void move() {
        //snake head
        snakeHead.x+=velocityX;
        snakeHead.y+=velocityY;

    }

    @java.lang.Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        move();
        repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && velocityY!=1)
        {
            velocityX=0;
            velocityY=-1;
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN && velocityY!=-1)
        {
            velocityX=0;
            velocityY=1;
        } else if (e.getKeyCode()==KeyEvent.VK_LEFT && velocityX!=1)
        {
            velocityX=-1;
            velocityY=0;

        } else if (e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX!=-1)
        {
            velocityX=1;
            velocityY=0;

        }

    }
//DO NOT NEED
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}


}
