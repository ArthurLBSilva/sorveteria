package sorveteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@SQLDelete(sql = "UPDATE peso SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Peso extends AbstractEntity {

    @NotBlank(message = "O nome do peso não pode estar em branco.")
    String peso;

    @Min(value = 0, message = "O valor do peso deve ser maior ou igual a zero.")
    Double valor;

    @OneToMany(mappedBy = "peso")
    List<Sorvete> sorvetes = new ArrayList<>();
}
