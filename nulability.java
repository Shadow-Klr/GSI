package Gs_Inventario;

public class nulability extends Exception {

    public Nulability(String tipoDato, double valor) {
        super("No puedes introducir un " + tipoDato + " nulo");
    } 

    // Método estático para validar un número
    public static void verifyNulability(double valor, String tipoDato) throws Nulability {
        if (valor == null) {
            throw new Nulability(tipoDato, valor);
        }
    }
}
