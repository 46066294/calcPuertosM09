/**
 * Created by 46066294p on 10/02/16.
 */
public class Operacion {

    public static String calcular(String operacion){

        if (operacion.contains("+")){

            int posPrimera = operacion.indexOf("+");
            String operando1 = operacion.substring(0,posPrimera);
            String operando2 = operacion.substring(posPrimera+1,operacion.length());

            double op1 = Double.parseDouble(operando1);
            double op2 = Double.parseDouble(operando2);
            double total = op1+op2;

            String msgTotal = op1 + " + " + op2 + " = " + total;

            System.out.println(msgTotal);
            return msgTotal;

        }else if (operacion.contains("-")) {
            int posPrimera = operacion.indexOf("-");
            String operando1 = operacion.substring(0, posPrimera);
            String operando2 = operacion.substring(posPrimera + 1, operacion.length());

            double op1 = Double.parseDouble(operando1);
            double op2 = Double.parseDouble(operando2);
            double total = op1 - op2;

            String msgTotal = op1 + " - " + op2 + " = " + total;

            System.out.println(msgTotal);
            return msgTotal;

        }else if (operacion.contains("*")) {
            int posPrimera = operacion.indexOf("*");
            String operando1 = operacion.substring(0, posPrimera);
            String operando2 = operacion.substring(posPrimera + 1, operacion.length());

            double op1 = Double.parseDouble(operando1);
            double op2 = Double.parseDouble(operando2);
            double total = op1 * op2;

            String msgTotal = op1 + " * " + op2 + " = " + total;

            System.out.println(msgTotal);
            return msgTotal;

        }else if (operacion.contains("/")) {

            int posPrimera = operacion.indexOf("/");
            String operando1 = operacion.substring(0, posPrimera);
            String operando2 = operacion.substring(posPrimera + 1, operacion.length());

            double op1 = Double.parseDouble(operando1);
            double op2 = Double.parseDouble(operando2);
            double total = op1 / op2;

            String msgTotal = op1 + " / " + op2 + " = " + total;

            System.out.println(msgTotal);
            return msgTotal;

        }else{
            String msgSalida = "QUE COÑO DICES?";
            System.out.println(msgSalida);
            return msgSalida;
        }

    }


}