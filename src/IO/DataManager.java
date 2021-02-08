package IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class DataManager {

    private String dataPath;
    private List<String> allLines;
    private List<String> allInfo = new LinkedList<>();
    private List<String> date = new LinkedList<>();
    private List<Integer> totalTest = new LinkedList<>();
    private List<Integer> totalCase = new LinkedList<>();
    private List<Integer> totalDead = new LinkedList<>();
    private List<Integer> totalIc = new LinkedList<>();
    private List<Integer> totalIntubated = new LinkedList<>();
    private List<Integer> totalRecover = new LinkedList<>();

    public List<String> getDate() {
        return date;
    }

    public List<Integer> getTotalTest() {
        return totalTest;
    }

    public List<Integer> getTotalCase() {
        return totalCase;
    }

    public List<Integer> getTotalDead() {
        return totalDead;
    }

    public List<Integer> getTotalIc() {
        return totalIc;
    }

    public List<Integer> getTotalIntubated() {
        return totalIntubated;
    }

    public List<Integer> getTotalRecover() {
        return totalRecover;
    }

    public DataManager(String path) {
        this.dataPath = path;
        initData();
    }

    private void initData() {
        Path path = Paths.get(dataPath);
        try {
            allLines = Files.readAllLines(path);
            allLines.remove(0);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        for (String str: allLines) {
            String[] strings = str.split("\t");
            date.add(strings[0]);
            totalTest.add(Integer.valueOf(strings[1]));
            totalCase.add(Integer.valueOf(strings[2]));
            totalDead.add(Integer.valueOf(strings[3]));
            totalIc.add(Integer.valueOf(strings[4]));
            totalIntubated.add(Integer.valueOf(strings[5]));
            totalRecover.add(Integer.valueOf(strings[6]));
        }
    }

}
