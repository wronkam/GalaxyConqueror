package GalaxyConqueror.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static GalaxyConqueror.Controller.Highscores.Scores;

public class ReadScore {
    static public void readScores()
    {
        Scores.clear();
        Scanner l = null;
        try {
            l = new Scanner(new File("./resources/scores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(l == null){
                throw new NullPointerException("Scanner input missing");
            }
            else
            {
                while(l.hasNext())
                {
                    String line = l.nextLine();
                    String[] scores = line.split(",");
                    Scores.addAll(Arrays.asList(scores));
                }
                Scores.sort((s, t1) -> {
                    if(s.length() > t1.length())
                    {
                        return -1;
                    }
                    else if(s.length() == t1.length())
                    {
                        return t1.compareTo(s);
                    }
                    else
                    {
                        return 1;
                    }
                });
            }
        }catch (Exception e)
        {
            System.out.println(e+"error on scores.txt");
        }
    }
}
