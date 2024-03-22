import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;
class DrawPanel extends JPanel implements MouseListener
{
    private ArrayList<Card> hand;
    private Rectangle button;

    public DrawPanel()
    {
        button = new Rectangle(147, 248, 160, 26);
        this.addMouseListener(this);
        hand = Card.buildHand();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int x = 124;
        int y = 10;
        for(int i = 0; i < hand.size(); i++)
        {
            Card c = hand.get(i);
            if(c.getHighlight())
            {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            if((i % 3) == 2)
            {
                x = 124;
                y = y + c.getImage().getHeight() + 10;
            }
            else
            {
                x = x + c.getImage().getWidth() + 10;
            }
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("REPLACE CARDS", 150, 268);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawRect(147, 284, 160, 26);
        g.drawString("PLAY AGAIN", 170, 304);
    }

    public void mousePressed(MouseEvent e)
    {
        Point clicked = e.getPoint();
        if(e.getButton() == 1)
        {
            if(button.contains(clicked))
            {
                hand = Card.buildHand();
            }
            for(int i = 0; i < hand.size(); i++)
            {
                Rectangle box = hand.get(i).getCardBox();
                if(box.contains(clicked))
                {
                    hand.get(i).flipCard();
                }
            }
        }
        if(e.getButton() == 3)
        {
            for(int i = 0; i < hand.size(); i++)
            {
                Rectangle box = hand.get(i).getCardBox();
                if(box.contains(clicked))
                {
                    hand.get(i).flipHighlight();
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}