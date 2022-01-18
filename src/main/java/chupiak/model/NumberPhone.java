package chupiak.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phone_number")
public class NumberPhone {
    private static final int MAIN_PHONE = 1;
    private static final int ADDITIONAL_PHONE = 2;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private Client clientId;

    @Getter
    @Setter
    @Column(unique = true)
    private String phoneNumber;

    @Getter
    @Setter
    @Min(1)
    @Max(2)
    private int phoneType;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime deleted;

    @Override
    public String toString() {
        return "NumberPhone{"
                + "id=" + id
                + ", clientId=" + clientId
                + ", phoneNumber='" + phoneNumber + '\''
                + ", phoneType=" + phoneType
                + ", created=" + created
                + ", deleted=" + deleted
                + '}';
    }
}
