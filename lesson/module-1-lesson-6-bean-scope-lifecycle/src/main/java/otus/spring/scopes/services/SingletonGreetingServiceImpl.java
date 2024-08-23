package otus.spring.scopes.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("singletonGreetingService")
@Scope("singleton")
public class SingletonGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
