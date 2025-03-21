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

        // Allow all origins in development, restrict to your domain in production
        config.allowedOrigins = listOf(
            "http://localhost:3000",           // NextJS dev server
            "https://app.vaffel.org" // Your production NextJS domain
        )

        // Allow common HTTP methods
        config.allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")

        // Allow all headers the client might send
        config.allowedHeaders = listOf(
            "Origin",
            "Content-Type",
            "Accept",
            "Authorization",
            "X-Requested-With",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        )

        // Allow cookies if your app uses authentication
        config.allowCredentials = true

        // Expose these headers to the frontend
        config.exposedHeaders = listOf(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials"
        )

        // How long the browser should cache the CORS response (in seconds)
        config.maxAge = 3600L

        // Apply CORS configuration to all paths
        source.registerCorsConfiguration("/**", config)

        return CorsFilter(source)
    }
}