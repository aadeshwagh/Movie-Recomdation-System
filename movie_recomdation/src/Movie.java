import java.util.*;
import java.io.*;

public class Movie {
    BufferedReader br;

    public Movie() {

    }

    public Movie(int id) {
        this.id = id;
    }

    public Movie(String gener) {
        this.genres = gener;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenres() {
        return genres;
    }

    private int id;
    private String name;
    private String genres;

    public ArrayList<Movie> getMoviesBygener(String genres) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("movies.csv"));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                Movie m = new Movie(genres);
                String arr[] = line.split(",");
                for (String s : genres.split(" ")) {
                    if (arr[2].contains(s)) {
                        m.setName(arr[1]);
                        m.setId(Integer.parseInt(arr[0]));
                        m.setGenres(arr[2]);
                        movies.add(m);
                        break;
                    }
                }

            }
            br.close();
        } catch (Exception e) {
            throw new Error("exception in Movie class setmovierating method");
        }
        return movies;
    }

    public Movie getMovieByid(int movie_id) {
        Movie m = new Movie(movie_id);
        try {
            br = new BufferedReader(new FileReader("movies.csv"));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                if (Integer.parseInt(arr[0]) == movie_id) {
                    m.setName(arr[1]);
                    m.setGenres(arr[2]);
                }

            }
            br.close();
        } catch (Exception e) {
            throw new Error("exception in Movie class setmovierating method");
        }

        return m;

    }

    ArrayList<Movie> unCommonLikedMovies(User a, User b) {
        ArrayList<Movie> uncommonmovies = new ArrayList<>();
        a.setMovieRatings();
        b.setMovieRatings();
        for (int i : b.getMovieRatings().keySet()) {
            if (!a.getMovieRatings().keySet().contains(i) && b.getMovieRatings().get(i) > 4.0) {
                uncommonmovies.add(getMovieByid(i));
            }
        }
        return uncommonmovies;
    }

    public int getMDbRatingvector(int movie_id) {
        try {
            br = new BufferedReader(new FileReader("links.csv"));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String arr[] = line.split(",");
                if (movie_id == Integer.parseInt(arr[0])) {
                    return Integer.parseInt(arr[2]);
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
        }

        return -1;

    }

}
