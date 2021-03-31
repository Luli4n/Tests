package pg.krabel.julian.characters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Mage {
    private String name;
    private int level;
}