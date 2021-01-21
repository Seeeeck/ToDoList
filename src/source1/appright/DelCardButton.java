package source1.appright;

import javax.swing.*;
import java.awt.*;

public class DelCardButton extends JButton {
    public DelCardButton(){
        Icon icon = new ImageIcon("src/images/shanchu.png");
        setIcon(icon);
        setToolTipText("削除");
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(new Color(0x1296db)));
    }
}
