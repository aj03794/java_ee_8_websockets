package websocket.annotation;

import model.MySimplePojo;

import javax.json.bind.JsonbBuilder;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MySimplePojoEncoder implements Encoder.Text<MySimplePojo> {

    @Override
    public String encode(MySimplePojo mySimplePojo) {
        return JsonbBuilder.create().toJson(mySimplePojo);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
