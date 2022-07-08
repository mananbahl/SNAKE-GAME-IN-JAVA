import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.util.Random;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;





public class Gameplay extends JPanel implements ActionListener, KeyListener {

	private ImageIcon titleImage;
	
	private ImageIcon downmouthsnake=new ImageIcon("downmouthsnake.png");
	private ImageIcon leftmouthsnake=new ImageIcon("leftmouthsnake.png");
	private ImageIcon rightmouthsnake=new ImageIcon("rightmouthsnake.png");
	private ImageIcon upmouthsnake=new ImageIcon("upmouthsnake.png");
	private ImageIcon snakeimagesnake=new ImageIcon("snakeimagesnake.png");
	private ImageIcon pointimage=new ImageIcon("point.png");
	
	private int[] snakexlength=new int[750];
	private int[] snakeylength=new int[750];
	
	private int[] xEnemy={50,100,150,200,250,300,350,400,450,500,550,600,650,700,750,800};
	private int[] yEnemy={100,150,200,250,300,350,400,450};
	
	private int lengthofsnake=3;
	
	private int x=0;
	private int y=0;
	private Random random=new Random();
	
	private boolean left=false;
	private boolean right=true;
	private boolean up=false;
	private boolean down=false;
	
	private boolean gameOver=false;
	private int score=0;
	
	Timer timer;
	private int delay=100;
	private int moves=0;
	public Gameplay()
	{
		addKeyListener(this);
		requestFocus();
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		newEnemy();
		timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.RED);
		g.drawRect(24,10,850,55);
		g.fillRect(24,10,850,55);
		
		titleImage=new ImageIcon("snakebymanantitle.png");
		Image img=titleImage.getImage();
		Image newimg=img.getScaledInstance(849,66,java.awt.Image.SCALE_SMOOTH);
		titleImage=new ImageIcon(newimg);
		titleImage.paintIcon(this, g, 25, 8);
		g.setColor(Color.WHITE);
		g.drawRect(0,80,887,580);
		g.setColor(Color.BLACK);
		g.fillRect(1,81,886,579);
		
		if(moves==0)
		{
			snakexlength[0]=200;
			snakexlength[1]=175;
			snakexlength[2]=150;
			
			snakeylength[0]=100;
			snakeylength[1]=100;
			snakeylength[2]=100;
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",Font.PLAIN,40));
			g.drawString("Press Arrow keys to start !!",223,250);
		}
		if(left) leftmouthsnake.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		if(right) rightmouthsnake.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		if(down) downmouthsnake.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		if(up) upmouthsnake.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int i=0;i<lengthofsnake;i++) snakeimagesnake.paintIcon(this, g, snakexlength[i], snakeylength[i]);
		
		pointimage.paintIcon(this, g, x, y);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,20));
		g.drawString("Score : "+score,750,40);
		
		if(gameOver)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",Font.PLAIN,20));
			g.drawString("LMAO....XD",250,250);
			g.drawString("Press SPACE to restart",340,340);
		}
		g.dispose();	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for(int i=lengthofsnake-1;i>0;i--)
		{
			snakexlength[i]=snakexlength[i-1];
			snakeylength[i]=snakeylength[i-1];
		}
		if(left) snakexlength[0]=snakexlength[0]-25;
		if(right) snakexlength[0]=snakexlength[0]+25;
		if(down) snakeylength[0]=snakeylength[0]+25;
		if(up) snakeylength[0]=snakeylength[0]-25;
		
		if(snakexlength[0]>870) snakexlength[0]=1;
		if(snakexlength[0]<1) snakexlength[0]=870;
		if(snakeylength[0]>579) snakeylength[0]=81;
		if(snakeylength[0]<81) snakeylength[0]=579;
		
		if((snakexlength[0]>=x-20 && snakexlength[0]<=x+20) && (snakeylength[0]>=y-20 && snakeylength[0]<=y+20))
		{
			lengthofsnake++;
			score++;
			newEnemy();
		}
		
		for(int i=1;i<lengthofsnake-1;i++)
		{
			if(snakexlength[0]==snakexlength[i] && snakeylength[0]==snakeylength[i])
			{
				gameOver=true;
				timer.stop();
			}
			
		}
		repaint();
	}
	
	
	public void newEnemy()
	{
		x=xEnemy[random.nextInt(16)];
		y=yEnemy[random.nextInt(8)];
		for(int i=1;i<lengthofsnake-1;i++)
		{
			if(snakexlength[i]==x && snakeylength[i]==y) newEnemy();
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE && gameOver==true)
		{
			gameOver=false;
			moves=0;
			score=0;
			lengthofsnake=3;
			timer.start();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right))
		{
			left=true;
			right=false;
			up=false;
			down=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left))
		{
			left=false;
			right=true;
			up=false;
			down=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP && (!down))
		{
			left=false;
			right=false;
			up=true;
			down=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up))
		{
			left=false;
			right=false;
			up=false;
			down=true;
			moves++;
		}
		
	}
		
	public void keyReleased(KeyEvent e)
	{
		
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
}
