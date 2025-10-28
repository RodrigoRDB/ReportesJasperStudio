package com.FinanGlobal.bancoemp.controller;


import com.FinanGlobal.bancoemp.service.JasperReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    private final JasperReportService jasperService;

    public ReportController(JasperReportService jasperService) {
        this.jasperService = jasperService;
    }

    // Visualiza el reporte como HTML (inline)
    // ejemplo: /reporte/view?clienteId=1
    @GetMapping("/reporte/view")
    public ResponseEntity<String> viewReport(@RequestParam(required = false) Integer clienteId) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (clienteId != null) params.put("P_CLIENTE_ID", clienteId);
        // puedes añadir más params aquí

        String html = jasperService.generateHtml("Reporte1.jrxml", params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return ResponseEntity.ok().headers(headers).body(html);
    }

    // Descargar PDF (inline)
    @GetMapping("/reporte/download")
    public ResponseEntity<byte[]> downloadReport(@RequestParam(required = false) Integer clienteId) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        if (clienteId != null) params.put("P_CLIENTE_ID", clienteId);

        byte[] pdf = jasperService.generatePdf("Reporte1.jrxml", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=clientes_report.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
    //GAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    @GetMapping("/reporte/vista")
    public ResponseEntity<String> viewReport(
            @RequestParam String nombre) throws Exception {

        Map<String, Object> params = new HashMap<>();

        String html = jasperService.generateHtml(nombre + ".jrxml", params);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return ResponseEntity.ok().headers(headers).body(html);
    }

    @GetMapping("/reporte/descargar")
    public ResponseEntity<byte[]> downloadReport(
            @RequestParam String nombre) throws Exception {

        Map<String, Object> params = new HashMap<>();

        byte[] pdf = jasperService.generatePdf(nombre + ".jrxml", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + nombre + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}