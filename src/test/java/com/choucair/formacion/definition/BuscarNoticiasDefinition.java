package com.choucair.formacion.definition;

import com.choucair.formacion.steps.BuscarNoticiasStep;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class BuscarNoticiasDefinition {
    @Steps
    BuscarNoticiasStep buscarNoticiasStep;

    String casoPrueba;
    String CasoPrueba2;

    @Given("^que me encuentro en la  seccion de noticias$")
    public void que_me_encuentro_en_la_seccion_de_noticias()  {

        buscarNoticiasStep.abrirExplorador();

    }

    @When("^selecciono la noticia \"([^\"]*)\"$")
    public void selecciono_la_noticia(String id) {

        this.casoPrueba = id;
        this.CasoPrueba2 = id;

        buscarNoticiasStep.selecionadorNoticia("001");
        buscarNoticiasStep.busquedaNoticiaParticular("001");
        buscarNoticiasStep.paginacion("002");


    }

    @Then("^imprimo reportes$")
    public void imprimo_reportes() {
        buscarNoticiasStep.obtenerReportes();



    }

    }
