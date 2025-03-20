import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()

        val config = CorsConfiguration()
        config.allowedOrigins = listOf("https://app.vaffel.org")  // Frontend domain
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
        config.allowedHeaders = listOf("*") // Allow all headers
        config.exposedHeaders = listOf("Authorization", "Content-Type") // Expose headers if needed
        config.allowCredentials = true // Allow cookies/credentials
        config.maxAge =3600L // Cache preflight response for1 hour

        // Apply configuration to all endpoints
        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }
}