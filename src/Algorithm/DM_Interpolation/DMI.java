package Algorithm.DM_Interpolation;

import Algorithm.Exeptions.NotASquareException;
import Algorithm.Exeptions.OrderExceedException;
import Algorithm.MatrixOp.Matrix;
import Algorithm.Utility.Tuple_2;

import java.util.*;

public class DMI {

    private List<Tuple_2> rawData;
    private List<Tuple_2> data;
    private List<Double> coffs;

    public DMI(List<Tuple_2> list) {
        rawData = list;
    }

    public double getApprox(int order, double value) throws OrderExceedException {
        coffs = getCoffs(order, value);

        double sum = 0;
        for (int i = 0; i < coffs.size(); i++) {
            sum += coffs.get(i) * Math.pow(value, i);
        }
        return sum;
    }

    public List<Double> getCoffs(int order, double value) throws OrderExceedException {

        if (order >= rawData.size()) {
            throw new OrderExceedException("Data has " + rawData.size() + " points. User wants " + order +
                    "th order interpolation. For (n)th order interpolation input must include (n + 1) points");
        }

        if (order == rawData.size() - 1)
            data = rawData;
        else
            data = rearrangeData(order + 1, value);

        Matrix mat1 = new Matrix(data.size(), data.size());
        Matrix mat2 = new Matrix(data.size(), 1);
        Matrix mat3 = new Matrix(data.size(), 1);

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                mat1.setValueAt(i, j, Math.pow(data.get(i).getX(), j));
            }
        }

        for (int i = 0; i < data.size(); i++) {
            mat2.setValueAt(i, 0, data.get(i).getY());
        }

        try {
            mat3 = mat1.getInverse().multiplyByMatrix(mat2);
        } catch (NotASquareException e) {
            e.printStackTrace();
        }

        List<Double> cffs = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            cffs.add(i, mat3.getValueAt(i, 0));
        }
        return cffs;
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