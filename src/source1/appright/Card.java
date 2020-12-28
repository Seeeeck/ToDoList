package source1.appright;

import af.swing.AfPanel;
import af.swing.layout.AfXLayout;
import source1.Objectios;
import source1.appleft.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Card extends AfPanel {
    private JTextField titleT;
    private JTextArea memoT;
    private AfPanel start;
    private JScrollPane end;
    private DelCardButton delCardButton;
    private ToDoList toDoList;
    public Card(ToDoList toDoList){

        this.toDoList = toDoList;
        titleT = new JTextField();
        memoT = new JTextArea("メモ");
        start = new AfPanel();
        end = new JScrollPane(memoT);
        delCardButton = new DelCardButton();
        preferredHeight(70);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLACK));
        padding(2,1,2,3);
        start.preferredHeight(24);
        add(start,BorderLayout.PAGE_START);
        add(end,BorderLayout.CENTER);

        //start
        start.setLayout(new AfXLayout());
        start.padding(0,0,2,0);
        titleT.setFont(new Font("Menlo", Font.PLAIN,15));
        titleT.setOpaque(false);
        titleT.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(0x1296db)));

        start.add(titleT,"1w");
        start.add(delCardButton,"16px");

        //end

        //end.setLayout(new AfXLayout());
        //end.add(memoT,"96%");
        memoT.setBorder(null);

        memoT.setFont(new Font("Menlo", Font.PLAIN,12));
        memoT.setForeground(Color.GRAY);
        memoT.setLineWrap(true);

        memoT.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Card.this.memoT.getText().equals("メモ")){
                    Card.this.memoT.setText("");
                }
            }
        });

        delCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                for(Card card:Card.this.toDoList.getCards()){
                    if(Card.this.equals(card)){
                        Card.this.toDoList.getCards().remove(card);
                        break;
                    }
                }
                Card.this.toDoList.getCardNumL().setText(String.valueOf((Card.this.toDoList.getCards().size())));

            }
        });





    }

    public JTextField getTitleT() {
        return titleT;
    }

    public JTextArea getMemoT() {
        return memoT;
    }
}
