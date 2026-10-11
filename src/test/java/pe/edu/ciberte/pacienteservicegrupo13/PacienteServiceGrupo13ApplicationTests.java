package pe.edu.ciberte.pacienteservicegrupo13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "eureka.client.enabled=false")
class PacienteServiceGrupo13ApplicationTests {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Test
    void consultaPacienteConHistorialAnidado() throws Exception {
        HttpResponse<String> response = consultar("/api/v1/pacientes/1");

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"pacienteId\":1"));
        assertTrue(response.body().contains("\"tipoSeguro\":\"EPS\""));
        assertTrue(response.body().contains("\"codigoCIE10\":\"J18.9\""));
        assertTrue(response.body().contains("\"gravedad\":\"SEVERO\""));
    }

    @Test
    void consultaDiagnosticosDelPaciente() throws Exception {
        HttpResponse<String> response = consultar("/api/v1/pacientes/2/diagnosticos");

        assertEquals(200, response.statusCode());
        assertTrue(response.body().startsWith("["));
        assertTrue(response.body().contains("\"codigoCIE10\":\"J06.9\""));
        assertTrue(response.body().contains("\"gravedad\":\"LEVE\""));
    }

    @Test
    void pacienteInexistenteResponde404() throws Exception {
        assertEquals(404, consultar("/api/v1/pacientes/3").statusCode());
    }

    private HttpResponse<String> consultar(String ruta) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + ruta))
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
