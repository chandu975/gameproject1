package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
    Board game = new Board();
    static Game newGame = new Game();
    static JFrame frame = new JFrame("2048");
    static Color green;
    String gameBoard;

    public Game() {
        this.gameBoard = this.game.toString();
    }

    public static void setUpGUI() {
        frame.addKeyListener(newGame);
        frame.getContentPane().add(newGame);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() != 'w' && e.getKeyCode() != 38) {
            if (e.getKeyChar() != 's' && e.getKeyCode() != 40) {
                if (e.getKeyChar() != 'a' && e.getKeyCode() != 37) {
                    if (e.getKeyChar() != 'd' && e.getKeyCode() != 39) {
                        if (e.getKeyCode() == 10) {
                            this.game = new Board();
                            this.game.spawn();
                            this.game.spawn();
                            frame.repaint();
                        }
                    } else {
                        this.game.right();
                        this.game.spawn();
                        this.gameBoard = this.game.toString();
                        frame.repaint();
                    }
                } else {
                    this.game.left();
                    this.game.spawn();
                    this.gameBoard = this.game.toString();
                    frame.repaint();
                }
            } else {
                this.game.down();
                this.game.spawn();
                this.gameBoard = this.game.toString();
                frame.repaint();
            }
        } else {
            this.game.up();
            this.game.spawn();
            this.gameBoard = this.game.toString();
            frame.repaint();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString("2048", 250, 20);
        g2.drawString("Score: " + this.game.getScore(), 200 - 4 * String.valueOf(this.game.getScore()).length(), 40);
        g2.drawString("Highest Tile: " + this.game.getHighTile(), 280 - 4 * String.valueOf(this.game.getHighTile()).length(), 40);
        g2.drawString("Press 'Enter' to Start", 210, 315);
        g2.drawString("Use 'wasd' or Arrow Keys to move", 180, 335);
        if (this.game.blackOut()) {
            g2.drawString("Press 'Enter' to restart", 200, 355);
        }

        g2.setColor(Color.gray);
        g2.fillRect(140, 50, 250, 250);

        int i;
        int j;
        for(i = 0; i < 4; ++i) {
            for(j = 0; j < 4; ++j) {
                this.drawTiles(g, this.game.board[i][j], j * 60 + 150, i * 60 + 60);
            }
        }

        if (this.game.gameOver()) {
            g2.setColor(Color.gray);
            g2.fillRect(140, 50, 250, 250);

            for(i = 0; i < 4; ++i) {
                for(j = 0; j < 4; ++j) {
                    g2.setColor(Color.RED);
                    g2.fillRoundRect(j * 60 + 150, i * 60 + 60, 50, 50, 5, 5);
                    g2.setColor(Color.black);
                    g.drawString("GAME", j * 60 + 160, i * 60 + 75);
                    g.drawString("OVER", j * 60 + 160, i * 60 + 95);
                }
            }
        }

    }

    public void drawTiles(Graphics g, Tile tile, int x, int y) {
        int tileValue = tile.getValue();
        int length = String.valueOf(tileValue).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.lightGray);
        g2.fillRoundRect(x, y, 50, 50, 5, 5);
        g2.setColor(Color.black);
        if (tileValue > 0) {
            g2.setColor(tile.getColor());
            g2.fillRoundRect(x, y, 50, 50, 5, 5);
            g2.setColor(Color.black);
            g.drawString("" + tileValue, x + 25 - 3 * length, y + 25);
        }

    }

    public static void main(String[] args) {
        setUpGUI();
    }
}

