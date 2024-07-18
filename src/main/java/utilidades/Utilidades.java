package utilidades;

public class Utilidades {
    public static String calcularPorcentaje(int cantidad, int total) {
        if (total == 0) {
            return "0%";
        }
        return String.format("%.2f", (cantidad * 100.0 / total)) + "%";
    }
}
