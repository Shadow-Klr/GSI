package GSI;

public class Nulability extends Exception {

    public Nulability(String tipoDato, String valor) {
        super("No puedes introducir un " + tipoDato + " nulo");
    } 

    // Método estático para validar un número
    public static void verifyNulability(String valor, String tipoDato) throws Nulability {
        if (valor == "" || valor == null) {
            throw new Nulability(tipoDato, valor);
        }
    }
}
