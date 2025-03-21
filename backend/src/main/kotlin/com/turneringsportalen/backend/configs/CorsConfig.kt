import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class SecurityConfig {

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().applyPermitDefaultValues() // Use default values as a starting point
        config.allowedOrigins = listOf("http://localhost:3000", "https://app.vaffel.org") // Be SPECIFIC!
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowedHeaders = listOf("*")
        config.exposedHeaders = listOf("Authorization", "Content-Type", "Access-Control-Allow-Origin") // Keep these.
        config.allowCredentials = true //Important for cookies, authorization headers, etc.
        config.maxAge = 3600L

        source.registerCorsConfiguration("/**", config) // Apply to all paths
        return CorsFilter(source)
    }
}