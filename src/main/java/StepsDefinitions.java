import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class StepsDefinitions {

    @Steps
    PageObject ob;

    @Given("^realizo el consumo del servicio con los datos \"([^\"]*)\"\"([^\"]*)\"$")
    public void ConsumirServicio(String provincia,String municipio) throws Exception {
        ob.consumoServicio(provincia,municipio);
    }

    @Then("^valido la correcta respuesta del servicio$")
    public void validarRespuesta(){
        ob.validarRespuesta();
    }



}
