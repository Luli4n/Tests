package repository;

import org.junit.jupiter.api.Test;
import pg.krabel.julian.characters.Mage;
import pg.krabel.julian.repository.MageRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTest {

    @Test
    public void delete_NullObject_ShouldThrowIllegalArgumentException()
    {
        MageRepository mc=new MageRepository(new ArrayList<Mage>());

        assertThatThrownBy(() -> {
            mc.delete("Jan");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bad name request");
    }

    @Test
    public void find_NullObject_ShouldReturnEmptyOptionalObject()
    {
        MageRepository mc=new MageRepository(new ArrayList<Mage>());

        Optional<Mage> m=mc.find("");

        assertThat(m).isEmpty();
    }

    @Test
    public void find_NotNullObject_ShouldReturnOptionalObjectWithContent()
    {
        MageRepository mc=new MageRepository(new ArrayList<Mage>());
        Mage m =Mage.builder()
                .name("Jan")
                .level(12)
                .build();

        mc.save(m);

        Optional<Mage> mFound=mc.find("Jan");

        assertThat(mFound.get()).isEqualTo(m);
    }
}
