import javax.swing.JOptionPane;

public class Conversor {
    // Conversion al 5 de Marzo de 2023
    static double dolar = 0.0051;
    static double euro = 0.0048;
    static double esterlina = 0.0042;
    static double yen = 0.69;
    static double won = 6.57;

    static String[] tipoConversion = { "Conversor de Moneda", "Conversor numero Decimal-Romano" };
    static String[] moneda = { "Peso Arg a Dolar", "Peso Arg a Euro", "Peso Arg a Libra Esterlina",
            "Peso Arg a Yen Japones", "Peso Arg a Won Surcoreano", "Dolar a Peso Arg", "Euro a Peso Arg",
            "Libra Esterlina a Peso Arg", "Yen Japones a Peso Arg", "Won Surcoreano a Peso Arg" };
    static String[] decimalRomano = { "Decimal a Romano", "Romano a Decimal" };
    static Boolean continuar = true;

    public static double desdePeso(double valorDeConversion, double cantidad) {
        return cantidad * valorDeConversion;
    }

    public static double haciaPeso(double valorDeConversion, double cantidad) {
        return cantidad / valorDeConversion;
    }

    public static boolean continuar() {
        int seguir;
        seguir = JOptionPane.showConfirmDialog(null, "Desea continuar?", "Continuar?", 0);

        if (seguir == 0) {
            return true;
        }
        return false;
    }

    private static boolean esNumero(String n) {
        try {
            Double.parseDouble(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void convertidorMoneda(String tipoMoneda, int desdeOhacia, double valorDeCambio) {
        String ingresoValor;

        try {
            ingresoValor = (JOptionPane.showInputDialog(null, "Ingrese la cantidad", tipoMoneda, 3, null,
                    null, 0).toString());

        } catch (NullPointerException npe) {
            return;
        }

        while (!esNumero(ingresoValor) || Integer.parseInt(ingresoValor) <= 0) {
            try {
                ingresoValor = (JOptionPane
                        .showInputDialog(null, "Ingrese un valor valido.", tipoMoneda, 0, null, null, 0)
                        .toString());

            } catch (NullPointerException npe) {
                return;
            }
        }
        if (desdeOhacia == 0) {
            JOptionPane.showMessageDialog(null, "El resultado de la conversion es : "
                    + String.format("%.2f", desdePeso(valorDeCambio, Double.parseDouble(ingresoValor)), tipoMoneda,
                            1),
                    tipoMoneda, 1);

        } else {
            JOptionPane.showMessageDialog(null, "El resultado de la conversion es : "
                    + String.format("%.2f", haciaPeso(valorDeCambio, Double.parseDouble(ingresoValor)), tipoMoneda,
                            1),
                    tipoMoneda, 1);
        }
    }

    public static String aRomano(int numero) {
        int[] valores = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] letras = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            while (numero >= valores[i]) {
                resultado.append(letras[i]);
                numero -= valores[i];
            }
        }
        return resultado.toString();
    }

    public static int aDecimal(String numero) {
        int resultado = 0;
        String romano = numero.toUpperCase();
        for (int i = 0; i < romano.length(); i++) {
            char letra = romano.charAt(i);
            if (letra == 'M') {
                resultado += 1000;
            } else if (letra == 'D') {
                resultado += 500;
            } else if (letra == 'C') {
                if (i < romano.length() - 1 && romano.charAt(i + 1) == 'M') {
                    resultado += 900;
                    i++;
                } else if (i < romano.length() - 1 && romano.charAt(i + 1) == 'D') {
                    resultado += 400;
                    i++;
                } else {
                    resultado += 100;
                }
            } else if (letra == 'L') {
                resultado += 50;
            } else if (letra == 'X') {
                if (i < romano.length() - 1 && romano.charAt(i + 1) == 'C') {
                    resultado += 90;
                    i++;
                } else if (i < romano.length() - 1 && romano.charAt(i + 1) == 'L') {
                    resultado += 40;
                    i++;
                } else {
                    resultado += 10;
                }
            } else if (letra == 'V') {
                resultado += 5;
            } else if (letra == 'I') {
                if (i < romano.length() - 1 && romano.charAt(i + 1) == 'X') {
                    resultado += 9;
                    i++;
                } else if (i < romano.length() - 1 && romano.charAt(i + 1) == 'V') {
                    resultado += 4;
                    i++;
                } else {
                    resultado += 1;
                }
            }
        }
        return resultado;
    }

    public static void convertidorDecimalRomano(String tipoCambio, int desdeOhacia) {
        String ingresoValor;
        try {
            ingresoValor = (JOptionPane
                    .showInputDialog(null, "Ingrese un valor (Entre 1 a 4000).", tipoCambio, 3, null,
                            null, 0)
                    .toString());
        } catch (NullPointerException npe) {
            return;
        }
        if (desdeOhacia == 0) {
            while (!esNumero(ingresoValor) || Integer.parseInt(ingresoValor) >= 4000
                    || Integer.parseInt(ingresoValor) <= 0) {
                try {
                    ingresoValor = (JOptionPane
                            .showInputDialog(null, "Ingrese un numero entre 1 a 4000.", tipoCambio,
                                    0, null, null, 0)
                            .toString());
                } catch (NullPointerException npe) {
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "El resultado de la conversion es : " +
                    aRomano(Integer.parseInt(ingresoValor)), tipoCambio, 1);
        } else {
            while (aDecimal(ingresoValor) == 0 || aDecimal(ingresoValor)>=4000) {
                try {
                    ingresoValor = (JOptionPane
                            .showInputDialog(null, "Ingrese un valor valido (Entre un valor de 1 a 4000).", tipoCambio,
                                    0, null, null, 0)
                            .toString());
                } catch (NullPointerException npe) {
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "El resultado de la conversion es : " +
                    aDecimal(ingresoValor), tipoCambio, 1);
        }
    }

    public static void main(String[] args) {
        while (continuar) {
            String seleccion;
            try {
                seleccion = JOptionPane
                        .showInputDialog(null, "Seleccione un tipo  de conversion", "Conversor", 3, null,
                                tipoConversion, 0)
                        .toString();
            } catch (NullPointerException e) {
                seleccion = "Salir";
            }
            switch (seleccion) {
                case "Conversor de Moneda":
                    String tipoMoneda;
                    try {
                        tipoMoneda = JOptionPane.showInputDialog(null, "Seleccione un cambio de moneda",
                                "Conversor de Moneda", 3, null, moneda, 0).toString();

                    } catch (NullPointerException e) {
                        tipoMoneda = "Salir";
                    }
                    switch (tipoMoneda) {
                        case "Peso Arg a Dolar":
                            convertidorMoneda(tipoMoneda, 0, dolar);
                            break;
                        case "Peso Arg a Euro":
                            convertidorMoneda(tipoMoneda, 0, euro);
                            break;
                        case "Peso Arg a Libra Esterlina":
                            convertidorMoneda(tipoMoneda, 0, esterlina);
                            break;
                        case "Peso Arg a Yen Japones":
                            convertidorMoneda(tipoMoneda, 0, yen);
                            break;
                        case "Peso Arg a Won Surcoreano":
                            convertidorMoneda(tipoMoneda, 0, won);
                            break;
                        case "Dolar a Peso Arg":
                            convertidorMoneda(tipoMoneda, 1, dolar);
                            break;
                        case "Euro a Peso Arg":
                            convertidorMoneda(tipoMoneda, 1, euro);
                            break;
                        case "Libra Esterlina a Peso Arg":
                            convertidorMoneda(tipoMoneda, 1, esterlina);
                            break;
                        case "Yen Japones a Peso Arg":
                            convertidorMoneda(tipoMoneda, 1, yen);
                            break;
                        case "Won Surcoreano a Peso Arg":
                            convertidorMoneda(tipoMoneda, 1, won);
                            break;
                        case "Salir":
                            break;
                    }
                    break;
                case "Conversor numero Decimal-Romano":
                    String tipoDecimalRomano;
                    try {
                        tipoDecimalRomano = JOptionPane.showInputDialog(null, "Seleccione el tipo de cambio",
                                "Conversor numero Decimal-Romano", 3, null, decimalRomano, 0).toString();
                    } catch (NullPointerException e) {
                        break;
                    }
                    if (tipoDecimalRomano == "Decimal a Romano") {
                        convertidorDecimalRomano(tipoDecimalRomano, 0);
                    } else {
                        convertidorDecimalRomano(tipoDecimalRomano, 1);
                    }
                    break;
                case "Salir":
                    break;
            }
            continuar = continuar();
        }
    }
}