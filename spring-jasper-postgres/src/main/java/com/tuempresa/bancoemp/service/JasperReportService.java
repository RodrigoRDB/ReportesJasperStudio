package com.tuempresa.bancoemp.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Map;

@Service
public class JasperReportService {

    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;

    public JasperReportService(DataSource dataSource, ResourceLoader resourceLoader) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
    }

    // Compilar JRXML desde resources/reports/
    public JasperReport compileReport(String jrxmlFileName) throws JRException {
        try {
            Resource res = resourceLoader.getResource("classpath:reports/" + jrxmlFileName);
            try (InputStream is = res.getInputStream()) {
                return JasperCompileManager.compileReport(is);
            }
        } catch (Exception ex) {
            throw new JRException("Error compilando JRXML: " + jrxmlFileName, ex);
        }
    }

    // Generar PDF usando conexión JDBC
    public byte[] generatePdf(String jrxmlFileName, Map<String, Object> params) throws Exception {
        JasperReport jasperReport = compileReport(jrxmlFileName);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }

    // Generar HTML en String (para visualizar)
    public String generateHtml(String jrxmlFileName, Map<String, Object> params) throws Exception {
        JasperReport jasperReport = compileReport(jrxmlFileName);
        try (Connection conn = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            HtmlExporter exporter = new HtmlExporter();
            ByteArrayOutputStream htmlStream = new ByteArrayOutputStream();

            SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
            exporter.setConfiguration(configuration);

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(htmlStream, StandardCharsets.UTF_8.name()));

            exporter.exportReport();

            return htmlStream.toString(StandardCharsets.UTF_8.name());
        }
    }
}
