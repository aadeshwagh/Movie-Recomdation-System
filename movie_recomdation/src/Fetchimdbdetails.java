import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Fetchimdbdetails {
    String[] fetchMovieDetails(int tmdbid) {
        StringBuilder sb = null;
        String arr[] = new String[2];
        try {
            String api_key = "<api_key>";
            URL url = new URL(
                    "https://api.themoviedb.org/3/movie/" + tmdbid
                            + "?api_key=" + api_key + "&language=en-US");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            Reader streamReader = null;
            // System.out.println(status);

            streamReader = new InputStreamReader(con.getInputStream());

            String line;
            BufferedReader in = new BufferedReader(streamReader);
            sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                // System.out.println(line);
                sb.append(line);
            }

        } catch (Exception e) {
            new Error("Error downloading movie details");
        }

        if (sb == null) {
            new Error("connection error in movie details string buffer is null");
        } else {
            // System.out.println(sb.toString());
            for (String s : sb.toString().split(",")) {
                if (s.contains("original_title")) {
                    arr[0] = s.split(":")[1];
                }
                if (s.contains("poster_path")) {
                    arr[1] = s.split(":")[1];
                }
            }
        }

        return arr;
    }

    void downloadPoster(String imgpath, int movie_id) {
        BufferedImage image = null;
        StringBuilder b = new StringBuilder();
        String baseurl = "https://image.tmdb.org/t/p/w500";
        b.append(baseurl);
        b.append(imgpath);
        // System.out.println(baseurl + imgpath.replace('"' + "", ""));
        try {
            URL url1 = new URL(baseurl + imgpath.replace('"' + "", ""));
            image = ImageIO.read(url1);
            // for jpg
            ImageIO.write(image, "jpg", new File("posters/" + movie_id + ".jpg"));
        } catch (Exception e) {
            // TODO: handle exception
            new Error("Error downloading image");
        }

    }

}
