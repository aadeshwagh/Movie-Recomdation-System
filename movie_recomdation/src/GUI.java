import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    JFrame root;
    JMenuItem registered_user, new_user, gener_based;
    JButton giveratings;
    JTextField P_T;
    JMenuBar menu_bar;

    public void main() {
        // Menu Items
        registered_user = new JMenuItem("Registered User");
        gener_based = new JMenuItem("Gener ");
        new_user = new JMenuItem("new user");
        registered_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String g = (String) JOptionPane.showInputDialog("User id");
                registereduser r = new registereduser(g);
                r.getRecomdatedmovies(1);
                r.pack();
                root.dispose();

            }
        });

        gener_based.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                String u = (String) JOptionPane.showInputDialog("Enter user");
                Generebased gb = new Generebased(g, Integer.parseInt(u));
                gb.rec(0, 10);
                gb.pack();
                root.dispose();

            }
        });
        new_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                int user_id = u.addNewUser();
                JOptionPane.showMessageDialog(root, "User Id :" + user_id);
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                Generebased gb = new Generebased(g, user_id);
                gb.rec(0, 10);
                gb.pack();
                root.dispose();

            }
        });

        // Menu
        JMenu Options = new JMenu("Options");
        Options.add(registered_user);
        Options.add(new_user);
        Options.add(gener_based);

        // Menu bar
        menu_bar = new JMenuBar();
        menu_bar.add(Options);

        // Background image
        JLabel background = new JLabel(new ImageIcon("background2.jpg"));

        // Main window
        root = new JFrame("Movie Recommendation System");
        root.add(background);
        root.setJMenuBar(menu_bar);
        root.setVisible(true);
        root.setExtendedState(JFrame.MAXIMIZED_BOTH);
        root.setLayout(new GridLayout());
        root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
