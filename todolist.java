import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

//import java.awt.Dimension;
//import java.awt.FlowLayout;

import javax.swing.*;


public class todolist extends JFrame implements ActionListener{
 JButton addbutton,delbutton;
 JTextField textField;
 HashSet<Integer> taskdone;
 DefaultListModel<String> listModel;
 JList<String> taskList;

todolist(){
    /*/
    this.setTitle("DO IT");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(400,400);
    this.setLayout(null);
    this.add(button);
    //this.setPreferredSize(new Dimension(400,400));
    */
    textField = new JTextField();
    textField.setVisible(true);
    textField.setAlignmentX(CENTER_ALIGNMENT);
    //textField.setSize(240,40);
    textField.setPreferredSize(new Dimension(240,40));

    listModel = new DefaultListModel<>();
    //taskList = new JList<>(listModel);
    taskdone = new HashSet<>();


 //Here i am not deleting the tasks. I am just clicking the task to set it done or undone
    taskList = new JList<>(listModel);
    taskList.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e){
            if(e.getClickCount()==2){
                int index = taskList.locationToIndex(e.getPoint());
                if(taskdone.contains(index)){
                    taskdone.remove(index);
                }
                else{
                    taskdone.add(index);
                }
                taskList.repaint(); //It updates the screen with striked grey text and unstriked black text accordingly.
            }
        }
    });


    taskList.setCellRenderer(new DefaultListCellRenderer(){
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(taskdone.contains(index)){
                label.setText("<html><strike>"+ value + "</strike></html>");
                label.setForeground(Color.RED);
            }
            else{
                label.setText(value.toString());
                label.setForeground(Color.BLACK);
            }
            return label;
        }
    });



    delbutton = new JButton();
    delbutton.setText("delete");
    delbutton.setVisible(true);
    //delbutton.setSize(45,40);
    delbutton.setPreferredSize(new Dimension(80,40));
    delbutton.setFocusable(false);
    delbutton.setLayout(new FlowLayout());
    delbutton.addActionListener(this);
    


    addbutton = new JButton();
    addbutton.setText("add");
    addbutton.setVisible(true);
    //addbutton.setSize(45,40);
    addbutton.setPreferredSize(new Dimension(80,40));
    addbutton.setFocusable(false);
    addbutton.setLayout(new FlowLayout());
    addbutton.addActionListener(this);

    this.setTitle("DO IT");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(500,400);
    this.setLayout(new FlowLayout());
    this.add(addbutton);
    this.add(delbutton);
    this.add(textField);
    JScrollPane scrollPane = new JScrollPane(taskList);
    scrollPane.setPreferredSize(new Dimension(400, 200)); // width=400, height=200
    this.add(scrollPane);

    this.setPreferredSize(new Dimension(400,400));
}
@Override
public void actionPerformed(ActionEvent e){
    if (e.getSource()== addbutton){
        String task = textField.getText().trim();
    if(!task.isEmpty())
    {
        listModel.addElement(task);
        textField.setText("");
    }
    }
    if(e.getSource() == delbutton){
        int index = taskList.getSelectedIndex();
        if(index != -1){
            listModel.remove(index);
            taskdone.remove(index);
        }
    }

}

public static void main (String args[]){
    new todolist();
}
}