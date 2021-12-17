import java.util.*;

public class Recomdation {
    Set<List<String>> RegisteredUserRecomdations(int user_id, int pass) {
        similarUsers s = new similarUsers();
        Movie m = new Movie();
        s.CreateuserRatingmatrix();
        s.findSimilirityMatrix(user_id);
        ArrayList<User> neg = s.Neighbourhood(s.getSimilarityMatrix());
        Set<List<String>> movies = new HashSet<>();
        if (pass == 1) {
            movies = firstpass(user_id, m, neg, movies);
        } else if (pass == 2) {
            movies = secondpass(user_id, m, neg, movies);
        } else if (pass == 3) {
            movies = thirdpass(user_id, m, neg, movies);
        }

        return movies;
    }

    Set<List<String>> firstpass(int user_id, Movie m, ArrayList<User> neg, Set<List<String>> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(1))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);

            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(0))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);
            }

        }
        return movies;
    }

    Set<List<String>> secondpass(int user_id, Movie m, ArrayList<User> neg, Set<List<String>> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(2))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);
            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(1))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);
            }

        }
        return movies;
    }

    Set<List<String>> thirdpass(int user_id, Movie m, ArrayList<User> neg, Set<List<String>> movies) {
        if (user_id < 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(3))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);
            }

        } else if (user_id >= 100) {
            for (Movie p : m.unCommonLikedMovies(new User(user_id), neg.get(2))) {
                List<String> mset = new ArrayList<>();
                mset.add(p.getName());
                mset.add(p.getId() + "");
                mset.add(p.getMDbRatingvector(p.getId()) + "");
                movies.add(mset);
            }

        }
        return movies;
    }

    // NewUserRecomdations();

    Set<List<String>> generBasedRecomdations(String gener) {
        Movie m = new Movie();
        Set<List<String>> movies = new HashSet<>();
        for (Movie p : m.getMoviesBygener(gener)) {
            List<String> mset = new ArrayList<>();
            mset.add(p.getName());
            mset.add(p.getId() + "");
            mset.add(p.getMDbRatingvector(p.getId()) + "");
            movies.add(mset);
        }
        return movies;

    }

}
