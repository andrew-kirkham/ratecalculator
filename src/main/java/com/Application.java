package com;

import com.controllers.ApiController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Objects;

public class Application {
    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(final String... args) throws Exception {
        LOG.info("Starting up");
        setupSwagger();
        final HandlerList handlers = new HandlerList();

        // Handler for Swagger UI, static handler.
        handlers.addHandler(buildSwaggerUI());

        // Handler for Entity Browser and Swagger
        handlers.addHandler(buildContext());

        Server server = new Server(8080);
        server.setHandler(handlers);
        server.start();
        server.join();
    }

    private static ContextHandler buildContext() {
        final ResourceConfig resourceConfig = new ResourceConfig();
        // io.swagger.jaxrs.listing loads up Swagger resources
        resourceConfig.packages(ApiController.class.getPackage().getName(), ApiListingResource.class.getPackage().getName());
        final ServletContainer servletContainer = new ServletContainer(resourceConfig);
        final ServletHolder entityBrowser = new ServletHolder(servletContainer);
        final ServletContextHandler entityBrowserContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        entityBrowserContext.setContextPath("/");
        entityBrowserContext.addServlet(entityBrowser, "/*");

        return entityBrowserContext;
    }

    // This starts the Swagger UI at http://localhost:9999/docs
    private static ContextHandler buildSwaggerUI() throws URISyntaxException {
        final ResourceHandler swaggerUIResourceHandler = new ResourceHandler();
        swaggerUIResourceHandler.setResourceBase(Objects.requireNonNull(Application.class.getClassLoader().getResource("swagger-ui")).toURI().toString());
        final ContextHandler swaggerUIContext = new ContextHandler();
        swaggerUIContext.setContextPath("/docs");
        swaggerUIContext.setHandler(swaggerUIResourceHandler);
        return swaggerUIContext;
    }

    private static void setupSwagger() {
        final BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setResourcePackage(Application.class.getPackage().getName());
        beanConfig.setScan(true);
        beanConfig.setBasePath("/");
        beanConfig.setDescription("Description");
        beanConfig.setTitle("TOPIC");
    }
}
