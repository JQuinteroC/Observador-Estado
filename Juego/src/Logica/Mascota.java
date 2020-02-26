package Logica;

import static Logica.Personaje.panel;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Mascota extends Decorador {

    public Mascota(Personaje per, JPanel panel) throws IOException {
        super(per, panel);
        if (per.isRelacion()) { //MAGO
            setHilo(5, 5, 5, 5, 130);
        } else {  // OGRO
            setHilo(24, 18, 15, 12, 50);
        }
        setPanel(panel);
    }

    @Override
    public void paint(Graphics g) {
        try {
            g.drawRect(personaje.hitbox.x, personaje.hitbox.y, personaje.hitbox.width, personaje.hitbox.height);
            ImageIcon mascota = new ImageIcon(ImageIO.read(new File("Recursos\\PowerUp\\1.png")));
            dibujarMascota(personaje, mascota, g);
            personaje.paint(g);
        } catch (Exception ex) {

        }
    }

    @Override
    public void mover() {
        personaje.x = 0;
        if(!((personaje.tempDesplazamiento>36)&(personaje.tempDesplazamiento<41))){
            personaje.numero = 0;
        } 
        if (!personaje.hilo.isAlive()) {
            personaje.hilo.start();
        }
    }
    
    @Override
    public void saltar() {
        personaje.x = 1;
        personaje.numero = 0;
        if (!personaje.hilo.isAlive()) {
            personaje.hilo.start();
        }
    }

    @Override
    public void morir() {
        personaje.x = 2;
        personaje.numero = 0;
        if (!personaje.hilo.isAlive()) {
            personaje.hilo.start();
        }
    }

    @Override
    public void atacar() {
        personaje.x = 3;
        personaje.numero = 0;
        if (!personaje.hilo.isAlive()) {
            personaje.hilo.start();
        }
    }

    @Override
    public void setHilo(int mover, int saltar, int morir, int atacar, int sleep) {
        personaje.hilo = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        switch (personaje.x) {
                            case 0:
                                personaje.numero++;
                                switch (desplazamiento) { //Personaje Individual
                                    case 39:
                                        personaje.desplazamientoHorizontal += 24;
                                        personaje.hitbox.x += 24;
                                        desplazamiento = 0;
                                        break;
                                    case 38:
                                        personaje.desplazamientoVertical -= 24;
                                        personaje.hitbox.y -= 24;
                                        desplazamiento = 0;
                                        break;
                                    case 37:
                                        personaje.desplazamientoHorizontal -= 24;
                                        personaje.hitbox.x -= 24;
                                        desplazamiento = 0;
                                        break;
                                    case 40:
                                        personaje.desplazamientoVertical += 24;
                                        personaje.hitbox.y += 24;
                                        desplazamiento = 0;
                                        break;
                                    default:
                                        break;
                                }
                                personaje.numero = personaje.numero % mover;
                                panel.repaint();
                                personaje.hilo.sleep(sleep);
                                break;
                            case 1:
                                personaje.numero++;
                                personaje.numero = personaje.numero % saltar;
                                panel.repaint();
                                personaje.hilo.sleep(sleep);
                                break;
                            case 2:
                                personaje.numero++;
                                personaje.numero = personaje.numero % morir;
                                panel.repaint();
                                personaje.hilo.sleep(sleep);
                                break;
                            case 3:
                                personaje.numero++;
                                personaje.numero = personaje.numero % atacar;
                                panel.repaint();
                                personaje.hilo.sleep(sleep);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (java.lang.InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };
    }

    @Override
    public void interrumpir() {
        try {
            personaje.hilo.interrupt();
        } catch (Exception ex) {
            System.out.println("hilo " + personaje.hilo.getName() + " no interrumpido");
        }
    }

    public void dibujarMascota(Personaje per, ImageIcon img, Graphics g) {
        if (per.isRelacion()) {
            g.drawImage(img.getImage(), -50 + per.desplazamientoHorizontal, 50 + per.desplazamientoVertical, img.getIconWidth() / 3, img.getIconHeight() / 3, null);
        } else {
            g.drawImage(img.getImage(), 35 + per.desplazamientoHorizontal, 115 + per.desplazamientoVertical, img.getIconWidth() / 3, img.getIconHeight() / 3, null);
        }
    }
}
