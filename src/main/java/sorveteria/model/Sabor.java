package sorveteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import sorveteria.model.generic.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE sabor SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Sabor extends AbstractEntity {

    @NotBlank(message = "O nome não pode estar em branco.")
    String nome;

    @Column(nullable = false)
    String estoque = "Disponível";

    @ManyToMany(mappedBy = "sabores")
    List<Sorvete> sorvetes = new ArrayList<>();
}