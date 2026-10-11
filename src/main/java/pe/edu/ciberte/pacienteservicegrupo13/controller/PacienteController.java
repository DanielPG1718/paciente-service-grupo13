package pe.edu.ciberte.pacienteservicegrupo13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.ciberte.pacienteservicegrupo13.dto.DiagnosticoDTO;
import pe.edu.ciberte.pacienteservicegrupo13.dto.PacienteHistorialDTO;
import pe.edu.ciberte.pacienteservicegrupo13.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{pacienteId}")
    public PacienteHistorialDTO obtenerPaciente(@PathVariable("pacienteId") Long pacienteId) {
        return pacienteService.obtenerPaciente(pacienteId);
    }

    @GetMapping("/{pacienteId}/diagnosticos")
    public List<DiagnosticoDTO> obtenerDiagnosticos(@PathVariable("pacienteId") Long pacienteId) {
        return pacienteService.obtenerDiagnosticos(pacienteId);
    }
}
