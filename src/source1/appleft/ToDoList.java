package source1.appleft;

import af.swing.AfPanel;
import af.swing.layout.AfXLayout;
import source1.Display;
import source1.appright.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ToDoList extends AfPanel {
    private JLabel listNameL;
    private JLabel cardNumL;
    private ArrayList<Card> Cards;
    private Display display;
    private JPopupMenu popup;
    private Color color;
    public ToDoList(Display display) {
        this.display = display;
        listNameL = new JLabel("新規リスト");
        listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/orange.png"));
        setLayout(new AfXLayout());
        Cards = new ArrayList<>();
        padding(0,4,0,1);
        add(listNameL,"1w");
        cardNumL = new JLabel("");
        cardNumL.setText(String.valueOf(Cards.size()));
        add(cardNumL,"20px");
        popup = new JPopupMenu();
        JMenuItem changeName = new JMenuItem("名前を変更");
        JMenuItem delete = new JMenuItem("削除");
        changeName.setActionCommand("changeName");
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameChanged();

            }
        });
        delete.setActionCommand("delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ToDoList.this.setVisible(false);
                if(ToDoList.this.display.getSelectedList()==ToDoList.this){
                    for(Card card:ToDoList.this.Cards){
                        card.setVisible(false);
                    }
                    ToDoList.this.display.setSelectedList(null);
                }
                ToDoList.this.display.getTodolist().remove(ToDoList.this);
                ToDoList.this.display.search_();
            }
        });

        color = new Color(255,143,31);

        JMenu changeColor = new JMenu("色を変更");
        JMenuItem green = new JMenuItem();
        green.setBackground(new Color(31,31,255));
        JMenuItem zise = new JMenuItem();
        zise.setBackground(new Color(143,31,255));
        JMenuItem pink2 = new JMenuItem();
        pink2.setBackground(new Color(255,31,143));
        JMenuItem orange = new JMenuItem();
        orange.setBackground(new Color(255,143,31));
        JMenuItem blue = new JMenuItem();
        blue.setBackground(new Color(31,255,143));
        JMenuItem lightGreen = new JMenuItem();
        lightGreen.setBackground(new Color(31,255,255));

        changeColor.add(green);
        changeColor.add(zise);
        changeColor.add(pink2);
        changeColor.add(orange);
        changeColor.add(blue);
        changeColor.add(lightGreen);
        ArrayList<JMenuItem> iro =  new ArrayList<>();
        iro.add(green);
        iro.add(zise);
        iro.add(pink2);
        iro.add(orange);
        iro.add(blue);
        iro.add(lightGreen);
        for (JMenuItem mi:iro){
            mi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    color = mi.getBackground();
                    if(ToDoList.this.display.getSelectedList() == ToDoList.this){
                        ToDoList.this.display.getListNameL().setForeground(color);
                    }
                    seticon();

                }

            });
        }


        popup.add(changeName);
        popup.add(delete);
        popup.add(changeColor);



        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3){
                    popup.show(e.getComponent(),e.getX(),e.getY());
                }
                if(e.getClickCount()==2){
                    nameChanged();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1){
                    boolean isChanged = false;
                    ToDoList.this.display.clearShowCards();
                    if(ToDoList.this != ToDoList.this.display.getSelectedList()){
                        isChanged = true;
                    }
                    if(isChanged){
                        if(ToDoList.this.display.getSelectedList()!=null){
                            for(Card card:ToDoList.this.display.getSelectedList().getCards()){
                                card.setVisible(false);
                            }
                            ToDoList.this.display.getSelectedList().setBgColor(Color.LIGHT_GRAY);
                            ToDoList.this.display.getSelectedList().getListNameL().setForeground(Color.BLACK);
                            ToDoList.this.display.getSelectedList().getCardNumL().setForeground(Color.BLACK);
                        }
                        ToDoList.this.display.setSelectedList(ToDoList.this);
                        for(Card card:Cards){
                            card.setVisible(true);
                        }
                        ToDoList.this.display.changeShowPanel("list");
                        setBgColor(new Color(0x1296db));
                        listNameL.setForeground(Color.WHITE);
                        cardNumL.setForeground(Color.WHITE);

                        ToDoList.this.display.getListNameL().setText(ToDoList.this.listNameL.getText());
                        ToDoList.this.display.getListNameL().setForeground(color);
                    }
                }


            }
        });





    }

    private void nameChanged(){
        JOptionPane a = new JOptionPane();
        String input = JOptionPane.showInputDialog(this,"リストネーム:",getListNameL().getText());
        if(input != null){
            if(!input.equals("")){
                listNameL.setText(input);
                display.getListNameL().setText(input);
            }else{
                JOptionPane.showMessageDialog(this,"リストネームを入力してください.");
            }
        }
    }

    public void seticon(){
        if(color.getRed()==31 && color.getGreen() == 31 && color.getBlue()==255){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/green.png"));
        }
        if(color.getRed()==143 && color.getGreen() == 31 && color.getBlue()==255){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/zise.png"));
        }
        if(color.getRed()==255 && color.getGreen() == 31 && color.getBlue()==143){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/pink2.png"));
        }
        if(color.getRed()==255 && color.getGreen() == 143 && color.getBlue()==31){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/orange.png"));

        }
        if(color.getRed()==31 && color.getGreen() == 255 && color.getBlue()==143){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/blue.png"));
        }
        if(color.getRed()==31 && color.getGreen() == 255 && color.getBlue()==255){
            listNameL.setIcon(new ImageIcon("/Users/sunyuqiang/IdeaProjects/untitled9/src/images/lightGreen.png"));
        }
    }

    public JLabel getListNameL() {
        return listNameL;
    }

    public JLabel getCardNumL() {
        return cardNumL;
    }

    public ArrayList<Card> getCards(){
        return Cards;
    }

    public Display getDisplay(){
        return display;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }


}
