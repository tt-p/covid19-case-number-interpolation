package Algorithm;

import Algorithm.DM_Interpolation.DMI;
import Algorithm.Exeptions.NotASquareException;
import Algorithm.Exeptions.OrderExceedException;
import Algorithm.LI_Polynomial.LIP;
import Algorithm.NDD_Interpolation.NDDI;
import Algorithm.Utility.Tuple_2;

import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws NotASquareException, OrderExceedException {

        List<Tuple_2> input = new LinkedList<>();

//        input.add(new Tuple_2(0, 1));
//        input.add(new Tuple_2(10, 227.04));
//        input.add(new Tuple_2(15, 362.78));
//        input.add(new Tuple_2(20, 517.35));
//        input.add(new Tuple_2(22.5, 602.97));
//        input.add(new Tuple_2(30, 901.67));

//        input.add(new Tuple_2(5, 12));
//        input.add(new Tuple_2(6, 13));
//        input.add(new Tuple_2(9, 14));
//        input.add(new Tuple_2(11, 16));

        input.add(new Tuple_2(2.20,0.00));
        input.add(new Tuple_2(1.28,0.88));
        input.add(new Tuple_2(0.66,1.14));

//        DMI dmi = new DMI(input);

       LIP lip = new LIP(input);
//        NDDI nddi = new NDDI(input);

//        System.out.println(nddi.getApprox(3,7));

//        System.out.println("Approximation : " + lip.getApproximation(3,16));
//        System.out.println("Approximation : " + dmi.getApprox(3, 16));

        List<Long> apprxList = new LinkedList<>();


        System.out.println(lip.getApprox(2,1.10));

//        for(int i = 1; i <= 11; i++) {
//            System.out.printf("Error on NDDI Approximation order of %2d : %d%n", i, (apprxList.get(i-1)- 69392));
//        }

//        for (int i = 1; i < 42; i++) {
//            try {
//                System.out.println("DMI -Approximation-" + i + " : " + Math.round(dmi.getApprox(11, i)));
//                System.out.println("LIP -Approximation-" + i + " : " + Math.round(lip.getApprox(11, i)));
//                System.out.println("NDDI-Approximation-" + i + " : " + Math.round(nddi.getApprox(11, i)));
//            } catch (OrderExceedException e) {
//                e.printStackTrace();
//            }
//        }

/*
        int size = (int)(Math.random()*40);
        Matrix matrix = new Matrix(size, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setValueAt(i, j, Math.random()*10);
            }
        }

        Matrix inverse = matrix.getInverse();
        Matrix identity = inverse.multiplyByMatrix(matrix);

        System.out.println("Matrix :");
        System.out.println(matrix);

        System.out.println("Inverse :");
        System.out.println(inverse);

        System.out.println("Identity :");
        System.out.println(identity);
*/

    }

}
