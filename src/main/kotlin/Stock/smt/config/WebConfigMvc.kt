package Stock.smt.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*

@Configuration
@EnableWebMvc
class WebConfigMvc : WebMvcConfigurer{
    val resourcesLocation = arrayOf("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/public/")

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
            .addResourceLocations(*resourcesLocation)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

//    override fun addViewControllers(registry: ViewControllerRegistry) {
//        registry.addRedirectViewController("swagger", "/swagger")
//        registry.addRedirectViewController("/", "index.html")
//    }

}