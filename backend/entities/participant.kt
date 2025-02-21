package entities
import jakarta.persistence.*

@Entity
@Table(name = "participants")
data class Participant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val participant_id: Int = 0,
        val name: String = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tournament_id", nullable = false)
        val participates_in: tournament

)