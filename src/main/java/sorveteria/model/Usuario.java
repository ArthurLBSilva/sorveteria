package sorveteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@SQLDelete(sql = "UPDATE usuario SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Usuario extends AbstractEntity {

    @NotBlank(message = "O nome não pode estar em branco.")
    String nome;

    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Email(message = "O e-mail deve ser válido.")
    String email;

    @NotBlank(message = "A senha não pode estar em branco.")
    String senha;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrinho_id", referencedColumnName = "id")
    Carrinho carrinho;

    boolean isAdmin;
}
