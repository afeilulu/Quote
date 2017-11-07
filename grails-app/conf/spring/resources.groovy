import com.afeilulu.swagger.WPSwaggyDataService
import com.afeilulu.swagger.filter.ApiKeyFilter
import com.github.rahulsom.swaggydoc.SwaggyDataService
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.core.Ordered

// Place your Spring DSL code here
beans = {
    /**
     * We wanto APIController to inject our extended SwaggyDataService, WPSwaggyDataService
     * So we overwrite the swaggyDataService bean with our own implementation
     */
    swaggyDataService(WPSwaggyDataService){
        pluginSwaggyDataService = ref('pluginSwaggyDataService')
        grailsApplication = ref('grailsApplication')
    }

    /**
     * Our WPSwaggyDataService uses the origintal SwaggyDataService. In order to do this we define a bean with the original SwaggyDataService and we name
     * it pluginSwaggyDataService
     */
    pluginSwaggyDataService(SwaggyDataService){
        grailsApplication = ref('grailsApplication')
        grailsLinkGenerator = ref('grailsLinkGenerator')
        grailsUrlMappingsHolder = ref('grailsUrlMappingsHolder')
        grailsMimeUtility = ref('grailsMimeUtility')
    }

    /**
     * Added a filter to retrieve the api_key token from swagger and inject it in the header so Spring Rest
     * security can get it.
     */
    apiKeyFilter(ApiKeyFilter)

    myFilter(FilterRegistrationBean) {
        filter = bean(ApiKeyFilter)
        urlPatterns = ['/rest/*']
        order = Ordered.HIGHEST_PRECEDENCE
    }
}
