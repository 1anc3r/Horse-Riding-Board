import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel
{
    private JLabel l[][];
    final int D = 8;
    final int M = 12;
    final int b[][] = new int[M][M];
    private int px[] = new int[64];
    private int py[] = new int[64];
    final int nx[] = {-2, -1, 1, 2, 2, 1, -1, -2};
    final int ny[] = {1, 2, 2, 1, -1, -2, -2, -1};
    int ox,oy;

    public void initBoard(){
        int i, j;
        for (i = 0; i < 2; i++ ){
            for (j = 0; j < M; j++){
                b[i][j]=1;
                b[M-i-1][j]=1;
                b[j][i]=1;
                b[j][M-i-1]=1;
            }
        }
    }

    public void Path(int x, int y){
        px[0] = x;
        py[0] = y;
        x = x + 1;
        y = y + 1;
        int i;
        int pedometer = 1;
        int direction[] = new int[D];
        int nsd=8,nsx=0,nsy=0;
        b[x][y] = pedometer;
        for (pedometer = 2; pedometer < 65; pedometer++) {
            nsd = 8;
            for (i = 0; i < D; i++) {
                if (b[x+nx[i]][y+ny[i]] == 0) {
                    direction[i] = Step(x+nx[i],y+ny[i]);
                    if (direction[i] < nsd) {
                        nsd = direction[i];
                        nsx = x+nx[i];
                        nsy = y+ny[i];
                    }
                }
            }
            x = nsx;
            y = nsy;
            b[x][y] = pedometer;
            px[pedometer-1] = x-1;
            py[pedometer-1] = y-1;
        }
    }

    public int Step(int x, int y){
        int count = 0;
        for (int i = 0; i < D; i++) {
            if (b[x+nx[i]][y+ny[i]] == 0) {
                count++;
            }
        }
        return count;
    }

    public void initGUI(int grids,int gridsize)
    {
        l = new JLabel[grids][grids];
        for(int i=0; i<grids; i++)
        {
            for(int j=0; j<grids; j++)
            {
                l[i][j] = new JLabel();
                l[i][j].setSize(gridsize,gridsize);
                l[i][j].setLocation(i*gridsize,j*gridsize);
                l[i][j].setFont(new java.awt.Font("Dialog", 1, 25));
                l[i][j].setForeground(Color.gray);
                l[i][j].setHorizontalTextPosition(JLabel.CENTER);
                l[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                l[i][j].addMouseListener(new MouseAdapter(i,j)); ;
                l[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                if((i+j)%2==0)
                {
                    l[i][j].setBackground(Color.black);
                    l[i][j].setOpaque(true);
                }
                add(l[i][j]);
            }
        }
    }

    public class MouseAdapter implements MouseListener{
        int x,y;
        public MouseAdapter(int i,int j){
            x = j;
            y = i;
        }
        public void mouseClicked(MouseEvent e) {
            Path(x+1,y+1);
            new Jump(l,px,py).start();
        }
        public void mouseEntered(MouseEvent e) {

        }
        public void mouseExited(MouseEvent e) {

        }
        public void mousePressed(MouseEvent e) {

        }
        public void mouseReleased(MouseEvent e) {

        }
    }

    public Board(){
        super(null);
        initGUI(8,80);
        initBoard();
    }

    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setSize(658,677);
        f.setLocationRelativeTo(null);
        f.add(new Board());
        f.setVisible(true);
    }
}
