/**
 * Created by Rian on 06/12/2017.
 */

import DAO.CartDao;
import resources.CartResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import resources.LoginService;
import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import services.CartService;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.util.EnumSet;

//java -jar target/webshop_backend01-1.0-SNAPSHOT.jar server example.yml


public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
  public static void main(String[] args) throws Exception {
    new HelloWorldApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    bootstrap.addBundle(new ConfiguredAssetsBundle("/assets", "/", "index.html"));
  }

  @Override
  public void run(HelloWorldConfiguration configuration,
          Environment environment) throws IOException {


    final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());

    ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
    errorHandler.addErrorPage(404, "/");
    environment.getApplicationContext().setErrorHandler(errorHandler);
    final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    environment.healthChecks().register("template", healthCheck);
    final LoginService loginService = new LoginService();

    environment.jersey().setUrlPattern("/api");
    environment.jersey().register(loginService);
    environment.jersey().register(new CartResource(new CartService(new CartDao())));

//    environment.jersey().register(resource);



  }

}