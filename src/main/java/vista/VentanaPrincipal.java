package vista;

import analizadorSintactico.Lexico;
import analizadorSintactico.parser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VentanaPrincipal extends javax.swing.JFrame {

    public static StringBuilder textoConsola = new StringBuilder();
    public static boolean error = false;
    /**
     * Creates new form TextFrame
     */
    private boolean modificado = false;
    private String tituloVentana = "Nuevo archivo - Analizador Sintáctico";
    private JFileChooser archivoActual = new JFileChooser();
    
    public VentanaPrincipal() {
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Nuevo archivo - Analizador Sintáctico");
        setVisible(true);
        setSize(1000,540);
        setResizable(false);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int cerrar = cerrarPrograma();
                switch(cerrar){
                    case 0 -> {
                        // No pasa nada
                    }
                    case 1 -> dispose();
                }
            }
        });
        
        panelTexto.addCaretListener((CaretEvent e) -> {
            int posicion = panelTexto.getCaretPosition();
            int linea = panelTexto.getDocument().getDefaultRootElement().getElementIndex(posicion) + 1;
            int columna = posicion - panelTexto.getDocument().getDefaultRootElement().getElement(linea - 1).getStartOffset() + 1;
            lnCol.setText(" Ln: " + linea + " Col: " + columna);
        });
        
        panelTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                modificado = true;
                setTitle("*" + tituloVentana);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                modificado = true;
                setTitle("*" + tituloVentana);
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                modificado = true;
                setTitle("*" + tituloVentana);
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelTexto = new javax.swing.JEditorPane();
        jToolBar1 = new javax.swing.JToolBar();
        lnCol = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        barraMenu = new javax.swing.JMenuBar();
        botonArchivo = new javax.swing.JMenu();
        bArchivoNuevo = new javax.swing.JMenuItem();
        bArchivoAbrir = new javax.swing.JMenuItem();
        bArchivoGuardar = new javax.swing.JMenuItem();
        bArchivoGuardarComo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        bAnalizar = new javax.swing.JMenuItem();
        bArchivoSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(panelTexto);

        jToolBar1.setBorder(null);
        jToolBar1.setRollover(true);
        jToolBar1.setFocusable(false);
        jToolBar1.setEnabled(false);

        lnCol.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lnCol.setText(" Ln 1  Col 1 ");
        jToolBar1.add(lnCol);

        consola.setEditable(false);
        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane2.setViewportView(consola);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        botonArchivo.setText("Archivo");
        botonArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonArchivoActionPerformed(evt);
            }
        });

        bArchivoNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bArchivoNuevo.setText("Nuevo");
        bArchivoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArchivoNuevoActionPerformed(evt);
            }
        });
        botonArchivo.add(bArchivoNuevo);

        bArchivoAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bArchivoAbrir.setText("Abrir...");
        bArchivoAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArchivoAbrirActionPerformed(evt);
            }
        });
        botonArchivo.add(bArchivoAbrir);

        bArchivoGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bArchivoGuardar.setText("Guardar");
        bArchivoGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArchivoGuardarActionPerformed(evt);
            }
        });
        botonArchivo.add(bArchivoGuardar);

        bArchivoGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bArchivoGuardarComo.setText("Guardar como...");
        bArchivoGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArchivoGuardarComoActionPerformed(evt);
            }
        });
        botonArchivo.add(bArchivoGuardarComo);
        botonArchivo.add(jSeparator1);

        bAnalizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bAnalizar.setText("Analizar");
        bAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnalizarActionPerformed(evt);
            }
        });
        botonArchivo.add(bAnalizar);

        bArchivoSalir.setText("Salir");
        bArchivoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bArchivoSalirActionPerformed(evt);
            }
        });
        botonArchivo.add(bArchivoSalir);

        barraMenu.add(botonArchivo);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonArchivoActionPerformed

    private void bArchivoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArchivoSalirActionPerformed
        int cerrar = cerrarPrograma();
        if (cerrar == 1){
            dispose();
        }
    }//GEN-LAST:event_bArchivoSalirActionPerformed

    private void bAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnalizarActionPerformed
        consola.setText("");
        if (archivoActual.getSelectedFile() == null){
            int guardado = guardarArchivoComo();
            if (guardado == 1){
                return;
            }
        }
        try {
            FileReader lectorArchivo = new FileReader(archivoActual.getSelectedFile().getPath());
            Lexico lexer = new Lexico(lectorArchivo);
            //TODO hacer que Lexer retorne valores y a partir de ahi hacer cosas
            parser sintactico = new parser(lexer);
            sintactico.parse();
            if (error){
                consola.setText(textoConsola.toString());
            }else {
                consola.setText(textoConsola.toString());
                textoConsola =new StringBuilder();}

        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Error ex) {
            consola.append("Error: " + ex.getMessage() + "\n");
        } catch (Exception e) {
            consola.append("Error: " + e.getMessage() + "\n");
        }
    }//GEN-LAST:event_bAnalizarActionPerformed

    private void bArchivoGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArchivoGuardarComoActionPerformed
        guardarArchivoComo();
    }//GEN-LAST:event_bArchivoGuardarComoActionPerformed

    private void bArchivoGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArchivoGuardarActionPerformed
        if (archivoActual.getSelectedFile() == null){
            guardarArchivoComo();
        } else {
            PrintWriter buffer;
            try {
                buffer = new PrintWriter(archivoActual.getSelectedFile());
                buffer.print(panelTexto.getText());
                buffer.close();
                tituloVentana = archivoActual.getSelectedFile().getName() + " - Analizador Sintáctico";
                setTitle(tituloVentana);
                modificado = false;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bArchivoGuardarActionPerformed

    private void bArchivoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArchivoAbrirActionPerformed
        if (modificado){
            int opcion = popupCambios();
            switch (opcion){
                case 0 -> {
                    // Abrir ventana para elegir donde guardar el archivo y abrir el archivo
                    int i = guardarArchivoComo();
                    if (i == 0) {
                        // Si no se cancela el guardado del archivo, se abre el archivo deseado
                        abrirArchivo();
                    }
                }
                case 1 -> abrirArchivo(); // Abre el archivo sin guardar lo anterior
                case 2, -1 -> {
                    // No hacer nada
                }
            }
        } else abrirArchivo();

    }//GEN-LAST:event_bArchivoAbrirActionPerformed

    public void actualizar(String s){
        consola.append(s);
    }

    private void bArchivoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bArchivoNuevoActionPerformed
        if (modificado){
            int opcion = popupCambios();
            switch (opcion){
                case 0 -> {
                    // Abrir ventana para elegir donde guardar el archivo
                    int i = guardarArchivoComo();
                    if (i == 0){
                        nuevoArchivo();
                    }
                }
                case 1 -> nuevoArchivo(); // Limpiar el editor de texto sin guardar nada

            }
        } else {
            nuevoArchivo();
        }
    }//GEN-LAST:event_bArchivoNuevoActionPerformed

    private void nuevoArchivo() {
        panelTexto.setText("");
        archivoActual = new JFileChooser();
        tituloVentana = "Nuevo archivo - Analizador Sintáctico";
        setTitle(tituloVentana);
        modificado = false;
    }
    
    private void abrirArchivo() {
        int opcion = archivoActual.showOpenDialog(barraMenu);
        if (opcion == JFileChooser.APPROVE_OPTION){
            try {
                Scanner archivo = new Scanner(archivoActual.getSelectedFile());
                String buffer = "";
                while (archivo.hasNext()){
                    buffer += archivo.nextLine() + "\n";
                }
                panelTexto.setText(buffer);
                tituloVentana = archivoActual.getSelectedFile().getName() + " - Analizador Sintáctico";
                setTitle(tituloVentana);
                modificado = false;
            } catch (FileNotFoundException ex){
                JOptionPane.showMessageDialog(rootPane, "El archivo no se pudo encontrar.");
            }
        }
    }
    
    private int guardarArchivoComo(){
        int opcion = archivoActual.showSaveDialog(barraMenu);
        if (opcion == JFileChooser.APPROVE_OPTION){
            try {
                PrintWriter buffer = new PrintWriter(archivoActual.getSelectedFile());
                buffer.print(panelTexto.getText());
                buffer.close();
                tituloVentana = archivoActual.getSelectedFile().getName() + " - Analizador Sintáctico";
                setTitle(tituloVentana);
                modificado = false;
                return 0;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 1;
    }
    
    private int popupCambios(){
        Object [] opciones = {"Si", "No", "Cancelar"};
        return JOptionPane.showOptionDialog(null, "Hay cambios sin guardar. ¿Deseas guardarlos?", "Analizador Sintáctico", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
    }
    
    private int cerrarPrograma(){
        if (modificado){
            int opcion = popupCambios();
            switch (opcion){
                case 0 -> {
                    int i = guardarArchivoComo();
                    if (i == 0){
                        return 1; // Se guardó el archivo, se cierra
                    } else return 0; // Se canceló el guardado, no se cierra
                }
                case 1 -> {
                    return 1; // Se eligió no guardar el archivo, se cierra
                }
                case 2, -1 -> {
                    return 0; // Se eligió cancelar, no se cierra
                }
            }
        }
        return 1; // No hay cambios, se cierra
    }
        

    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bAnalizar;
    private javax.swing.JMenuItem bArchivoAbrir;
    private javax.swing.JMenuItem bArchivoGuardar;
    private javax.swing.JMenuItem bArchivoGuardarComo;
    private javax.swing.JMenuItem bArchivoNuevo;
    private javax.swing.JMenuItem bArchivoSalir;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenu botonArchivo;
    private javax.swing.JTextArea consola;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lnCol;
    private javax.swing.JEditorPane panelTexto;
    // End of variables declaration//GEN-END:variables
}
