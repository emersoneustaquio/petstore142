
// 0 -Nome do pacote

// 1- Biblioteca

//2- Classe
import io.restassured.response.Response;  //Classe Resposta do Rest-assured
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
  

import static io.restassured.RestAssured.given; // função given da classe Rest Assured
import static org.hamcrest.Matchers.is;

public class TestePet {
    //2.1 atributos
    static String ct = "application/json"; // content-type
    static String uriPet = "https://petstore.swagger.io/v2/pet"; //URL base

    //2.2 funções e metodos
    //2.2.1 funcções e metrodos comuns / uteis
    //2.2.2 métodos de teste

    //Função de leitura de Json
    public static String lerArquivoJson (String arquivoJson) throws IOException{
        //To Do: Completar a leitura do arquivo

        //return new String(Files)
        return new String(Files.readAllBytes(Paths.get(arquivoJson))) ;
    }

    @Test
    public void testPostPet () throws IOException{
        // carregar os dados do arquivo json do usuario pet
       String jsonBory = lerArquivoJson("src/test/resources/json/pet1.json"); // carregar arquivo json

       long petId = 170621001;  // codigo esperado do pet
        
      // cl eça o teste via REST - ASSURED

    given()                     //Dado que
      .contentType(ct)           // Tipo do conteúdo é ct (aplication json)
      .log().all()               // mostre tudo na ida      
      .body(jsonBory)            // envie o corpo da requisição

    .when()
      .post(uriPet)           // chamando o endpoint fazendo post
      
    .then()                      //Então
      .log().all()              //mostre tudo na volta
      .statusCode(200)  // verifica se status code é 200
      .body ("name", is("doggie"))  //verifica variavel name
      .body("id",is(petId) )
      .body("category.name",is("cachorro"))
      .body("Tags[0].name",is("vacinado"))
      ;


    }

    
}
