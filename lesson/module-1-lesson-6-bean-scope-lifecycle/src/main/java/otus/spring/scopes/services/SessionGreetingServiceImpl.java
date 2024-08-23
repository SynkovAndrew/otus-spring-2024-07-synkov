package otus.spring.scopes.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("sessionGreetingService")
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
