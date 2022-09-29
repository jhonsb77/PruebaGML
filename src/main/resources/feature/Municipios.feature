Feature: Consumo servicio municipios

  @wsMunicipios
  Scenario Outline: consumir servicio de municipios
    Given realizo el consumo del servicio con los datos <provincia><municipio>
    Then valido la correcta respuesta del servicio

    Examples:
    |provincia|municipio|
    |"PONTEVEDRA"|"VIGO"|
