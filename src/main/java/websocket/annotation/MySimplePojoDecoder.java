package websocket.annotation;

import model.MySimplePojo;

import javax.json.bind.JsonbBuilder;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MySimplePojoDecoder implements Decoder.Text<MySimplePojo> {

    @Override
    public MySimplePojo decode(String s) throws DecodeException {
        return JsonbBuilder.create().fromJson(s, MySimplePojo.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
