import java.util.*;

public class App {
    public static void main(String[] args) {

        // create user rating matrix
        // find similarity matrix between our user and other users
        // find the neighbourhood of our user i.e users who are similar with our user
        // i.e user with greater value of pearson corelation
        // recommend movies that similar user like which our user havent watched

        // Recomdation r = new Recomdation();
        //

        // printFirst10(r.generBasedRecomdations(" Sci-Fi"));
        // printFirst10(r.RegisteredUserRecomdations(12));
        // printFirst10(r.generBasedRecomdations("Sci-Fi"));
        GUI g = new GUI();
        g.main();
        // similarUsers s = new similarUsers();
        // s.CreateuserRatingmatrix();
        // registereduser r = new registereduser("12");
        // r.getRecomdatedmovies();
        // r.pack();

        // registereduser r = new registereduser("1");
        // r.createUserinterface();

        // User u = new User();
        // int p = u.addNewUser();
        // u.GiveRating(new User(p), new Movie(2), 3.7);
        // System.out.println(p);

    }

    static void printFirst10(Set<List<String>> s) {
        int count = 1;
        for (List<String> i : s) {
            if (count == 10) {
                break;
            }
            System.out.println(count + ". " + i);
            count++;

        }
    }

}
