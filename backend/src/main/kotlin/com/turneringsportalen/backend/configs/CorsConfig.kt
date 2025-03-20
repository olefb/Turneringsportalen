import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Value("\${allowed.origins:}")
    private lateinit var allowedOrigins: String

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        val origins = allowedOrigins.split(",").filter { it.isNotEmpty() }
        config.allowedOrigins = origins
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowedHeaders = listOf("*")
        config.allowCredentials = true
        config.maxAge = 3600L
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}