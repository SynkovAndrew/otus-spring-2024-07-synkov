package otus.spring.scopes.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("requestGreetingService")
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class RequestGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
