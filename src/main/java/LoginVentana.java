import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginVentana extends Ventana implements ActionListener {
    private JLabel lblUsuario, lblPassword; // Etiquetas para el nombre de usuario y la contraseña
    private JTextField txtUsuario; // Campo de texto para el nombre de usuario
    private JPasswordField txtPassword; // Campo de texto para la contraseña
    private JButton btnLogin, btnRegistrarse; // Botones para hacer login o registrarse
    private JPanel panel; // Panel para organizar los componentes
    private GestorDatos gestor; // Objeto de la clase GestorDatos que maneja los usuarios

    // Constructor que recibe el título y el tamaño de la ventana, y el nombre del archivo donde se guardan los usuarios
    public LoginVentana(String titulo, int ancho, int alto, String archivo) {
        super(titulo, ancho, alto); // Llama al constructor de la superclase Ventana
        gestor = new GestorDatos(archivo); // Crea un objeto de la clase GestorDatos con el nombre del archivo
        crearComponentes(); // Crea los componentes de la ventana
        agregarComponentes(); // Agrega los componentes al panel y al marco
        agregarEventos(); // Agrega los eventos a los botones
        setVisible(true); // Hace visible la ventana
    }

    // Método que crea los componentes de la ventana
    private void crearComponentes() {
        lblUsuario = new JLabel("Usuario:"); // Crea la etiqueta para el nombre de usuario
        lblPassword = new JLabel("Contraseña:"); // Crea la etiqueta para la contraseña
        txtUsuario = new JTextField(15); // Crea el campo de texto para el nombre de usuario con un tamaño de 15 caracteres
        txtPassword = new JPasswordField(15); // Crea el campo de texto para la contraseña con un tamaño de 15 caracteres
        btnLogin = new JButton("Iniciar sesión"); // Crea el botón para hacer login
        btnRegistrarse = new JButton("Registrarse"); // Crea el botón para registrarse
        panel = new JPanel(); // Crea el panel para organizar los componentes
        panel.setLayout(new GridLayout(3, 2)); // Establece el diseño del panel como una cuadrícula de 3 filas y 2 columnas
    }

    // Método que agrega los componentes al panel y al marco
    private void agregarComponentes() {
        panel.add(lblUsuario); // Agrega la etiqueta del nombre de usuario al panel
        panel.add(txtUsuario); // Agrega el campo de texto del nombre de usuario al panel
        panel.add(lblPassword); // Agrega la etiqueta de la contraseña al panel
        panel.add(txtPassword); // Agrega el campo de texto de la contraseña al panel
        panel.add(btnLogin); // Agrega el botón de login al panel
        panel.add(btnRegistrarse); // Agrega el botón de registro al panel

        add(panel); // Agrega el panel al marco
    }
    private void agregarEventos() {
        btnLogin.addActionListener(this); // Agrega un escuchador de acción al botón de login
        btnRegistrarse.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) { // Si se hizo clic en el botón de login
            String usuario = txtUsuario.getText(); // Obtiene el nombre de usuario ingresado
            String password = new String(txtPassword.getPassword()); // Obtiene la contraseña ingresada
            String passwordGuardada = gestor.buscarUsuario(usuario); // Busca la contraseña del usuario en el archivo usando el gestor
            if (passwordGuardada == null) { // Si no se encontró el usuario
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos"); // Muestra un mensaje de error
            } else if (passwordGuardada.equals(password)) { // Si la contraseña guardada coincide con la ingresada
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario); // Muestra un mensaje de bienvenida
                dispose(); // Cierra la ventana de login
            } else { // Si la contraseña guardada no coincide con la ingresada
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos"); // Muestra un mensaje de error
            }
        } else if (e.getSource() == btnRegistrarse) { // Si se hizo clic en el botón de registrarse
            dispose(); // Cierra la ventana de login
            new RegistroVentana("Registrarse", 200, 100, gestor); // Crea una nueva ventana de registro y le pasa el gestor de datos
        }
    }
}