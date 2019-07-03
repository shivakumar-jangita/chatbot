
package  com.sap.itservices.copilot.smalltalk.config;

        import com.github.benmanes.caffeine.cache.Caffeine;
        import com.github.benmanes.caffeine.cache.LoadingCache;
        import com.github.benmanes.caffeine.cache.RemovalListener;
        import com.sap.ea.eacp.okhttp.destinationclient.OkHttpDestination;
        import com.sap.ea.eacp.okhttp.destinationclient.configuration.properties.Cookies;
        import com.sap.ea.eacp.okhttp.destinationclient.provider.DestinationConfigurationProvider;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;

        import javax.servlet.http.HttpServletRequest;
        import java.util.concurrent.TimeUnit;

public class OkHttpDestinationFactorySpring {

    private static final Logger LOG = LoggerFactory.getLogger(OkHttpDestinationFactorySpring.class);



    @Autowired
    private HttpServletRequest request;

    private final LoadingCache<String, DestinationConfigurationProvider> cache = Caffeine.newBuilder()
            .expireAfterAccess(1, TimeUnit.DAYS)
            .removalListener((RemovalListener<String, DestinationConfigurationProvider>) (destinationName, configurationProvider, removalCause) -> {
                LOG.info("Removing destination '{}' from cache");
            })
            .build(destinationName -> {
                LOG.info("Creating DestinationConfigurationProvider for '{}'", destinationName);
                return getDestinationConfiguration(destinationName);
            });

    public OkHttpDestination create(String destinationName) {
        return OkHttpDestination.create(cache.get(destinationName).provide());
    }

    protected DestinationConfigurationProvider getDestinationConfiguration(String destinationName) {
        return DestinationConfigurationProvider.newBuilder(destinationName)
                .cookies(Cookies.ENABLE)
                .withSessionScope(request)
                .build();

    }
}