package entitites;
import jakarta.persistence*;

@Entity
data class Tournament(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val tournament_id: Int,
        val name : String,
        val start_date : Instant,
        val start_time : Instant,
        val location : String,
        @ElementCollection
        @CollectionTable(name = "available_fields", joinColumns = [JoinColumn(name = "tournament_id")])
        // @Column(name = "") - kanskje vi må definere dette
        val fields : List<String>,
        val match_interval : String
)