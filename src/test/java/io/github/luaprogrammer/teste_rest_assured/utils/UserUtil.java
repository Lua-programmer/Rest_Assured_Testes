package io.github.luaprogrammer.teste_rest_assured.utils;

import com.jayway.jsonpath.Criteria;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class UserUtil {

    private final Random random = new Random();

    public static JSONObject geraUsuario() throws JSONException {
        return new JSONObject()
                .put( "nome", "Usuario " + new Random().nextInt( 1000 ) )
                .put( "dataNascimento", "10/08/1978" )
                .put( "email", "usuario" + new Random().nextInt( 1000 ) + "@gmail.com" );
    }

    public static JSONObject geraUsuarioComEmailInvalido() throws JSONException {
        return new JSONObject()
                .put( "nome", "Usuario " + new Random().nextInt( 1000 ) )
                .put( "dataNascimento", "10/08/1978" )
                .put( "email", "usuario" + new Random().nextInt( 1000 ) + "*gmail.com" );
    }

    public static JSONObject geraUsuarioSemEmail() throws JSONException {
        return new JSONObject()
                .put( "nome", "Usuario " + new Random().nextInt( 1000 ) )
                .put( "dataNascimento", "10/08/1978" )
                .put( "email", "" );
    }

    public static JSONObject geraUsuarioSemNome() throws JSONException {
        return new JSONObject()
                .put( "nome", " " )
                .put( "dataNascimento", "10/08/1978" )
                .put( "email", "usuario" + new Random().nextInt( 1000 ) + "@gmail.com" );
    }

    public static JSONObject geraUsuarioComEmailExistente() throws JSONException {
        return new JSONObject()
                .put( "nome", "Usuario " + new Random().nextInt( 1000 ) )
                .put( "dataNascimento", "10/08/1978" )
                .put( "email", "stepaz@gmail.com" );
    }
}
