
import jakarta.persistence.*


@Entity
data class Participant(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val participant_id: Int = 0, 
    val name: String  
)

