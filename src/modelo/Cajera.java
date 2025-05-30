package modelo;

import java.util.logging.Logger;

public class Cajera extends Thread {
    private String nombre;
    private Cliente cliente;
    private long tiempoInicio;
    private static final Logger logger = Logger.getLogger(Cajera.class.getName());

    public Cajera(String nombre, Cliente cliente, long tiempoInicio) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.tiempoInicio = tiempoInicio;
    }

    @Override
    public void run() {
        logger.info(nombre + " comienza a procesar a " + cliente.getNombre());
        double total = 0;

        for (Producto p : cliente.getProductos()) {
            try {
                Thread.sleep(p.getTiempoProcesamiento());
            } catch (InterruptedException e) {
                logger.warning("Error al procesar producto: " + e.getMessage());
            }
            total += p.getPrecio();
            logger.info(nombre + " procesó " + p.getNombre() + " en " + p.getTiempoProcesamiento() + "ms");
        }

        long tiempoTotal = System.currentTimeMillis() - tiempoInicio;
        logger.info(nombre + " terminó con " + cliente.getNombre() + " en " + tiempoTotal + "ms - Total: $" + total);
    }
}
