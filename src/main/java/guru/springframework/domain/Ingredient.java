package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String description;
  private BigDecimal amount;

  @OneToOne(fetch = FetchType.EAGER)  // this is the default anyway, but this explicitly shows intent
  private UnitOfMeasure unitOfMeasure;

  @ManyToOne
  private Recipe recipe;

  public Ingredient() {}
  
  public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
    this.description = description;
    this.amount = amount;
    this.unitOfMeasure = unitOfMeasure;
    this.recipe = recipe;
  }

  public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
    this.description = description;
    this.amount = amount;
    this.unitOfMeasure = unitOfMeasure;
  }

}
