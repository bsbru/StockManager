/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanager;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Janie
 */
public class AdminScreen extends JFrame implements ActionListener{
    
    private JList list;
    private DefaultListModel listModel;
    private static final String addMealString = "+ Add Meal";
    private static final String removeMealString = "- Remove Meal";
    private static final String editMealString = "# Edit Meal";
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JTextField mealName;
    JScrollPane listScrollPane;
    
    public static void main(String args[]){
        new AdminScreen().setVisible(true);
    }
    
    public AdminScreen(){
        super();
        initComponents();
    }
    public void initComponents(){
        
        //set up just some random examples to be in the list
        listModel = new DefaultListModel();
        listModel.addElement("Meal 1");
        listModel.addElement("Meal 2");
        listModel.addElement("Meal 3");
        
        //set up list and add is to a scroller pane
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(new listSelectionListener());
        list.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(list);
        
        addButton = new JButton(addMealString);
        removeButton = new JButton(removeMealString);
        editButton = new JButton(editMealString);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        editButton.addActionListener(this);
        
        this.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        
        //set up the layout
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        getContentPane().setBackground(Color.white);
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 4;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        getContentPane().add(listScrollPane, c);
        //c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 2;
        getContentPane().add(removeButton,c);
        c.gridy++;
        getContentPane().add(editButton, c);
        c.gridx = 0;
        c.gridy = 4;
        getContentPane().add(addButton, c);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    public void addListItem(String itemName){
        listModel.addElement(itemName);
        pack();
    }
    public void replaceListItem(String itemName){
        int index = list.getSelectedIndex();
        listModel.remove(index);
        listModel.insertElementAt(itemName, index);
        pack();
    }
    
    public String getDishName(){
        String name = (String) list.getSelectedValue();
        //int index = list.getSelectedIndex();
        return name;
    }

    public void actionPerformed(ActionEvent e) {
        //what to do when stuff is done
        //throw new UnsupportedOperationException("Not supported yet.");
        if (e.getSource() == addButton) {
            this.setVisible(false);
            MealScreen mealScreen = new MealScreen(this, true);
            mealScreen.setVisible(true);
            repaint();
        }else if (e.getSource() == removeButton){
            int index = list.getSelectedIndex();
            listModel.remove(index);
            int size = listModel.getSize();
            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);
                editButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            repaint();
        }else if (e.getSource() == editButton){
            this.setVisible(false);
            MealScreen mealScreen = new MealScreen(this, false);
            mealScreen.setVisible(true);
            repaint();
        }
    }
    
        private class listSelectionListener implements ListSelectionListener {

        public listSelectionListener() {
        }

        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {

                if (list.getSelectedIndex() == -1) {
                //No selection, disable button.
                    removeButton.setEnabled(false);
                    editButton.setEnabled(false);
                } else {
                //Selection, enable button.
                    removeButton.setEnabled(true);
                    editButton.setEnabled(true);
                }
            }
        }
        
    }
    
}
