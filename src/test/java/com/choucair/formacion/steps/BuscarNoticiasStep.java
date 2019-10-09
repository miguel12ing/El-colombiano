package com.choucair.formacion.steps;

import au.com.bytecode.opencsv.CSVReader;
import com.choucair.formacion.pageobjects.BuscarNoticiasPage;
import net.thucydides.core.annotations.Step;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class BuscarNoticiasStep {
    BuscarNoticiasPage buscarNoticiasPage;

    String[] datos;

    public void leerCSV(String id) {
        au.com.bytecode.opencsv.CSVReader reader;
        try {
            reader = new CSVReader(new FileReader("src/test/resources/Datadriven/basededatos.csv"));

            String[] fila;
            while ((fila = reader.readNext()) != null) {
                Logger.getLogger(fila[0]);
                if (id.equals(fila[0].trim())) {
                    datos = fila;
                }
            }
            reader.close();
        } catch (IOException e) {
            Logger.getLogger("" + e);
        }
    }


    @Step
    public void abrirExplorador() {
        buscarNoticiasPage.open();
        buscarNoticiasPage.navegarMenu();
    }

    public void selecionadorNoticia(String id) {
        leerCSV(id);
        buscarNoticiasPage.seleccionarNoticia(datos[1]);
    }

    public void busquedaNoticiaParticular(String id) {
        leerCSV(id);
        buscarNoticiasPage.BusquedaNoticia(datos[2]);
        buscarNoticiasPage.fechaInicial(datos[5], datos[4]);
        buscarNoticiasPage.fechaFinal(datos[8], datos[7]);

    }

    public void paginacion(String id) {
        leerCSV(id);
        buscarNoticiasPage.paginadorNoticia(datos[9],datos[10]);
    }

    public void obtenerReportes() {
        buscarNoticiasPage.sacarReportes();
    }
}


