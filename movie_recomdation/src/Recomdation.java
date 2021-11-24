import java.util.*;

public class Recomdation {
    Set<String> RegisteredUserRecomdations(int user_id) {
        similarUsers s = new similarUsers();
        Movie m = new Movie();
        s.CreateuserRatingmatrix();
        s.findSimilirityMatrix(user_id);
        ArrayList<User> neg = s.Neighbourhood(s.getSimilarityMatrix());
        Set<String> movies = new HashSet<>();
        movies = firstpass(user_id, m, neg, movies);

        return movies;
    }

    Set<String> firstpass(int user_id, Movie m, ArrayList<User> neg, Set<String> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(1))) {
                movies.add(p.getName());
            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(0))) {
                movies.add(p.getName());
            }

        }
        return movies;
    }

    Set<String> secondpass(int user_id, Movie m, ArrayList<User> neg, Set<String> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(2))) {
                movies.add(p.getName());
            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(1))) {
                movies.add(p.getName());
            }

        }
        return movies;
    }

    Set<String> thirdpass(int user_id, Movie m, ArrayList<User> neg, Set<String> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(3))) {
                movies.add(p.getName());
            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(2))) {
                movies.add(p.getName());
            }

        }
        return movies;
    }

    // NewUserRecomdations();

    Set<String> generBasedRecomdations(String gener) {
        Movie m = new Movie();
        Set<String> movies = new HashSet<>();
        for (Movie k : m.getMoviesBygener(gener)) {
            movies.add(k.getName());
        }
        return movies;

    }

}
