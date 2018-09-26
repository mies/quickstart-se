package io.helidon.examples.quickstart.se;

import javax.json.Json;
import javax.json.JsonObject;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import io.prometheus.client.Counter;

public class MyService implements Service {
    static final Counter accessCtr = Counter.build()
        .name("requests_total").help("Total requests.").register();  

    @Override
    public final void update(final Routing.Rules rules) {
            rules
                 .any(this::countAccess);
                 //.get("/", this::myGet);
        }

    private void countAccess(ServerRequest request, ServerResponse response) {
            accessCtr.inc(); 
            request.next();
    }
}