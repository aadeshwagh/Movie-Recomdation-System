import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class registereduser {

    int user_id;
    JFrame user;
    JButton giveratings;
    int movie_id;
    JMenuItem registered_user, new_user, gener_based;
    JMenuBar menu_bar;

    public registereduser(String s) {

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
                user.dispose();

            }
        });
        gener_based.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                Generebased gb = new Generebased(g, user_id);
                gb.rec(0, 10);
                gb.pack();
                user.dispose();

            }
        });
        new_user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User u = new User();
                int user_id = u.addNewUser();
                JOptionPane.showMessageDialog(user, "User Id :" + user_id);
                String g = (String) JOptionPane.showInputDialog("Enter genre");
                Generebased gb = new Generebased(g, user_id);
                gb.rec(0, 10);
                gb.pack();
                user.dispose();

            }
        });
        JMenu Options = new JMenu("Options");
        Options.add(registered_user);
        Options.add(new_user);
        Options.add(gener_based);

        // Button
        JButton next = new JButton("next");
        next.setFocusable(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                registereduser ru = new registereduser(user_id + "");
                ru.getRecomdatedmovies(2);
                ru.pack();
                user.dispose();

            }
        });

        // Menu bar
        menu_bar = new JMenuBar();
        menu_bar.add(Options);
        menu_bar.add(next);

        user = new JFrame("User");

        user.setJMenuBar(menu_bar);
        user.setExtendedState(JFrame.MAXIMIZED_BOTH);
        user.getContentPane().setLayout(new GridLayout(3, 5, 5, 5));
        user.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.user_id = Integer.parseInt(s);

    }

    void pack() {
        user.setVisible(true);
    }

    void getRecomdatedmovies(int pass) {
        // new_user.addActionListener(this);
        // gener_based.addActionListener(this);
        // registered_user.addActionListener(this);

        // Menu
        // Menu bar
        // Background image
        BufferedImage inputImage = null;
        Recomdation re = new Recomdation();
        int count = 9;
        for (List<String> s : re.RegisteredUserRecomdations(user_id, pass)) {
            if (count > 0) {
                String name = s.get(0);
                movie_id = Integer.parseInt(s.get(1));
                int mdid = Integer.parseInt(s.get(2));
                Fetchimdbdetails fd = new Fetchimdbdetails();
                try {
                    inputImage = ImageIO.read(new File("posters/" + movie_id + ".jpg"));

                } catch (Exception e) {
                    String[] arr = fd.fetchMovieDetails(mdid);
                    fd.downloadPoster(arr[1], movie_id);
                    try {
                        inputImage = ImageIO.read(new File("posters/" + movie_id + ".jpg"));
                    } catch (Exception o) {
                        // TODO: handle exception
                        o.getCause();

                    }
                }

                addmovieui(name, inputImage, user);
                count--;
            } else {
                break;
            }

        }

    }

    void addmovieui(String movie, BufferedImage inputImage, JFrame frame) {
        JPanel p = new JPanel();
        // p.setBorder(BorderFactory.createTitledBorder("demo"));
        if (inputImage == null) {
            JLabel name = new JLabel(movie);
            name.setFont(new Font("Verdana", Font.PLAIN, 17));
            p.add(name);
            JButton rate = new JButton("Rate");
            rate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String rat = (String) JOptionPane.showInputDialog("Give Rating 1-5");
                    rate(Integer.parseInt(rat));

                }
            });
            rate.setFocusable(false);
            p.add(rate);
            frame.getContentPane().add(p);
            return;
        }
        Image background = inputImage.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(background));
        // System.out.println(movie);
        p.add(picLabel);

        JButton rate = new JButton("Rate");
        rate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String rat = (String) JOptionPane.showInputDialog("Give Rating 1-5");
                rate(Integer.parseInt(rat));

            }
        });
        rate.setFocusable(false);
        p.add(rate);
        p.setPreferredSize(new Dimension(200, 290));
        p.setMaximumSize(new Dimension(200, 290));

        frame.getContentPane().add(p);

    }

    void rate(int rating) {
        User u = new User();
        u.GiveRating(new User(user_id), new Movie(movie_id), rating);
        JOptionPane.showMessageDialog(user, "Rating stored :");

    }

}
