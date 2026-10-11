package pe.edu.ciberte.pacienteservicegrupo13.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.ciberte.pacienteservicegrupo13.dto.DiagnosticoDTO;
import pe.edu.ciberte.pacienteservicegrupo13.dto.PacienteHistorialDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PacienteService {
    private final Map<Long, PacienteHistorialDTO> pacientes = Map.of(
            1L, new PacienteHistorialDTO(1L, "00000001", "Ana Torres", "EPS",
                    List.of(new DiagnosticoDTO(1L, "J18.9", "Neumonía no especificada",
                            LocalDateTime.of(2026, 10, 1, 10, 0), "SEVERO")), "ACTIVO"),
            2L, new PacienteHistorialDTO(2L, "00000002", "Luis Vega", "ESSALUD",
                    List.of(new DiagnosticoDTO(2L, "J06.9", "Infección respiratoria aguda no especificada",
                            LocalDateTime.of(2026, 10, 2, 11, 30), "LEVE")), "ACTIVO"),
            3L, new PacienteHistorialDTO(3L, "00000003", "Rosa Díaz", "PARTICULAR",
                    List.of(new DiagnosticoDTO(3L, "M54.5", "Lumbago no especificado",
                            LocalDateTime.of(2026, 10, 3, 9, 15), "MODERADO")), "ACTIVO")
    );

    public PacienteHistorialDTO obtenerPaciente(Long pacienteId) {
        PacienteHistorialDTO paciente = pacientes.get(pacienteId);
        if (paciente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }
        return paciente;
    }

    public List<DiagnosticoDTO> obtenerDiagnosticos(Long pacienteId) {
        return obtenerPaciente(pacienteId).getListaDiagnosticosActivos();
    }
}
