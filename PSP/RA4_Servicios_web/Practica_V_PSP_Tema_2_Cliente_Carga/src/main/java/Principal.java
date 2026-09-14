import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Principal {
    private static final int TOTAL = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        HttpClient cliente = HttpClient.newHttpClient();
        AtomicLong tiempoTotal = new AtomicLong();
        AtomicInteger errores = new AtomicInteger();

        for (int i = 0; i < TOTAL; i++) {
            pool.submit(() -> {
                long inicio = System.currentTimeMillis();
                try {
                    HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/productos"))
                        .GET().build();
                    HttpResponse<String> res = cliente.send(
                        req, HttpResponse.BodyHandlers.ofString());
                    if (res.statusCode() >= 400) errores.incrementAndGet();
                } catch (Exception e) {
                    errores.incrementAndGet();
                } finally {
                    tiempoTotal.addAndGet(
                        System.currentTimeMillis() - inicio);
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.MINUTES);
        System.out.println("Tiempo medio: " +
            tiempoTotal.get() / TOTAL + " ms");
        System.out.println("Errores: " + errores.get());
    }
}
