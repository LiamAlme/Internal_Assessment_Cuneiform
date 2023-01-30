import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        JButton c=new JButton("click");
        f.add(b);//adding button in JFrame
        f.add(c);

        f.setSize(400,500);//400 width and 500 height
        LayoutManager layout = new GridLayout(2,2);
        f.setLayout(layout);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}