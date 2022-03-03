package io.hanbings.carbon.router;

import io.hanbings.carbon.common.content.OAuth2PlatformType;
import io.hanbings.carbon.data.OAuth2Platform;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public record OAuth2Handler(Map<OAuth2PlatformType, OAuth2Platform> auths) implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext event) {
        if (event.request().getParam("platform") != null) {
            OAuth2PlatformType type = OAuth2PlatformType.lookup(event.request().getParam("platform"));
            if (type != null && auths.containsKey(type)) {
                OAuth2Platform auth = auths.get(type);
                String url = auth.getAuthServer()
                        + "?client_id=" + auth.getClientId()
                        + "&scope=" + auth.getPermission()
                        + "&redirect_uri=" + auth.getRedirectUri();
                event.response().putHeader("location", url).setStatusCode(302).end();
                return;
            }
        }
        event.response().setStatusCode(404).end();
    }
}
