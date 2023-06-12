package io.github.luaprogrammer.teste_rest_assured.controller;

import io.github.luaprogrammer.teste_rest_assured.utils.BaseApi;
import io.github.luaprogrammer.teste_rest_assured.utils.UserUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTestIntegration extends BaseApi {

    @Test
    @DisplayName( "Dado que eu solicite uma lista de usuários" +
            "deve retornar a lista e statusCode 200")
    public void test1() {
        Response response = given().log().all().
                contentType( "application/json" ).
                when().
                get().
                then().log().all().
                extract().response();

        assertEquals( 200, response.statusCode() );
        assertEquals( 1234, response.jsonPath().getLong( "id[0]") );
    }

    @Test
    @DisplayName( "Dado que eu solicite um usuário específico" +
            " deve retornar o usuário e statusCode 200")
    public void test2() {
        Response response = given().log().all().
                contentType( "application/json" ).
                when().
                get( "/1234" ).
                then().log().all().
                extract().response();

        assertAll(
            () -> assertEquals( 200, response.statusCode() ),
            () -> assertEquals( 1234, response.jsonPath().getLong( "id" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu solicite um usuário específico e ele não exista" +
            "deve retornar 'Usuário não encontrado' o  e statusCode 404")
    public void test3() {
        Response response = given().log().all().
                contentType( "application/json" ).
                when().
                get( "/5896" ).
                then().log().all().
                extract().response();

        assertAll(
            () -> assertEquals( 404, response.statusCode() ),
            () -> assertEquals( "[Usuário não encontrado]", response.jsonPath().getString( "errors" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu crie um usuario com sucesso, deve retornar" +
            "statusCode 201" )
    public void test4() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuario().toString() ).
                post().
                then().log().all().
                extract().response();

        assertEquals( 201, response.statusCode() );
    }

    @Test
    @DisplayName( "Dado que eu crie um usuario e o e-mail á exista, deve retornar" +
            "statusCode 400 e mensagem de erro" )
    public void test5() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuarioComEmailExistente().toString() ).
                post().
                then().log().all().
                extract().response();

        assertAll(
            () -> assertEquals( 400, response.statusCode() ),
            () -> assertEquals( "[E-mail já está sendo utilizado]", response.jsonPath().getString( "errors" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu crie um usuário e passe um e-mail inválido " +
            "deve retornar statusCode 400 e mensagem de erro" )
    public void test6() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuarioComEmailInvalido().toString() ).
                post().
                then().log().all().
                extract().response();

        assertAll(
            () -> assertEquals( 400, response.statusCode() ),
            () -> assertEquals( "[email: O campo email deve ser um email válido]", response.jsonPath().getString( "errors" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu crie um usuário e passe um e-mail em branco " +
            "deve retornar statusCode 400 e mensagem de erro" )
    public void test7() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuarioSemEmail().toString() ).
                post().
                then().log().all().
                extract().response();

        assertAll(
                () -> assertEquals( 400, response.statusCode() ),
                () -> assertEquals( "[email: O campo email é obrigatório]", response.jsonPath().getString( "errors" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu crie um usuário e passe um nome em branco " +
            "deve retornar statusCode 400 e mensagem de erro" )
    public void test8() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuarioSemNome().toString() ).
                post().
                then().log().all().
                extract().response();

        assertAll(
                () -> assertEquals( 400, response.statusCode() ),
                () -> assertEquals( "[nome: O campo nome é obrigatório]", response.jsonPath().getString( "errors" ) )
        );
    }

    @Test
    @DisplayName( "Dado que eu passe um id de usuário e altere os dados dele, deve retornar o " +
            "statusCode 204" )
    public void test9() throws JSONException {
        Response response = given().log().all().
                contentType( "application/json" ).
                body( UserUtil.geraUsuario().toString()).
                put( "/1234" ).
                then().log().all().
                extract().response();

        assertEquals( 204, response.statusCode() );
    }

    @Test
    @DisplayName( "Dado que eu passe um id de usuário para excluí-lo " +
            "deve retornar statusCode 204" )
    public void test10() {
        Response response = given().log().all().
                contentType( "application/json" ).
                when().
                delete( "/3265" ).
                then().log().all().
                extract().response();

        assertEquals( 204, response.statusCode() );
    }
}
