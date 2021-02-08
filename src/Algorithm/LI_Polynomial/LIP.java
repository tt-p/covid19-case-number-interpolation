package Algorithm.LI_Polynomial;

import Algorithm.Exeptions.OrderExceedException;
import Algorithm.Utility.Tuple_2;

import java.util.LinkedList;
import java.util.List;

public class LIP {

    private List<Tuple_2> rawData;
    private List<Tuple_2> data;

    public LIP(List<Tuple_2> list) {
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

        double res = 0;

        for (int i = 0; i < data.size(); i++) {

            double term = data.get(i).getY();
            for (int j = 0; j < data.size(); j++) {
                if (j != i)
                    term = term * (value - data.get(j).getX()) / (data.get(i).getX() - data.get(j).getX());
            }
            res += term;
        }

        return res;
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
