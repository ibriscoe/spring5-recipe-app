package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

  // a command object is a useful intermediary for a domain object
  // using the domain object itself for e.g. form handling, is not best practice
  // as you may want to expose a much smaller subset of properties than exist in a domain object.

  private Long id;
  private String description;
}
