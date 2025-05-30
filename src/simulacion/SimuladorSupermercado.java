package simulacion;

import modelo.*;
import java.util.*;
import java.util.logging.*;

public class SimuladorSupermercado {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(SimuladorSupermercado.class.getName());
        configurarLogger();

        List<Producto> productos = Arrays.asList(
                new Producto("Pan", 1.0, 500),
                new Producto("Leche", 2.0, 1000),
                new Producto("Huevos", 3.5, 800),
                new Producto("Cereal", 4.0, 1200)
        );

        Cliente c1 = new Cliente("Ana", productos);
        Cliente c2 = new Cliente("Luis", productos);
        Cliente c3 = new Cliente("Camila", productos);

        long inicio = System.currentTimeMillis();

        Cajera cajera1 = new Cajera("Cajera 1", c1, inicio);
        Cajera cajera2 = new Cajera("Cajera 2", c2, inicio);
        Cajera cajera3 = new Cajera("Cajera 3", c3, inicio);

        cajera1.start();
        cajera2.start();
        cajera3.start();
    }

    private static void configurarLogger() {
        try {
            LogManager.getLogManager().reset();
            Logger rootLogger = Logger.getLogger("");
            FileHandler fh = new FileHandler("logs/supermercado.log");
            fh.setFormatter(new SimpleFormatter());
            rootLogger.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
