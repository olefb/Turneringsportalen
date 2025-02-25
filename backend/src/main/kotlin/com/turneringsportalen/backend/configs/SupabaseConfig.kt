package com.turneringsportalen.backend.configs

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SupabaseConfig {

    @Bean
    fun client(): SupabaseClient { // Should probably be moved to environment variables or something
        return createSupabaseClient(
            supabaseUrl = "https://alkuvpbupekuvdgfvsnd.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFsa3V2cGJ1cGVrdXZkZ2Z2c25kIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDAxMzg1MTQsImV4cCI6MjA1NTcxNDUxNH0.og6JYAkL2uoQ0OwSBXQmbVmu4aQUVxFexkl_kBR5RbY"
        ) {
            install(Postgrest)
        }
    }
}