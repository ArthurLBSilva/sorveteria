package sorveteria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sorveteria.model.generic.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE sorvete SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Sorvete extends AbstractEntity {

    @Min(value = 0, message = "O preço do sorvete deve ser maior ou igual a zero.")
    Double preco;

    @ManyToOne
    @JoinColumn(name = "peso_id", nullable = false)
    Peso peso;

    @ManyToMany
    @JoinTable(
        name = "sorvete_sabor",
        joinColumns = @JoinColumn(name = "sorvete_id"),
        inverseJoinColumns = @JoinColumn(name = "sabor_id")
    )
    List<Sabor> sabores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "calda_id", nullable = false)
    
    Calda calda;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    Carrinho carrinho;
}
