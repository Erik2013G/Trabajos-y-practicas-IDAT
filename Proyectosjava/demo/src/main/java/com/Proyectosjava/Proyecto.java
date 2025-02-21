package com.Proyectosjava;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Proyecto {
    private JFrame frame;
    private JTextField userField;
    private JPasswordField passwordField;
    private HikariDataSource dataSource;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Proyecto window = new Proyecto();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Proyecto() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        frame = new JFrame("Login");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        JLabel titleLabel = new JLabel("Bienvenido al sistema");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(new JLabel("Usuario:"), constraints);

        constraints.gridx = 1;
        userField = new JTextField(20);
        panel.add(userField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Contraseña:"), constraints);

        constraints.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setPreferredSize(new Dimension(200, 40));
        btnLogin.setBackground(new Color(0, 123, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(e -> verificarCredenciales());
        panel.add(btnLogin, constraints);

        constraints.gridy = 4;
        JButton btnRegister = new JButton("Regístrate");
        btnRegister.setPreferredSize(new Dimension(200, 40));
        btnRegister.setBackground(new Color(0, 123, 255));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(e -> abrirNuevoFormularioRegistro());
        panel.add(btnRegister, constraints);
    }

    private void connectToDatabase() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:1324/Proyecto_Escritorio_Java");
            config.setUsername("root");
            config.setPassword("Claro2025");
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);

            crearTablaUsuarios();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error al conectar a la base de datos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearTablaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "usuario VARCHAR(50) UNIQUE NOT NULL, " +
                "contrasena VARCHAR(255) NOT NULL, " +
                "correo VARCHAR(100) UNIQUE NOT NULL, " +
                "rol ENUM('admin', 'usuario') DEFAULT 'usuario', " +
                "fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        try (Connection connection = dataSource.getConnection();
                Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla 'Usuarios': " + e.getMessage());
        }
    }

    private void verificarCredenciales() {
        String username = userField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT contrasena FROM Usuarios WHERE usuario = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("contrasena");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso!", "Éxito",
                                JOptionPane.INFORMATION_MESSAGE);
                        mostrarDashboard(); // Llamamos al método para cambiar la interfaz
                    } else {
                        JOptionPane.showMessageDialog(frame, "Contraseña incorrecta.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error al verificar las credenciales: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDashboard() {
        frame.getContentPane().removeAll(); // Elimina todos los componentes anteriores
        frame.setTitle("Panel de Control"); // Cambia el título de la ventana

        JPanel panelDashboard = new JPanel();
        panelDashboard.setLayout(new BorderLayout());
        panelDashboard.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Bienvenido al sistema", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setForeground(new Color(0, 51, 102));

        JButton btnCerrar = new JButton("Cerrar sesión");
        btnCerrar.setPreferredSize(new Dimension(150, 40));
        btnCerrar.setBackground(new Color(255, 51, 51));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(e -> System.exit(0)); // Cierra la aplicación
        btnCerrar.addActionListener(e -> mostrarLogin()); // Regresar a la pantalla de inicio de sesión

        panelDashboard.add(label, BorderLayout.CENTER);
        panelDashboard.add(btnCerrar, BorderLayout.SOUTH);

        frame.getContentPane().add(panelDashboard);
        frame.revalidate(); // Refresca la ventana
        frame.repaint();
    }

    private void mostrarLogin() {
        frame.getContentPane().removeAll();
        frame.setTitle("Login");

        initialize(); // Vuelve a cargar los componentes originales
        frame.revalidate();
        frame.repaint();
    }

    private void abrirNuevoFormularioRegistro() {
        JFrame registroFrame = new JFrame("Registro de Usuario");
        registroFrame.setSize(350, 300);
        registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registroFrame.setLocationRelativeTo(frame);
        registroFrame.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarUsuario(txtNombre.getText(), txtUsuario.getText(),
                new String(txtContrasena.getPassword()), txtCorreo.getText()));

        registroFrame.add(lblNombre);
        registroFrame.add(txtNombre);
        registroFrame.add(lblUsuario);
        registroFrame.add(txtUsuario);
        registroFrame.add(lblCorreo);
        registroFrame.add(txtCorreo);
        registroFrame.add(lblContrasena);
        registroFrame.add(txtContrasena);
        registroFrame.add(new JLabel());
        registroFrame.add(btnRegistrar);

        registroFrame.setVisible(true);
    }

    private void registrarUsuario(String nombre, String usuario, String contrasena, String email) {
        if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        String query = "INSERT INTO Usuarios (nombre, usuario, contrasena, correo) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombre);
            statement.setString(2, usuario);
            statement.setString(3, hashedPassword);
            statement.setString(4, email);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Registro exitoso!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error al registrar usuario: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        // frame.dispose();
    }
}