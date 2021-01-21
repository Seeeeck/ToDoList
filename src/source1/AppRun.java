package source1;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * アプリを起動させるClass
 */
public class AppRun {
    private static void createGUI(){
        Display display;
        Display obj = Objectios.readToDoList();
        if(obj == null){
            display = new Display("ToDoList");
        }else {
            display = obj;
        }
        display.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Objectios.writeToDoList(display);
            }
        });



        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(800,640);

        display.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
}
