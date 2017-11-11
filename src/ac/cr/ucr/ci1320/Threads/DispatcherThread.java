package ac.cr.ucr.ci1320.Threads;
import ac.cr.ucr.ci1320.Connection.Server;

/**
 * Universidad de Costa Rica
 * Facultad de Ingeniería
 * Escuela de Ciencias de la Computación e Informática
 * Profesora: Gabriela Barrantes
 * Autores:
 * Abellán Jiménez Mariana B50031
 * Brenes Solano Silvia B41133
 * Cubero Sánchez Josué B42190
 * Garita Centeno Alonso B42791
 */

public class DispatcherThread implements Runnable {
    private Server server;

    public DispatcherThread(Server server){
        this.server = server;
    }

    @Override
    public void run(){
        try {
            this.server.startServerDispatcher();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

