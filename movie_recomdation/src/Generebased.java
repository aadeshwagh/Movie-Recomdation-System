import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Generebased {
    JFrame genere;
    JMenuItem registered_user, new_user, gener_based;
    JButton giveratings;
    JMenuBar menu_bar;
    String s;
    int user_id;
    List<List<String>> res;

    public Generebased(String s, int user_id) {
        this.s = s;
        this.user_id = user_id;

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
                genere.dispose();

            }
        });
        gener_based.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                Generebased gb = new Generebased(g, user_id);
                gb.rec(0, 10);
                gb.pack();
                genere.dispose();

            }
        });
        new_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                int user_id = u.addNewUser();
                JOptionPane.showMessageDialog(genere, "User Id :" + user_id);
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                Generebased gb = new Generebased(g, user_id);
                gb.rec(0, 10);
                gb.pack();
                genere.dispose();

            }
        });
        // new_user.addActionListener(this);
        // gener_based.addActionListener(this);
        // registered_user.addActionListener(this);

        // Menu
        JMenu Options = new JMenu("Options");
        Options.add(registered_user);
        Options.add(new_user);
        Options.add(gener_based);

        JButton next = new JButton("next");
        next.setFocusable(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Generebased g = new Generebased(s, user_id);
                if (res.size() < 20) {
                    g.rec(11, res.size());
                } else {
                    g.rec(11, 20);
                }

                g.pack();
                genere.dispose();

            }
        });

        // Menu bar
        menu_bar = new JMenuBar();
        menu_bar.add(Options);
        menu_bar.add(next);

        // Background image
        // JLabel background = new JLabel(new ImageIcon("254361.jpg"));

        // Main window
        genere = new JFrame("Genere based ");
        // genere.add(background);
        genere.setJMenuBar(menu_bar);
        genere.setExtendedState(JFrame.MAXIMIZED_BOTH);
        genere.getContentPane().setLayout(new GridLayout(3, 5, 5, 5));
        genere.setExtendedState(JFrame.MAXIMIZED_BOTH);
        genere.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    void pack() {
        genere.setVisible(true);
    }

    void rec(int x, int y) {

        registereduser u = new registereduser(user_id + "");
        BufferedImage inputImage = null;
        Recomdation re = new Recomdation();
        res = new ArrayList<>();
        for (List<String> s : re.generBasedRecomdations(s)) {
            res.add(s);
        }
        for (int i = x; i <= y; i++) {
            String name = res.get(i).get(0);
            int movie_id = Integer.parseInt(res.get(i).get(1));
            int mdid = Integer.parseInt(res.get(i).get(2));
            Fetchimdbdetails fd = new Fetchimdbdetails();
            try {
                inputImage = ImageIO.read(new File(movie_id + ".jpg"));

            } catch (Exception e) {
                String[] arr = fd.fetchMovieDetails(mdid);
                fd.downloadPoster(arr[1], movie_id);
                try {
                    inputImage = ImageIO.read(new File(movie_id + ".jpg"));
                } catch (Exception o) {
                    // TODO: handle exception
                    o.getCause();

                }
            }
            u.addmovieui(name, inputImage, genere);
        }
    }

}
