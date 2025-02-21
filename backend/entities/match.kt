package entities;
import jakarta.persistence.*

@Entity
@Table(name = "matches")
data class Match(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val match_id : Int,
        @OneToMany(fetch = FetchType.LAZY)
        @JoinColumn(name = "participant_id", nullable = false)
        val participants : MutableSet<participant> = mutableSetOf(),
        val time : Instant,
        val game_location : String
)