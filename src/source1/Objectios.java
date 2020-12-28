package source1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import source1.appleft.ToDoList;
import source1.appright.Card;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Objectios {
    private Objectios(){

    }

    public static Display readToDoList() {
        String filename = "todolist.xml";
        File file = new File(filename);
        Display display = new Display("ToDoList");
        ArrayList<ToDoList> t = new ArrayList<>();
        SAXReader sr = new SAXReader();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try{
                Document doc = sr.read(filename);
                Element toDoLists = doc.getRootElement();
                for(Element list:toDoLists.elements()){
                    ToDoList l1 = new ToDoList(display);
                    for(Element temp1:list.elements()){
                        if(temp1.getName().equals("listname")){
                            l1.getListNameL().setText((String) temp1.getData());
                        }else if(temp1.getName().equals("cardlist")){
                            for(Element card:temp1.elements()){
                                Card temp_card = new Card(l1);
                                for(Element temp2:card.elements()){
                                    if(temp2.getName().equals("title")){
                                        temp_card.getTitleT().setText((String)temp2.getData());
                                    }else if(temp2.getName().equals("memo")){
                                        temp_card.getMemoT().setText((String)temp2.getData());
                                    }
                                }
                                l1.getCards().add(temp_card);
                            }
                        }else if(temp1.getName().equals("color")){
                            String[] strs = temp1.getText().split(",");
                            l1.setColor(new Color(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]),Integer.parseInt(strs[2])));
                        }
                    }
                    display.getTodolist().add(l1);
                    l1.seticon();

                }
                display.initListAndCard();

            } catch (DocumentException e) {
                e.printStackTrace();
            }
            return display;


        }
        return null;
    }

    public static void writeToDoList(Display display)  {
        File file = new File("todolist.xml");
        XMLWriter xw = null;
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            OutputFormat of = new OutputFormat("\t",true,"UTF-8");
            xw = new XMLWriter(bos,of);
            Document newDoc = DocumentHelper.createDocument();
            Element toDoLists = newDoc.addElement("todolists");
            for(ToDoList t1:display.getTodolist()){
                Element list = toDoLists.addElement("list");
                list.addElement("listname").setText(t1.getListNameL().getText());
                list.addElement("color").setText(""+t1.getColor().getRed()+","+t1.getColor().getGreen()+","+t1.getColor().getBlue());
                Element cardList = list.addElement("cardlist");
                for(Card card1: t1.getCards()){
                    Element card = cardList.addElement("card");
                    card.addElement("title").setText(card1.getTitleT().getText());
                    card.addElement("memo").setText(card1.getMemoT().getText());
                }
            }
            xw.write(newDoc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (xw != null) {
                    xw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}