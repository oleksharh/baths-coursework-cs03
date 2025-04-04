package wars;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/02/12
 */
public class GameGUI 
{
    private BATHS gp = new SeaBattles("Fred");
    private JFrame myFrame = new JFrame("Game GUI");
    private Container contentPane = myFrame.getContentPane();
    private JTextArea listing = new JTextArea();
    private JLabel codeLabel = new JLabel ();
    private JButton fightBtn = new JButton("Fight Encounter");
    private JButton viewBtn = new JButton("View State");
    private JButton clearBtn = new JButton("Clear");
    private JButton quitBtn = new JButton("Quit");
    private JPanel eastPanel = new JPanel();

    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.CENTER);
        listing.setVisible(false);
        contentPane.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));

        eastPanel.add(viewBtn);
        viewBtn.addActionListener(new ViewStateHandler());
        eastPanel.add(fightBtn);
        fightBtn.addActionListener(new FightHandler());
        eastPanel.add(clearBtn);
        clearBtn.addActionListener(new ClearHandler());
        eastPanel.add(quitBtn);
        quitBtn.addActionListener(new QuitHandler());

        viewBtn.setVisible(true);
        fightBtn.setVisible(true);
        clearBtn.setVisible(true);
        quitBtn.setVisible(true);

        // building is done - arrange the components and show
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        myFrame.setMinimumSize(new Dimension(400, 600));
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the Ships menu
        JMenu shipMenu = new JMenu("Ships");
        menubar.add(shipMenu);

        JMenuItem listShipItem = new JMenuItem("List reserve Ships");
        listShipItem.addActionListener(new ListFleetHandler());
        shipMenu.add(listShipItem);

        JMenuItem listSquadronItem = new JMenuItem("List squadron");
        listSquadronItem.addActionListener(new ListSquadronHandler());
        shipMenu.add(listSquadronItem);

        JMenuItem viewShipItem = new JMenuItem("View a Ship");
        viewShipItem.addActionListener(new ViewShipHandler());
        shipMenu.add(viewShipItem);

        JMenuItem commissionShipItem = new JMenuItem("Commission a Ship");
        commissionShipItem.addActionListener(new CommissionShipHandler());
        shipMenu.add(commissionShipItem);

        JMenuItem decommission = new JMenuItem("Decommission Ship");
        decommission.addActionListener(new DecommissionHandler());
        shipMenu.add(decommission);
    }

    
    private class ListFleetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setVisible(true);
            String xx = gp.getReserveFleet();
            listing.setText(xx);
            
        }
    }

    private class ListSquadronHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String xx = gp.getSquadron();
            listing.setText(xx);
        }
    }

    private class ViewShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String inputValue = JOptionPane.showInputDialog("Please provide ship name to get details: ");
            String result = gp.getShipDetails(inputValue);
            JOptionPane.showMessageDialog(myFrame, result);
        }
    }

    private class CommissionShipHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String inputValue = JOptionPane.showInputDialog("Please provide ship name to commission: ");
            String result = gp.commissionShip(inputValue);
            JOptionPane.showMessageDialog(myFrame, result);
        }
    }
    
    private class ClearHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            listing.setText("");
            listing.setVisible(false);            
        }
    }
    
    private class DecommissionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            String result = "";
            String inputValue = JOptionPane.showInputDialog("Please provide ship name to decommission: ");
            
            if(gp.isInSquadron(inputValue)) 
            {
                gp.decommissionShip(inputValue);
                result = inputValue + " is decommissioned";
            }
            else
            {
                result = inputValue + " not in fleet";
            }
            JOptionPane.showMessageDialog(myFrame,result);    
        }
    }

    private class ViewStateHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String state = gp.toString();
            listing.setText(state);
        }
    }

    private class FightHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            listing.setVisible(true);
            String encounters = gp.getAllEncounters();
            listing.setText(encounters);
            int inputValue = Integer.parseInt(JOptionPane.showInputDialog("Please provide encounter number to fight: "));
            String result = gp.fightEncounter(inputValue);
            JOptionPane.showMessageDialog(myFrame, result);
        }
    }
   
    private class ClearButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            listing.setVisible(false);
            clearBtn.setVisible(false);
        }
    }

    private class QuitHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameGUI();
            }
        });
    }
}
   
