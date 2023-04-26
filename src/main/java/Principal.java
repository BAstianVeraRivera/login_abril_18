public class Principal {

    public static void main(String[] args) {
        GestorDatos gestor = new GestorDatos("usuarios.txt"); // Crea un objeto de la clase GestorDatos con el nombre del archivo
        new LoginVentana("Iniciar sesión", 200, 100, gestor.getArchivo()); // Crea una instancia de la clase LoginVentana y le pasa el nombre del archivo del gestor
    }
}