package websocket.annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/chat")
@ApplicationScoped
public class ChatEndPoint {

    private static final ConcurrentLinkedQueue<Session> peers = new ConcurrentLinkedQueue<>();

    @Inject
    private Logger logger;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("New client has connected to websocket server");
        peers.add(session);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.log(Level.INFO,"Websocket session closed because of {0}", closeReason.getReasonPhrase());
        peers.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.log(Level.INFO, "Message received from client {0}", message);
        // send message to all clients except the client that initiated the message
        for (var peer: peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendText(message);
            }
        }
    }

}
