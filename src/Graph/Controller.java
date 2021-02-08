package Graph;

import Algorithm.DM_Interpolation.DMI;
import Algorithm.Exeptions.OrderExceedException;
import Algorithm.LI_Polynomial.LIP;
import Algorithm.NDD_Interpolation.NDDI;
import Algorithm.Utility.Points;
import IO.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    private DataManager dM = new DataManager("C:/Users/user/IdeaProjects/Experimental/Bim204Homework/src/Graph/data.txt");
    private int order = 11;
    private int value = 35;

    @FXML
    LineChart<String, Number> lineChart1;

    @FXML
    LineChart<Number, Number> lineChart2;

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Total Case");
        List<String> date = dM.getDate();
        List<Integer> totalCase = dM.getTotalCase();

        for (int i = 0; i < date.size(); i++) {
            series.getData().add(new XYChart.Data<>(date.get(i), totalCase.get(i)));
        }

        lineChart1.getData().add(series);

    }

    public void dMIapproxBtn(ActionEvent event) {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        List<Double> list = new LinkedList<>();
        try {
            DMI dmi = new DMI(Points.getPoints());
            list = dmi.getCoffs(order,value);
        } catch (OrderExceedException e) {
            e.printStackTrace();
        }

        double x = 1;

        while(x <= 41) {

            double sum = 0;

            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i) * Math.pow(x, i);
            }

            series.getData().add(new XYChart.Data<>(x, sum));

            x += 0.1;
        }

        series.setName("DMI Approximation");
        lineChart2.getData().add(series);
    }

    public void lIPapproxBtn(ActionEvent event) {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        LIP lip = new LIP(Points.getPoints());

        double x = 1;

        while(x <= 41) {

            double y = 0;
            try {
                y = lip.getApprox(order, x);
            } catch (OrderExceedException e) {
                e.printStackTrace();
            }

            series.getData().add(new XYChart.Data<>(x, y));

            x += 0.1;
        }

        series.setName("LIP Approximation");
        lineChart2.getData().add(series);
    }

    public void NDDIapproxBtn(ActionEvent event) {

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        NDDI nddi = new NDDI(Points.getPoints());

        double x = 1;

        while(x <= 41) {

            double y = 0;
            try {
                y = nddi.getApprox(order, x);
            } catch (OrderExceedException e) {
                e.printStackTrace();
            }

            series.getData().add(new XYChart.Data<>(x, y));

            x += 0.1;
        }

        series.setName("NDDI Approximation");
        lineChart2.getData().add(series);
    }

}