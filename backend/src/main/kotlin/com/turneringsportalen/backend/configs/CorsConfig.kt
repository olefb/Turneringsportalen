import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke // Use invoke for cleaner DSL
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity // Enable Spring Security
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            cors { } //  <-- VERY IMPORTANT: Enable CORS in Spring Security
            csrf { disable() } // Usually disabled for APIs, but consider CSRF protection for non-API endpoints.
            authorizeHttpRequests {
                authorize(anyRequest, permitAll) // Simplest for now, but configure properly!
                //  Example of a more secure configuration (requires authentication)
                // authorize("/api/public/**", permitAll)
                // authorize("/api/private/**", authenticated)
                // authorize(anyRequest, authenticated)
            }
            // ... other security configurations (authentication, etc.)
        }
        return http.build()
    }

    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().applyPermitDefaultValues() // Use default values as a starting point
        config.allowedOrigins = listOf("http://localhost:3000", "https://api.vaffel.org") // Be SPECIFIC!
        config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        config.allowedHeaders = listOf("*")
        config.exposedHeaders = listOf("Authorization", "Content-Type", "Access-Control-Allow-Origin") // Keep these.
        config.allowCredentials = true //Important for cookies, authorization headers, etc.
        config.maxAge = 3600L

        source.registerCorsConfiguration("/**", config) // Apply to all paths
        return CorsFilter(source)
    }
}