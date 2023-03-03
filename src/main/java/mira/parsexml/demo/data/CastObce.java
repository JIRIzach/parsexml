package mira.parsexml.demo.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "castobce")
public class CastObce {
    @Id
    @Column(name = "kod", length = 50)
    private String kod;

    @Column(name = "nazev", length = 50)
    private String nazev;

    @Column(name = "kodobce", length = 50)
    private String kodObce;
}
