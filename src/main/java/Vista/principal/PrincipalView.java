/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package Vista.principal;

import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class PrincipalView extends javax.swing.JFrame {
    
    public PrincipalView() {
        initComponents();
    }

    public JButton getBtnPagarMulta() {
        return bntPagarMulta;
    }

    public JButton getBtnBuscarLibro() {
        return btnBuscarLibro;
    }

    public JButton getBtnBuscarUsuario() {
        return btnBuscarUsuario;
    }

    public JButton getBtnRegistrarPrestamo() {
        return btnRegistrarPrestamo;
    }
    
    

    public JLabel getLabelFrase() {
        return labelFrase;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    // GETTER Y SETTER DE ITEMS MENU Y MENUS DE VENTANA PRINCIPAL
    
    //Usuario

    public JMenuItem getItemAgregarUsuario() {
        return itemAgregarUsuario;
    }

    public JMenuItem getItemBuscarUsuario() {
        return itemBuscarUsuario;
    }

    public JMenuItem getItemEliminarUsuario() {
        return itemEliminarUsuario;
    }

    public JMenuItem getItemListarUsuarios() {
        return itemListarUsuarios;
    }

    public JMenuItem getItemModificarUsuario() {
        return itemModificarUsuario;
    }

    public JMenu getMenuUsuarios() {
        return menuUsuarios;
    }
    
    // Bibliotecario

    public JMenuItem getItemAgregarBibliotecario() {
        return itemAgregarBibliotecario;
    }

    public JMenuItem getItemBuscarBibliotecario() {
        return itemBuscarBibliotecario;
    }

    public JMenuItem getItemEliminarBibliotecario() {
        return itemEliminarBibliotecario;
    }

    public JMenuItem getItemListarBibliotecarios() {
        return itemListarBibliotecarios;
    }

    public JMenuItem getItemModificarBibliotecario() {
        return itemModificarBibliotecario;
    }

    public JMenu getMenuBibliotecarios() {
        return menuBibliotecarios;
    }
    
    // Autores

    public JMenuItem getItemAgregarAutor() {
        return itemAgregarAutor;
    }

    public JMenuItem getItemBuscarAutor() {
        return itemBuscarAutor;
    }

    public JMenuItem getItemEliminarAutor() {
        return itemEliminarAutor;
    }

    public JMenuItem getItemListarAutores() {
        return itemListarAutores;
    }

    public JMenuItem getItemModificarAutor() {
        return itemModificarAutor;
    }

    public JMenu getMenuAutores() {
        return menuAutores;
    }

    
    // Libros

    public JMenuItem getItemAgregarLibro() {
        return itemAgregarLibro;
    }

    public JMenuItem getItemBuscarLibro() {
        return itemBuscarLibro;
    }
    
    public JMenuItem getItemEliminarLibro() {
        return itemEliminarLibro;
    }

    public JMenuItem getItemListarLibro() {
        return itemListarLibro;
    }

    public JMenuItem getItemModificarLibro() {
        return itemModificarLibro;
    }

    public JMenu getMenuLibros() {
        return menuLibros;
    }

    // Prestamos

    public JMenuItem getItemBuscarPrestamo() {
        return itemBuscarPrestamo;
    }

    public JMenuItem getItemListarPrestamos() {
        return itemListarPrestamos;
    }

    public JMenuItem getItemRegistrarDevolucion() {
        return itemRegistrarDevolucion;
    }

    public JMenuItem getItemRegistrarPrestamo() {
        return itemRegistrarPrestamo;
    }

    public JMenu getMenuPrestamos() {
        return menuPrestamos;
    }

    
    // Sanciones

    public JMenuItem getItemBuscarSancion() {
        return itemBuscarSancion;
    }

    public JMenuItem getItemListarSanciones() {
        return itemListarSanciones;
    }


    public JMenuItem getItemPagarMulta() {
        return itemPagarMulta;
    }

    public JMenu getMenuSanciones() {
        return menuSanciones;
    }

    
    
    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    public void actualizarIdioma(ResourceBundle bundle){
        
        labelTitulo.setText(bundle.getString("titulo.VentanaPrincipal"));
        labelFrase.setText(bundle.getString("frase.VentanPrincipal"));
        
        btnRegistrarPrestamo.setText(bundle.getString("btn.RegistrarPrestamo"));
        btnBuscarUsuario.setText(bundle.getString("btn.BuscarUsuario"));
        btnBuscarLibro.setText(bundle.getString("btn.BuscarLibro"));
        bntPagarMulta.setText(bundle.getString("btn.PagarMulta"));
        
        menuUsuarios.setText(bundle.getString("tituloMenu.Usuarios"));
        menuBibliotecarios.setText(bundle.getString("tituloMenu.Bibliotecarios"));
        menuAutores.setText(bundle.getString("tituloMenu.Autores"));
        menuLibros.setText(bundle.getString("tituloMenu.Libros"));
        menuPrestamos.setText(bundle.getString("tituloMenu.Prestamos"));
        menuSanciones.setText(bundle.getString("tituloMenu.Sanciones"));
        
        itemBuscarUsuario.setText(bundle.getString("menuItem.BuscarUuario"));
        itemAgregarUsuario.setText(bundle.getString("menuItem.AgregarUsuario"));
        itemEliminarUsuario.setText(bundle.getString("menuItem.EliminarUsuario"));
        itemModificarUsuario.setText(bundle.getString("menuItem.ModificarUsuario"));
        itemListarUsuarios.setText(bundle.getString("menuItem.ListarUsuarios"));
        
        itemBuscarBibliotecario.setText(bundle.getString("menuItem.BuscarBibliotecario"));
        itemAgregarBibliotecario.setText(bundle.getString("menuItem.AgregarBibliotecario"));
        itemEliminarBibliotecario.setText(bundle.getString("menuItem.EliminarBibliotecario"));
        itemModificarBibliotecario.setText(bundle.getString("menuItem.ModificarBibliotecario"));
        itemListarBibliotecarios.setText(bundle.getString("menuItem.ListarBibliotecarios"));
        
        itemBuscarAutor.setText(bundle.getString("menuItem.BuscarAutor"));
        itemAgregarAutor.setText(bundle.getString("menuItem.AgregarAutor"));
        itemEliminarAutor.setText(bundle.getString("menuItem.EliminarAutor"));
        itemModificarAutor.setText(bundle.getString("menuItem.ModificarAutor"));
        itemListarAutores.setText(bundle.getString("menuItem.ListarAutores"));
        
        itemBuscarLibro.setText(bundle.getString("menuItem.BuscarLibro"));
        itemAgregarLibro.setText(bundle.getString("menuItem.AgregarLibro"));
        itemEliminarLibro.setText(bundle.getString("menuItem.EliminarLibro"));
        itemModificarLibro.setText(bundle.getString("menuItem.ModificarLibro"));
        itemListarLibro.setText(bundle.getString("menuItem.ListarLibros"));
        
        itemBuscarPrestamo.setText(bundle.getString("menuItem.BuscarPrestamo"));
        itemRegistrarPrestamo.setText(bundle.getString("menuItem.RegistrarPrestamo"));
        itemRegistrarDevolucion.setText(bundle.getString("menuItem.RegistrarDevolucion"));
        itemListarPrestamos.setText(bundle.getString("menuItem.ListarPrestamos"));
        
        itemBuscarSancion.setText(bundle.getString("menuItem.BuscarSancion"));
        itemPagarMulta.setText(bundle.getString("menuItem.PagarMulta"));
        itemListarSanciones.setText(bundle.getString("menuItem.ListarSanciones"));
    }

    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelFrase = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrarPrestamo = new javax.swing.JButton();
        bntPagarMulta = new javax.swing.JButton();
        btnBuscarUsuario = new javax.swing.JButton();
        btnBuscarLibro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuUsuarios = new javax.swing.JMenu();
        itemBuscarUsuario = new javax.swing.JMenuItem();
        itemAgregarUsuario = new javax.swing.JMenuItem();
        itemEliminarUsuario = new javax.swing.JMenuItem();
        itemModificarUsuario = new javax.swing.JMenuItem();
        itemListarUsuarios = new javax.swing.JMenuItem();
        menuBibliotecarios = new javax.swing.JMenu();
        itemBuscarBibliotecario = new javax.swing.JMenuItem();
        itemAgregarBibliotecario = new javax.swing.JMenuItem();
        itemEliminarBibliotecario = new javax.swing.JMenuItem();
        itemModificarBibliotecario = new javax.swing.JMenuItem();
        itemListarBibliotecarios = new javax.swing.JMenuItem();
        menuAutores = new javax.swing.JMenu();
        itemBuscarAutor = new javax.swing.JMenuItem();
        itemAgregarAutor = new javax.swing.JMenuItem();
        itemEliminarAutor = new javax.swing.JMenuItem();
        itemModificarAutor = new javax.swing.JMenuItem();
        itemListarAutores = new javax.swing.JMenuItem();
        menuLibros = new javax.swing.JMenu();
        itemBuscarLibro = new javax.swing.JMenuItem();
        itemAgregarLibro = new javax.swing.JMenuItem();
        itemEliminarLibro = new javax.swing.JMenuItem();
        itemModificarLibro = new javax.swing.JMenuItem();
        itemListarLibro = new javax.swing.JMenuItem();
        menuPrestamos = new javax.swing.JMenu();
        itemBuscarPrestamo = new javax.swing.JMenuItem();
        itemRegistrarPrestamo = new javax.swing.JMenuItem();
        itemRegistrarDevolucion = new javax.swing.JMenuItem();
        itemListarPrestamos = new javax.swing.JMenuItem();
        menuSanciones = new javax.swing.JMenu();
        itemBuscarSancion = new javax.swing.JMenuItem();
        itemPagarMulta = new javax.swing.JMenuItem();
        itemListarSanciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/upsLogo.png"))); // NOI18N

        labelTitulo.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 48)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("SISTEMA BIBLIOTECARIO UPS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        desktopPane.add(jPanel1);
        jPanel1.setBounds(0, 0, 840, 130);

        labelFrase.setBackground(new java.awt.Color(255, 255, 255));
        labelFrase.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelFrase.setText("Biblioteca UPS un espacio para aprender, investigar y crecer.");
        desktopPane.add(labelFrase);
        labelFrase.setBounds(130, 170, 420, 60);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/CatedralDiseño.png"))); // NOI18N
        desktopPane.add(jLabel4);
        jLabel4.setBounds(0, 150, 350, 530);

        btnRegistrarPrestamo.setText("REGISTRAR PRESTAMO");
        desktopPane.add(btnRegistrarPrestamo);
        btnRegistrarPrestamo.setBounds(380, 250, 420, 60);

        bntPagarMulta.setText("PAGAR MULTA");
        desktopPane.add(bntPagarMulta);
        bntPagarMulta.setBounds(380, 340, 420, 60);

        btnBuscarUsuario.setText("BUSCAR USUARIO");
        desktopPane.add(btnBuscarUsuario);
        btnBuscarUsuario.setBounds(380, 430, 420, 60);

        btnBuscarLibro.setText("BUSCAR LIBRO");
        desktopPane.add(btnBuscarLibro);
        btnBuscarLibro.setBounds(380, 510, 420, 60);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaAzul.png"))); // NOI18N
        desktopPane.add(jLabel5);
        jLabel5.setBounds(240, 130, 580, 550);

        menuUsuarios.setMnemonic('f');
        menuUsuarios.setText("Usuarios");

        itemBuscarUsuario.setMnemonic('o');
        itemBuscarUsuario.setText("Buscar Usuario");
        menuUsuarios.add(itemBuscarUsuario);

        itemAgregarUsuario.setMnemonic('s');
        itemAgregarUsuario.setText("Agregar Usuario");
        menuUsuarios.add(itemAgregarUsuario);

        itemEliminarUsuario.setMnemonic('a');
        itemEliminarUsuario.setText("Eliminar Usuario");
        menuUsuarios.add(itemEliminarUsuario);

        itemModificarUsuario.setMnemonic('x');
        itemModificarUsuario.setText("Modificar Usuario");
        itemModificarUsuario.addActionListener(this::itemModificarUsuarioActionPerformed);
        menuUsuarios.add(itemModificarUsuario);

        itemListarUsuarios.setText("Listar Usuarios");
        itemListarUsuarios.addActionListener(this::itemListarUsuariosActionPerformed);
        menuUsuarios.add(itemListarUsuarios);

        menuBar.add(menuUsuarios);

        menuBibliotecarios.setMnemonic('e');
        menuBibliotecarios.setText("Bibliotecarios");

        itemBuscarBibliotecario.setMnemonic('t');
        itemBuscarBibliotecario.setText("Buscar Bibliotecario");
        menuBibliotecarios.add(itemBuscarBibliotecario);

        itemAgregarBibliotecario.setMnemonic('y');
        itemAgregarBibliotecario.setText("Agregar Bibliotecario");
        menuBibliotecarios.add(itemAgregarBibliotecario);

        itemEliminarBibliotecario.setMnemonic('p');
        itemEliminarBibliotecario.setText("Eliminar Bibliotecario");
        menuBibliotecarios.add(itemEliminarBibliotecario);

        itemModificarBibliotecario.setMnemonic('d');
        itemModificarBibliotecario.setText("Modificar Bibliotecario");
        menuBibliotecarios.add(itemModificarBibliotecario);

        itemListarBibliotecarios.setText("Listar Bibliotecarios");
        menuBibliotecarios.add(itemListarBibliotecarios);

        menuBar.add(menuBibliotecarios);

        menuAutores.setMnemonic('h');
        menuAutores.setText("Autores");

        itemBuscarAutor.setMnemonic('c');
        itemBuscarAutor.setText("Buscar Autor");
        menuAutores.add(itemBuscarAutor);

        itemAgregarAutor.setMnemonic('a');
        itemAgregarAutor.setText("Agregar Autor");
        menuAutores.add(itemAgregarAutor);

        itemEliminarAutor.setText("Eliminar Autor");
        menuAutores.add(itemEliminarAutor);

        itemModificarAutor.setText("Modificar Autor");
        menuAutores.add(itemModificarAutor);

        itemListarAutores.setText("Listar Autores");
        menuAutores.add(itemListarAutores);

        menuBar.add(menuAutores);

        menuLibros.setText("Libros");

        itemBuscarLibro.setText("Buscar Libro");
        menuLibros.add(itemBuscarLibro);

        itemAgregarLibro.setText("Agregar Libro");
        menuLibros.add(itemAgregarLibro);

        itemEliminarLibro.setText("Eliminar Libro");
        menuLibros.add(itemEliminarLibro);

        itemModificarLibro.setText("Modificar Libro");
        menuLibros.add(itemModificarLibro);

        itemListarLibro.setText("Listar Libros");
        menuLibros.add(itemListarLibro);

        menuBar.add(menuLibros);

        menuPrestamos.setText("Prestamos");

        itemBuscarPrestamo.setText("Buscar Préstamo");
        menuPrestamos.add(itemBuscarPrestamo);

        itemRegistrarPrestamo.setText("Registrar Préstamo");
        menuPrestamos.add(itemRegistrarPrestamo);

        itemRegistrarDevolucion.setText("Registrar Devolución");
        menuPrestamos.add(itemRegistrarDevolucion);

        itemListarPrestamos.setText("Listar Prestamos");
        menuPrestamos.add(itemListarPrestamos);

        menuBar.add(menuPrestamos);

        menuSanciones.setText("Sanciones");

        itemBuscarSancion.setText("Buscar Sanción");
        menuSanciones.add(itemBuscarSancion);

        itemPagarMulta.setText("Pagar Multa");
        menuSanciones.add(itemPagarMulta);

        itemListarSanciones.setText("Listar Sanciones");
        menuSanciones.add(itemListarSanciones);

        menuBar.add(menuSanciones);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarUsuarioActionPerformed
        System.exit(0);
    }//GEN-LAST:event_itemModificarUsuarioActionPerformed

    private void itemListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListarUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemListarUsuariosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntPagarMulta;
    private javax.swing.JButton btnBuscarLibro;
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnRegistrarPrestamo;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem itemAgregarAutor;
    private javax.swing.JMenuItem itemAgregarBibliotecario;
    private javax.swing.JMenuItem itemAgregarLibro;
    private javax.swing.JMenuItem itemAgregarUsuario;
    private javax.swing.JMenuItem itemBuscarAutor;
    private javax.swing.JMenuItem itemBuscarBibliotecario;
    private javax.swing.JMenuItem itemBuscarLibro;
    private javax.swing.JMenuItem itemBuscarPrestamo;
    private javax.swing.JMenuItem itemBuscarSancion;
    private javax.swing.JMenuItem itemBuscarUsuario;
    private javax.swing.JMenuItem itemEliminarAutor;
    private javax.swing.JMenuItem itemEliminarBibliotecario;
    private javax.swing.JMenuItem itemEliminarLibro;
    private javax.swing.JMenuItem itemEliminarUsuario;
    private javax.swing.JMenuItem itemListarAutores;
    private javax.swing.JMenuItem itemListarBibliotecarios;
    private javax.swing.JMenuItem itemListarLibro;
    private javax.swing.JMenuItem itemListarPrestamos;
    private javax.swing.JMenuItem itemListarSanciones;
    private javax.swing.JMenuItem itemListarUsuarios;
    private javax.swing.JMenuItem itemModificarAutor;
    private javax.swing.JMenuItem itemModificarBibliotecario;
    private javax.swing.JMenuItem itemModificarLibro;
    private javax.swing.JMenuItem itemModificarUsuario;
    private javax.swing.JMenuItem itemPagarMulta;
    private javax.swing.JMenuItem itemRegistrarDevolucion;
    private javax.swing.JMenuItem itemRegistrarPrestamo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelFrase;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JMenu menuAutores;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuBibliotecarios;
    private javax.swing.JMenu menuLibros;
    private javax.swing.JMenu menuPrestamos;
    private javax.swing.JMenu menuSanciones;
    private javax.swing.JMenu menuUsuarios;
    // End of variables declaration//GEN-END:variables

}
