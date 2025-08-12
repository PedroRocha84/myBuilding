package pt.pedrorocha.mybuilding.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Value("${api.base-building-path}")
    private String baseBuildingPath;

    public String getBaseBuildingPath() {
        return baseBuildingPath;
    }

    @Value("${api.base-clients-path}")
    private String baseClientsPath;

    public String getBaseClientsPath() {
        return baseClientsPath;
    }
}