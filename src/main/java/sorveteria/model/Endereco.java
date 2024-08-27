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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE endereco SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Endereco extends AbstractEntity {

    @NotBlank(message = "A rua não pode estar em branco.")
    String rua;

    @NotBlank(message = "O número não pode estar em branco.")
    String numero;

    @OneToOne(mappedBy = "endereco")
    Usuario usuario;
}
