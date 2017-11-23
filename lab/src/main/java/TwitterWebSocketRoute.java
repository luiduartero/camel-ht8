// Basado en el ejemplo de:   Apache Software Foundation (ASF) under one or more

import org.apache.camel.component.websocket.WebsocketComponent;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.search.TwitterSearchComponent;


public class TwitterWebSocketRoute extends RouteBuilder {

	//Setiamos puerto y tiempo 
    private int port = 9090;
    private String searchTerm;
    private int delay = 9000;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public void setAccessTokenSecret(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void configure() throws Exception {
    	
        // bind del websocket de Camel al componente que se creo
        WebsocketComponent wc = getContext().getComponent("websocket", WebsocketComponent.class);
        wc.setPort(port);
        wc.setStaticResources("classpath:.");

        // setup Twitter component
        TwitterSearchComponent tc = getContext().getComponent("twitter-search", TwitterSearchComponent.class);
        tc.setAccessToken(accessToken);
        tc.setAccessTokenSecret(accessTokenSecret);
        tc.setConsumerKey(consumerKey);
        tc.setConsumerSecret(consumerSecret);

        // poll de los nuevos tweets
        fromF("twitter-search://%s?delay=%s", searchTerm, delay)
            .to("log:tweet")
            //push de los los tweets en la cuenta de camel 
            .to("websocket:camel-tweet?sendToAll=true");
    }
}
