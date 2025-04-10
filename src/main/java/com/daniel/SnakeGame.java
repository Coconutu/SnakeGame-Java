package com.daniel;
/**
 * Snake Game!
 * // https://www.youtube.com/watch?v=Y62MJny9LHg   min 35:37 :)
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    ArrayList<Tile> snakeBody;
    Tile food;
    Random random;
    //game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver=false;

    SnakeGame(int boardWidth,int boardHeight){
        this.boardWidth=boardWidth;
        this.boardHeight=boardHeight;
        setPreferredSize(new Dimension(this.boardWidth,this.boardHeight));
        setBackground(Color.BLACK);
        snakeHead=new Tile(5,5);
        snakeBody=new ArrayList<Tile>();

        food =new Tile(10,10);
        random=new Random();
        placeFood();
        gameLoop=new Timer(150,this);
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
//        //Grid
//        for(int i=0;i<boardWidth/tileSize;i++){
//            g.drawLine(i*tileSize,0,i*tileSize,boardHeight); //vertical lines
//            g.drawLine(0,i*tileSize,boardWidth,i*tileSize); //horizontal lines
//        }
        //Food
        g.setColor(Color.RED);
        //g.fillRect(food.x*tileSize, food.y*tileSize,tileSize,tileSize);
        g.fill3DRect(food.x*tileSize, food.y*tileSize,tileSize,tileSize,true);


        //Snake head
        g.setColor(Color.GREEN);
        //g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize,tileSize,tileSize);
        g.fill3DRect(snakeHead.x*tileSize, snakeHead.y*tileSize,tileSize,tileSize,true);

        //Snake body
        for(int i=0;i<snakeBody.size();i++)
        {
            Tile snakePart=snakeBody.get(i);
            //g.fillRect(snakePart.x*tileSize,snakePart.y*tileSize,tileSize,tileSize);
            g.fill3DRect(snakePart.x*tileSize,snakePart.y*tileSize,tileSize,tileSize,true);
        }
        //score
        g.setFont(new Font("Arial",Font.PLAIN,16));
        if(gameOver){
            g.setColor(Color.RED);
            g.drawString("Game Over :"+String.valueOf(snakeBody.size()),tileSize-16,tileSize);
        }
        else {
                g.drawString("Score "+String.valueOf(snakeBody.size()),tileSize-16,tileSize);
        }
    }
        public void placeFood( )
        {
            food.x=random.nextInt(boardWidth/tileSize); //600/25 =24
            food.y=random.nextInt(boardHeight/tileSize);

        }

        public boolean collision(Tile tile1, Tile tile2)
        {
            return tile1.x==tile2.x && tile1.y==tile2.y;
        }

    public void move() {

        //Eat food;
        if (collision(snakeHead,food))
        {
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        //Snake Body
        for(int i=snakeBody.size()-1;i>=0;i--)
        {
            Tile snakePart=snakeBody.get(i);
            if (i==0)
            {
                snakePart.x=snakeHead.x;
                snakePart.y= snakeHead.y;
            }
            else {
                Tile prevSnakePart=snakeBody.get(i-1);
                snakePart.x= prevSnakePart.x;
                snakePart.y= prevSnakePart.y;
            }
        }

        //Snake head
        snakeHead.x+=velocityX;
        snakeHead.y+=velocityY;
        //game over conditions
        for(int i=0;i<snakeBody.size();i++)
        {
            Tile snakePart=snakeBody.get(i);
            //collite with the snake head
            if (collision(snakeHead,snakePart))
            {
                gameOver=true;
            }
        }
        if (snakeHead.x * tileSize < 0||snakeHead.x*tileSize>boardWidth||
            snakeHead.y*tileSize<0||snakeHead.y*tileSize>boardHeight)
        {
            gameOver=true;
        }

    }

    @java.lang.Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        move();
        repaint();
        if(gameOver) {
            gameLoop.stop();
        }

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
