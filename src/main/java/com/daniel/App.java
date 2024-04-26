package com.daniel;

import javax.swing.*;

/**
 * Hello world!
 * // https://www.youtube.com/watch?v=Y62MJny9LHg
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int boardWidth=600;
        int boardHeight=boardWidth;
        JFrame frame=new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null); //face fereastra sa se deschida in centrul ecranului
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame=new SnakeGame(boardWidth,boardHeight);
        frame.add(snakeGame);
    }
}
