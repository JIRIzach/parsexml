package mira.parsexml.demo.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Obec {
    @Id
    @Column(name = "kod", length = 50)
    private String kod;

    @Column(name = "nazev", length = 50)
    private String nazev;
}
