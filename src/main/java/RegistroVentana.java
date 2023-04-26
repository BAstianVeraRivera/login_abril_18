import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class RegistroVentana extends Ventana implements ActionListener {
    private JLabel lblUsuario, lblPassword, lblConfirmar; // Etiquetas para el nombre de usuario, la contraseña y la confirmación de la contraseña
    private JTextField txtUsuario; // Campo de texto para el nombre de usuario
    private JPasswordField txtPassword, txtConfirmar; // Campos de texto para la contraseña y la confirmación de la contraseña
    private JButton btnRegistrarse, btnCancelar; // Botones para registrarse o cancelar
    private JPanel panel; // Panel para organizar los componentes
    private GestorDatos gestor;

    public RegistroVentana(String titulo, int ancho, int alto, GestorDatos gestor) {
        super(titulo, ancho, alto); // Llama al constructor de la superclase Ventana
        this.gestor = gestor; // Asigna el gestor de datos al atributo
        crearComponentes(); // Crea los componentes de la ventana
        agregarComponentes(); // Agrega los componentes al panel y al marco
        agregarEventos(); // Agrega los eventos a los botones
        setVisible(true); // Hace visible la ventana
    }
    private void crearComponentes() {
        lblUsuario = new JLabel("Usuario:"); // Crea la etiqueta para el nombre de usuario
        lblPassword = new JLabel("Contraseña:"); // Crea la etiqueta para la contraseña
        lblConfirmar = new JLabel("Confirmar contraseña:"); // Crea la etiqueta para la confirmación de la contraseña
        txtUsuario = new JTextField(15); // Crea el campo de texto para el nombre de usuario con un tamaño de 15 caracteres
        txtPassword = new JPasswordField(15); // Crea el campo de texto para la contraseña con un tamaño de 15 caracteres
        txtConfirmar = new JPasswordField(15); // Crea el campo de texto para la confirmación de la contraseña con un tamaño de 15 caracteres
        btnRegistrarse = new JButton("Registrarse"); // Crea el botón para registrarse
        btnCancelar = new JButton("Cancelar"); // Crea el botón para cancelar
        panel = new JPanel(); // Crea el panel para organizar los componentes
        panel.setLayout(new GridLayout(4, 2));
    }
    private void agregarComponentes() {
        panel.add(lblUsuario); // Agrega la etiqueta del nombre de usuario al panel
        panel.add(txtUsuario); // Agrega el campo de texto del nombre de usuario al panel
        panel.add(lblPassword); // Agrega la etiqueta de la contraseña al panel
        panel.add(txtPassword); // Agrega el campo de texto de la contraseña al panel
        panel.add(lblConfirmar); // Agrega la etiqueta de la confirmación de la contraseña al panel
        panel.add(txtConfirmar); // Agrega el campo de texto de la confirmación de la contraseña al panel
        panel.add(btnRegistrarse); // Agrega el botón de registro al panel
        panel.add(btnCancelar); // Agrega el botón de cancelar al panel

        add(panel);
    }
    private void agregarEventos() {
        btnRegistrarse.addActionListener(this); // Agrega un escuchador de acción al botón de registrarse
        btnCancelar.addActionListener(this); // Agrega un escuchador de acción al botón de cancelar
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrarse) { // Si se hizo clic en el botón de registrarse
            String usuario = txtUsuario.getText(); // Obtiene el nombre de usuario ingresado
            String password = new String(txtPassword.getPassword()); // Obtiene la contraseña ingresada
            String confirmar = new String(txtConfirmar.getPassword()); // Obtiene la confirmación de la contraseña ingresada
            if (usuario.isEmpty() || password.isEmpty() || confirmar.isEmpty()) { // Si alguno de los campos está vacío
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos"); // Muestra un mensaje de advertencia
            } else if (!password.equals(confirmar)) { // Si la contraseña y la confirmación no coinciden
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden"); // Muestra un mensaje de error
            } else { // Si los campos son válidos
                String passwordGuardada = gestor.buscarUsuario(usuario); // Busca la contraseña del usuario en el archivo usando el gestor
                if (passwordGuardada != null) { // Si se encontró el usuario
                    JOptionPane.showMessageDialog(this, "El nombre de usuario ya está en uso"); // Muestra un mensaje de error
                } else { // Si el usuario no existe
                    Usuario nuevo = new Usuario(usuario, password); // Crea un nuevo objeto Usuario con el nombre y la contraseña ingresados
                    gestor.guardarUsuario(nuevo); // Guarda el nuevo usuario en el archivo usando el gestor
                    JOptionPane.showMessageDialog(this, "Usuario registrado con éxito"); // Muestra un mensaje de confirmación
                    dispose(); // Cierra la ventana de registro
                    new LoginVentana("Iniciar sesión", 200, 100, gestor.getArchivo()); // Crea una nueva ventana de login y le pasa el nombre del archivo del gestor
                }
            }
        } else if (e.getSource() == btnCancelar) { // Si se hizo clic en el botón de cancelar
            dispose(); // Cierra la ventana de registro
            new LoginVentana("Iniciar sesión", 200, 100, gestor.getArchivo()); // Crea una nueva ventana de login y le pasa el nombre del archivo del gestor
        }
    }
}

