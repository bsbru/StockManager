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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ajs
 */
public class MealScreen extends JFrame implements ActionListener{
    
    JButton doneButton;
    JTextField dishName;
    JLabel dishNameLabel;
    AdminScreen as;
    Boolean add;
    public MealScreen(AdminScreen as, Boolean add){
        super();
        this.setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.as = as;
        this.add = add;
        doneButton = new JButton("Done");
        doneButton.addActionListener(this);
        dishName = new JTextField(30); //will 20 chars be enough?
        dishName.addActionListener(this);
        dishNameLabel = new JLabel("Dish Name");
        if(!add){
            dishName.setText(as.getDishName());
        }
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.BOTH;
        getContentPane().add(dishName, c);
        c.gridx++;
        getContentPane().add(dishNameLabel, c);
        c.gridx--;
        c.gridy++;
        getContentPane().add(doneButton, c);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doneButton | e.getSource() == dishName){
            this.setVisible(false);
            if (add){
                as.addListItem(dishName.getText());
            }else{
                as.replaceListItem(dishName.getText());
            }
            as.setVisible(true);
            this.dispose();
        }
    }

}

