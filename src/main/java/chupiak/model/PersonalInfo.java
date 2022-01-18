package chupiak.model;

import com.sun.istack.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "personal_info")
public class PersonalInfo {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @OneToOne
    private Client clientId;

    @Getter
    @Setter
    private String fio;

    @Getter
    @Setter
    @Column(unique = true)
    private String passportData;

    @Getter
    @Setter
    private LocalDate birthday;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime deleted;

    @Override
    public String toString() {
        return "PersonalInfo{"
                + "id=" + id
                + ", clientId=" + clientId
                + ", fio='" + fio + '\''
                + ", passportData='" + passportData + '\''
                + ", birthday=" + birthday
                + ", created=" + created
                + ", deleted=" + deleted
                + '}';
    }
}
