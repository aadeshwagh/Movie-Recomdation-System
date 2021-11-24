import java.util.HashMap;
import java.io.*;

public class User {
    BufferedReader bf;

    public User(int id) {
        this.id = id;
    }

    public User() {

    }

    private int id;
    private HashMap<Integer, Double> movieRatings;

    public int getId() {
        return id;
    }

    public HashMap<Integer, Double> getMovieRatings() {
        return movieRatings;
    }

    public void setMovieRatings() {
        movieRatings = new HashMap<>();
        try {
            bf = new BufferedReader(new FileReader(new File(("ratings.csv"))));
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String arr[] = line.split(",");
                if (Integer.parseInt(arr[0]) == id) {
                    movieRatings.put(Integer.parseInt(arr[1]), Double.parseDouble(arr[2]));
                }

            }
            bf.close();
        } catch (Exception e) {
            throw new Error("exception in User class setmovierating method");
        }

    }

    int addNewUser() {
        int user_id = 0;
        try {
            bf = new BufferedReader(new FileReader("ratings.csv"));
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String arr[] = line.split(",");
                user_id = Integer.parseInt(arr[0]);

            }
            bf.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return user_id + 1;
    }

    void GiveRating(User a, Movie m, double rating) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("ratings.csv"), true));
            bw.append(a.getId() + "," + m.getId() + "," + rating);
            bw.flush();
            bw.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
