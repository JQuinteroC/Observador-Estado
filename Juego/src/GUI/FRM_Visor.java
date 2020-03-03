package GUI;

import Logica.Mascota;
import Logica.Personaje;
import Logica.Poblacion;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FRM_Visor extends javax.swing.JFrame implements KeyListener {

    ArrayList<Personaje> p = new ArrayList<>();
    ArrayList<Personaje> huevos = new ArrayList<>();
    Poblacion grupo = new Poblacion("Grupo1");
    Poblacion grupo2 = new Poblacion("Grupo2");
    Reproductor[] repro = new Reproductor[2];
    Thread musica;

    public FRM_Visor(Personaje p, Personaje huevo, int cancion) {
        // Instancia de la ventana
        initComponents();
        super.setLocationRelativeTo(null);

        // Configuración del personaje y grupo
        p.setPanel(panel);
        this.p.add(p);
        this.p.add(p.clone());
        this.p.add(p.clone());
        this.p.add(p.clone());
        this.p.get(1).setDesplazamientoVertical(45);
        this.p.get(1).setDesplazamientoHorizontal(460);
        this.p.get(1).setHitbox(460, 45, this.p.get(1).getAncho(), this.p.get(1).getAlto());
        this.p.get(2).setDesplazamientoVertical(-10);
        this.p.get(2).setDesplazamientoHorizontal(530);
        this.p.get(2).setHitbox(530, -10, this.p.get(2).getAncho(), this.p.get(2).getAlto());
        this.p.get(3).setDesplazamientoVertical(-10);
        this.p.get(3).setDesplazamientoHorizontal(390);
        this.p.get(3).setHitbox(390, -10, this.p.get(3).getAncho(), this.p.get(3).getAlto());
        panel.add(this.p.get(0));
        panel.add(this.p.get(1));
        panel.add(this.p.get(2));
        panel.add(this.p.get(3));

        //se crean las poblaciones del patron Composite
        grupo.addPersonaje(this.p.get(0));
        grupo2.addPersonaje(this.p.get(1));
        grupo2.addPersonaje(this.p.get(2));
        grupo2.addPersonaje(this.p.get(3));

        //Metiendo al huevito
        huevo.setPanel(panel);
        this.huevos.add(huevo);
        this.huevos.get(0).setDesplazamientoVertical(300);
        this.huevos.get(0).setDesplazamientoHorizontal(480);
        this.huevos.get(0).setHitbox(480 - 91, 300 - 40, this.huevos.get(0).getAncho() + 192, (this.huevos.get(0).getAlto() / 2) + 125);
        panel.add(this.huevos.get(0));

        // Integración del listener 
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        repro[0] = new Reproductor1();
        repro[1] = new Reproductor2();
        repro[0].setSuccessor(repro[1]);
        repro[0].cancion = cancion;
        repro[0].start();

    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'q':
                FRM_Selector selector = null;
                try {
                    selector = FRM_Selector.getInstance();
                } catch (IOException ex) {
                    Logger.getLogger(FRM_Visor.class.getName()).log(Level.SEVERE, null, ex);
                }
                selector.setVisible(true);

                // Se interrumpe el hilo de musica
                repro[0].stop();
                repro[1].stop();
                ;
                this.dispose();
                break;
            case '+':
                boolean agregado = false;
                for (int i = 0; i < 4; i++) {
                    if (grupo.isHere(p.get(i))) {
                    } else {
                        grupo2.deletePerson(p.get(i));
                        grupo.addPersonaje(this.p.get(i));
                        agregado = true;
                        i = 10;
                    }
                }
                if (!agregado) {
                    JOptionPane.showMessageDialog(null, "No hay mas personajes para agregar");
                }

                break;
            case '-':
                agregado = false;
                for (int i = 3; i >= 0; i--) {
                    if (grupo2.isHere(p.get(i))) {
                    } else {
                        grupo.deletePerson(p.get(i));
                        grupo2.addPersonaje(this.p.get(i));
                        agregado = true;
                        i = -1;
                    }
                }
                if (!agregado) {
                    JOptionPane.showMessageDialog(null, "No hay mas personajes para agregar");
                }
                break;
            case 10:
                grupo.cambiarControl();
                break;
            default:
                grupo.operar(e);
                break;
        }
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < huevos.size(); j++) {
                if (p.get(i).getHitbox().intersects(huevos.get(j).getHitbox())) {
                    int team = 0;
                    huevos.get(j).interrumpir();
                    panel.remove(huevos.get(j));
                    huevos.remove(j);
                    p.get(i).interrumpir();
                    panel.remove(p.get(i));
                    if (grupo.isHere(p.get(i))) {
                        grupo.deletePerson(p.get(i));
                        team = 1;
                    } else {
                        grupo2.deletePerson(p.get(i));
                        team = 2;
                    }
                    Personaje mas;
                    try {
                        mas = new Mascota(p.get(i), panel);
                        p.set(i, mas);
                        if (team == 1) {
                            grupo.addPersonaje(mas);
                        } else {
                            grupo2.addPersonaje(mas);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FRM_Visor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    panel.add(p.get(i));
                    panel.repaint();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }// </editor-fold>  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setOpaque(false);

        org.jdesktop.layout.GroupLayout panelLayout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1200, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/catarata.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    private boolean colision(Personaje p, Personaje huevo) {
        System.out.println("X Duende: " + p.getDesplazamientoHorizontal() + ", Y Duende: " + p.getDesplazamientoVertical());
        System.out.println("X Huevo: " + huevo.getDesplazamientoHorizontal() + ", Y Huevo: " + huevo.getDesplazamientoVertical());
        if ((p.getDesplazamientoHorizontal() + p.getAncho() > huevo.getDesplazamientoHorizontal()) & (p.getDesplazamientoHorizontal() < huevo.getDesplazamientoHorizontal() + (huevo.getAncho())) & (p.getDesplazamientoVertical() > huevo.getDesplazamientoVertical()) & (p.getDesplazamientoVertical() < huevo.getDesplazamientoVertical() + (huevo.getAlto()))) {
            return true;
        } else {
            return false;
        }
    }
}
