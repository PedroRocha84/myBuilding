package pt.pedrorocha.mybuilding.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Value("${api.base-building-path}")
    private String baseBuildingPath;

    @Value("${api.base-company-path}")
    private String baseCompanyPath;

    @Value("${api.base-group-path}")
    private String baseGroupPath;

    public String getBaseBuildingPath() {
        return baseBuildingPath;
    }

    public String getBaseClientsPath() {
        return baseCompanyPath;
    }

    public String getBaseGroupPath() {return baseGroupPath;}
}