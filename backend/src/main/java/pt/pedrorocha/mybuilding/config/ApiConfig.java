package pt.pedrorocha.mybuilding.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Value("${api.base-building-path}")
    private String baseBuildingPath;

    @Value("${api.base-clients-path}")
    private String baseClientsPath;

    @Value("${api.base-clients-group-path}")
    private String baseGroupPath;

    public String getBaseBuildingPath() {
        return baseBuildingPath;
    }

    public String getBaseClientsPath() {
        return baseClientsPath;
    }

    public String getBaseGroupPath() {return baseGroupPath;}
}