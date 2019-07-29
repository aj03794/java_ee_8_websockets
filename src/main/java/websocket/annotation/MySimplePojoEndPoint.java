package websocket.annotation;

import model.MySimplePojo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint(value="/pojo", encoders = MySimplePojoEncoder.class, decoders = MySimplePojoDecoder.class)
public class MySimplePojoEndPoint {

    @Inject
    private Logger logger;

    @OnOpen
    public void onOpen(final Session session) throws IOException, EncodeException {
        logger.info("Connected to /pojo");
        var mySimplePojo = new MySimplePojo("Java EE", "____@____.com", "Great day!");
        session.getBasicRemote().sendObject(mySimplePojo);
    }

    @OnMessage
    // Decoder used for mySimplePojo
    public void onMessage(final Session session, MySimplePojo mySimplePojo) throws IOException, EncodeException {
        logger.log(Level.INFO, "My simple pojo received from the server");
        logger.log(Level.INFO, mySimplePojo.toString());
        // Encoder gets called here to properly serialize mySimplePojo
        session.getBasicRemote().sendObject(mySimplePojo);
    }
}
