import java.io.*;
import javax.swing.*;

class Jump extends Thread{
    private JLabel l[][];
    private int px[];
    private int py[];
    public Jump(JLabel l[][],int px[],int py[]){
        this.l = l;
        this.px = px;
        this.py = py;
    }
    public void run() {
        File pic=new File("horse.png");
        Icon icon=new ImageIcon(pic.toString());
        try{
            for (int i=1; i<65; i++) {
                l[py[i-1]-1][px[i-1]-1].setIcon(icon);
                Thread.sleep(1000);
                l[py[i-1]-1][px[i-1]-1].setText(""+i);
                l[py[i-1]-1][px[i-1]-1].setIcon(null);
            }
        }
        catch(Exception h){
            h.printStackTrace();
        }
    }
}
