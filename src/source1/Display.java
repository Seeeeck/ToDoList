package source1;

import af.swing.AfPanel;
import af.swing.layout.AfXLayout;
import af.swing.layout.AfYLayout;
import source1.appleft.AddListButton;
import source1.appleft.ToDoList;
import source1.appright.AddCardButton;
import source1.appright.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Appのwindowを作成するClass
 */
public class Display extends JFrame {
    private AfPanel root;
    private AfPanel left1, right1,rightStart2,rightEnd2,
    leftStart2,leftCenter2,leftEnd2;
    private JLabel listNameL;
    private JButton addCardB,addListB;
    private ArrayList<ToDoList> todolist;
    private ArrayList<Card> showCards;
    private ToDoList selectedList;

    public Display(String title){
        super(title);
        init();

    }

    private void init(){
        todolist = new ArrayList<>();
        selectedList = null;
        //root
        root = new AfPanel();
        setContentPane(root);
        root.setLayout(new AfXLayout(0));
        //1
        left1 = new AfPanel();
        right1 = new AfPanel();
        right1.setBgColor(Color.white);
        root.add(left1,"225px");
        root.add(right1,"1w");
        left1.setLayout(new BorderLayout());
        left1.setBgColor(Color.LIGHT_GRAY);
        right1.setLayout(new BorderLayout());
        //2_left
        leftStart2 = new AfPanel();
        leftCenter2 = new AfPanel();
        leftEnd2 = new AfPanel();
        leftStart2.padding(3,8,3,0);
        leftStart2.preferredHeight(40);
        leftCenter2.padding(2,8,0,0);
        leftEnd2.padding(4,0,2,0);
        leftEnd2.preferredHeight(40);
        leftEnd2.setBgColor(Color.LIGHT_GRAY);
        leftEnd2.setOpaque(true);
        left1.add(leftStart2,BorderLayout.PAGE_START);
        left1.add(leftCenter2,BorderLayout.CENTER);
        left1.add(leftEnd2,BorderLayout.PAGE_END);
        //2_left Button and Label
        addListB = new AddListButton();
        leftEnd2.setLayout(new AfXLayout());
        leftEnd2.add(addListB,"120px");
        addListB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addList();
            }
        });
        leftCenter2.setLayout(new AfYLayout());
        JLabel myList = new JLabel("マイリスト");
        myList.setFont(new Font("Menlo",1,14));
        leftCenter2.add(myList);


        //2_right
        rightStart2 = new AfPanel();
        rightEnd2 = new AfPanel();
        rightStart2.preferredHeight(40);
        rightEnd2.padding(10,13,0,0);
        rightStart2.setLayout(new AfXLayout());
        rightEnd2.setLayout(new AfYLayout(10));
        rightStart2.padding(3,13,0,5);
        right1.add(rightStart2,BorderLayout.PAGE_START);
        right1.add(rightEnd2,BorderLayout.CENTER);
        //2_rightStart  ListName and addCardButton
        listNameL = new JLabel("");
        listNameL.setForeground(new Color(0x1296db));
        listNameL.setFont(new Font(Font.MONOSPACED,Font.BOLD,26));
        rightStart2.add(listNameL,"1w");

        addCardB = new AddCardButton();
        addCardB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
            }
        });
        rightStart2.add(addCardB,"50px");


    }

    public void initListAndCard(){
        for(ToDoList temp:todolist){
            leftCenter2.add(temp,"25px");
            temp.getCardNumL().setText(String.valueOf(temp.getCards().size()));
            for (Card card:temp.getCards()){
                rightEnd2.add(card,"70px");
                card.setVisible(false);
            }
        }

    }




    private void addCard(){
        if(selectedList != null){
            Card card1 = new Card(selectedList);
            selectedList.getCards().add(card1);
            selectedList.getCardNumL().setText(String.valueOf(selectedList.getCards().size()));
            rightEnd2.add(card1,"70px");
        }

    }

    private void addList(){
        ToDoList temp1 = new ToDoList(this);
        todolist.add(temp1);
        leftCenter2.add(temp1,"25px");
        leftCenter2.updateUI();

    }

    public void setShowCards(ArrayList<Card> showCards) {
        this.showCards = showCards;
    }

    public void setSelectedList(ToDoList selectedList) {
        this.selectedList = selectedList;
    }

    public ArrayList<Card> getShowCards() {
        return showCards;
    }

    public ToDoList getSelectedList() {
        return selectedList;
    }

    public ArrayList<ToDoList> getTodolist(){
        return todolist;
    }

    public JLabel getListNameL(){
        return listNameL;
    }
}
