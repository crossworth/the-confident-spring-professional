package br.dev.pedro.pdfinvoices.context;

import br.dev.pedro.pdfinvoices.ApplicationLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ComponentScan scans only the package and subpackages
 * so we need to change the basePackage
 *
 * SpringBootApplication is also a ComponentScan
 */

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class PDFInvoiceApplicationCofiguration {

    /**
     * Bean
     * In Spring, the objects that form the backbone of your
     * application and that are managed by the Spring IoC container
     * are called beans. A bean is an object that is instantiated,
     * assembled, and otherwise managed by a Spring IoC container.
     * <p>
     * By default Beans are singletons, to change to a prototype model
     * Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
     */

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
