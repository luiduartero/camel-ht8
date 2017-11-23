
// Basado en el ejemplo de:   Apache Software Foundation (ASF) under one or more
 
import org.apache.camel.main.Main;


public final class CamelTwitterWebSocketMain {

	
    //credenciales para el twetter de camel (es publico)
    private static String consumerKey = "NMqaca1bzXsOcZhP2XlwA";
    private static String consumerSecret = "VxNQiRLwwKVD0K9mmfxlTTbVdgRpriORypnUbHhxeQw";
    private static String accessToken = "26693234-W0YjxL9cMJrC0VZZ4xdgFMymxIQ10LeL1K8YlbBY";
    private static String accessTokenSecret = "BZD51BgzbOdFstWZYsqB5p5dbuuDV12vrOdatzhY4E";

    private CamelTwitterWebSocketMain() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\n\n");
        System..println("Data pull en http://localhost:9090/index.html");
        System.out.println("===============================================");
        System.out.println("\n\n");

        //creamos un main para Camel 
        Main main = new Main();

        TwitterWebSocketRoute route = new TwitterWebSocketRoute();

        //configuracion de la autenticcion de la aplicacion 
        // setup twitter application authentication
        route.setAccessToken(accessToken);
        route.setAccessTokenSecret(accessTokenSecret);
        route.setConsumerKey(consumerKey);
        route.setConsumerSecret(consumerSecret);

        // buscamos la convinacion de caracters "hi" cada 10 segundos
        route.setSearchTerm("hi");
        route.setDelay(10000);
        
   
        // setiamos el socket en el puerto 9090
        route.setPort(9090);
        main.addRouteBuilder(route);

        main.run();
    }

}
