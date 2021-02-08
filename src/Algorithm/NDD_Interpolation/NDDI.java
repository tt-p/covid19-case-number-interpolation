package Algorithm.NDD_Interpolation;

import Algorithm.Exeptions.OrderExceedException;
import Algorithm.Utility.Tuple_2;

import java.util.LinkedList;
import java.util.List;

public class NDDI {

    private List<Tuple_2> rawData;
    private List<Tuple_2> data;

    public NDDI(List<Tuple_2> list) {
        this.rawData = list;
    }

    public double getApprox(int order, double value) throws OrderExceedException {

        if (order >= rawData.size()) {
            throw new OrderExceedException("Data has " + rawData.size() + " points. User wants " + order +
                    "th order interpolation. For (n)th order interpolation input must include (n + 1) points");
        }

        if (order == rawData.size() - 1)
            data = rawData;
        else
            data = rearrangeData(order + 1, value);

        double[][] table = new double[data.size()][data.size()];

        for (int i = 0; i < data.size(); i++) {
            table[i][0] = data.get(i).getY();
        }

        for (int i = 1; i < data.size(); i++) {
            for (int j = 0; j < data.size() - i; j++) {
                table[j][i] = (table[j][i - 1] - table[j + 1][i - 1]) / (data.get(j).getX() - data.get(i + j).getX());
            }
        }

        double res = table[0][0];

        for (int i = 1; i < data.size(); i++) {
            res = res + (proterm(i, value) * table[0][i]);
        }
        return res;
    }

    private double proterm(int i, double value) {
        double pro = 1;
        for (int j = 0; j < i; j++) {
            pro = pro * (value - data.get(j).getX());
        }
        return pro;
    }

    private List<Tuple_2> rearrangeData(int n, double value) {

        List<Tuple_2> list = new LinkedList<>(rawData);
        int del = list.size() - n;

        double diffLow, diffUp;

        while (del > 0) {
            diffLow = Math.abs(value - list.get(0).getX());
            diffUp = Math.abs(value - list.get(list.size() - 1).getX());
            if (diffLow > diffUp) {
                list.remove(0);
                del--;
            }else {
                list.remove(list.size() - 1);
                del--;
            }
        }
        return list;
    }

}
