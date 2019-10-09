@Noticias
Feature: Pagina el colombiano
  As
    usuario no registrado
  I want
    buscar noticias por parametros segun el ejercicio expuesto.

  @BuscarNoticia
  Scenario: Buscar noticias en pagina de inicio
    Given que me encuentro en la  seccion de noticias
    When selecciono la noticia "004"
    Then imprimo reportes