package source1.appleft;

import javax.swing.*;
import java.awt.*;

public class DelListButton extends JButton {
    public DelListButton(){
        Icon icon = new ImageIcon("/Users/sunyuqiang/IdeaProjects/ToDoList/src/images/shanchu.png");
        setIcon(icon);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(new Color(0x1296db)));
    }
}
