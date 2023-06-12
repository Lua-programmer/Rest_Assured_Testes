package io.github.luaprogrammer.teste_rest_assured.utils;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class BaseApi {

    @BeforeAll
    public static void setup() {
        baseURI = "http://localhost";
        basePath = "/usuarios";
        port = 8080;
    }
}
