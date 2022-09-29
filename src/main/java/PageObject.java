import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

public class PageObject {

    private static final String URL_BASE = "http://ovc.catastro.meh.es";
    String response;

    public void consumoServicio(String provincia,String municipio){

        String path="/ovcservweb/ovcswlocalizacionrc/ovccallejero.asmx";

        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type","text/xml;charset=UTF-8");
        headers.put("SOAPAction","http://tempuri.org/OVCServWeb/OVCCallejero/ConsultaMunicipio");

        Actor Jhon = Actor.named("Jhon");
        Jhon.can(CallAnApi.at(URL_BASE));

        String bodyRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cat=\"http://www.catastro.meh.es/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <cat:Provincia>"+provincia+"</cat:Provincia>\n" +
                "      <cat:Municipio>"+municipio+"</cat:Municipio>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        Jhon.attemptsTo(
                Post.to(path)
                        .with(
                                req -> req.headers(headers)
                                        .body(bodyRequest)
                        )
        );

        response=LastResponse.received().answeredBy(Jhon)
                .asString();

    }

    public void validarRespuesta(){
        String[] split = response.split("<");
        String nm="";
        String cd="";
        String cmc="";
        String cp="";
        String cm="";

        for (int i=0; i<split.length; i++){
            if (split[i].contains("nm>VIGO")) {
                nm=split[i];
            }
        }
        for (int i=0; i<split.length; i++){
            if (split[i].contains("cd>54")) {
                cd=split[i];
            }
        }
        for (int i=0; i<split.length; i++){
            if (split[i].contains("cmc>57")) {
                cmc=split[i];
            }
        }
        for (int i=0; i<split.length; i++){
            if (split[i].contains("cp>36")) {
                cp=split[i];
            }
        }
        for (int i=0; i<split.length; i++){
            if (split[i].contains("cm>57")) {
                cm=split[i];
            }
        }

        if(!nm.equals("nm>VIGO")){
            Serenity.recordReportData().withTitle("Response Body").andContents(response);
            assertThat("El servicio no arrojo el valor esperado",false);
        }
        if(!cd.equals("cd>54")){
            Serenity.recordReportData().withTitle("Response Body").andContents(response);
            assertThat("El servicio no arrojo el valor esperado",false);
        }
        if(!cmc.equals("cmc>57")){
            Serenity.recordReportData().withTitle("Response Body").andContents(response);
            assertThat("El servicio no arrojo el valor esperado",false);
        }
        if(!cp.equals("cp>36")){
            Serenity.recordReportData().withTitle("Response Body").andContents(response);
            assertThat("El servicio no arrojo el valor esperado",false);
        }
        if(!cm.equals("cm>57")){
            Serenity.recordReportData().withTitle("Response Body").andContents(response);
            assertThat("El servicio no arrojo el valor esperado",false);
        }
    }
}
