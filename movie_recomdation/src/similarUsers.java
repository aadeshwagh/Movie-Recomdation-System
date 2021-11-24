import java.io.*;
import java.util.*;

public class similarUsers {
    BufferedReader bf;

    public double[] getSimilarityMatrix() {
        return similarityMatrix;
    }

    public void setSimilarityMatrix(double[] similarityMatrix) {
        this.similarityMatrix = similarityMatrix;
    }

    public double[][] getUserRatingMatrix() {
        return userRatingMatrix;
    }

    public void setUserRatingMatrix(double[][] userRatingMatrix) {
        this.userRatingMatrix = userRatingMatrix;
    }

    private double similarityMatrix[];
    private double userRatingMatrix[][];

    public void findSimilirityMatrix(int user_id) {
        similarityMatrix = new double[101];
        for (int j = 1; j <= 100; j++) {
            if (personCorelation(new User(user_id), new User(j)) == -13) {
                continue;
            } else {
                similarityMatrix[j] = personCorelation(new User(user_id), new User(j));
            }

        }
    }

    public double personCorelation(User a, User b) {
        // find common user rating vectors
        ArrayList<Double> userratingVector1 = new ArrayList<>();
        ArrayList<Double> userratingVector2 = new ArrayList<>();

        for (int i = 1; i < 193610; i++) {
            userratingVector1.add(userRatingMatrix[a.getId()][i]);
            userratingVector2.add(userRatingMatrix[b.getId()][i]);
        }
        int size = userratingVector1.size();
        int one = 0;
        int two = 0;
        for (int i = 0; i < size; i++) {
            if (userratingVector1.get(i) == 0.0) {
                one++;
            }
            if (userratingVector2.get(i) == 0.0) {
                two++;
            }
        }
        if (one == size || two == size) {
            return -13;
        }

        // normalize them

        double sum1 = 0;
        double sum2 = 0;
        for (int i = 1; i < size; i++) {
            sum1 += userratingVector1.get(i);
            sum2 += userratingVector2.get(i);
        }

        for (int i = 1; i < size; i++) {
            userratingVector1.set(i, userratingVector1.get(i) - sum1 / size);
            userratingVector2.set(i, userratingVector2.get(i) - sum2 / size);
        }

        // fine cosine between them

        double mul = 0;
        double div1 = 0;
        double div2 = 0;
        for (int i = 0; i < size; i++) {
            mul += userratingVector1.get(i) * userratingVector2.get(i);
            div1 += Math.pow(userratingVector1.get(i), 2);
            div2 += Math.pow(userratingVector2.get(i), 2);
        }

        double centeredCosine = mul / (Math.sqrt(div1) * Math.sqrt(div2));
        return centeredCosine;

    }

    public void CreateuserRatingmatrix() {
        userRatingMatrix = new double[611][193610];
        try {
            bf = new BufferedReader(new FileReader(new File(("ratings.csv"))));
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                String arr[] = line.split(",");
                userRatingMatrix[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])] = Double.parseDouble(arr[2]);
            }
            bf.close();
        } catch (Exception e) {
            throw new Error("exception in Similaruser class createuserratings method");
        }

    }

    ArrayList<User> Neighbourhood(double similarityMatrix[]) {
        ArrayList<User> temp = new ArrayList<>();
        ArrayList<User> neighbourhood = new ArrayList<>();
        for (int i = 1; i < similarityMatrix.length; i++) {
            temp.add(new User(i));
        }
        for (User u : temp) {
            if (similarityMatrix[u.getId()] > 0) {
                neighbourhood.add(u);
            }
        }

        Collections.sort(neighbourhood, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                // TODO Auto-generated method stub
                if (similarityMatrix[o2.getId()] > similarityMatrix[o1.getId()]) {
                    return 1;
                } else if (similarityMatrix[o2.getId()] == similarityMatrix[o1.getId()]) {
                    return 0;
                }
                return -1;

            }

        });

        return neighbourhood;

    }
}
