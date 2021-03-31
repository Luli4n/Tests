package controller;

import org.junit.jupiter.api.Test;
import pg.krabel.julian.characters.Mage;
import pg.krabel.julian.controller.MageController;
import pg.krabel.julian.repository.MageRepository;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class MageControllerTest {

    @Test
    public void delete_ExistingObject_ReturnDone(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        doNothing().when(mr).delete(anyString());

        assertThat(mc.delete(anyString())).isEqualTo("done");
        verify(mr,times(1)).delete(anyString());
    }

    @Test
    public void delete_NonExistingObject_ReturnNotFound(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        doThrow(IllegalArgumentException.class).when(mr).delete(anyString());

        assertThat(mc.delete(anyString())).isEqualTo("not found");
        verify(mr,times(1)).delete(anyString());
    }

    @Test
    public void find_NonExistingObject_ReturnNotFound(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        when(mr.find(anyString())).thenReturn(Optional.empty());

        assertThat(mc.find(anyString())).isEqualTo("not found");
        verify(mr,times(1)).find(anyString());
    }

    @Test
    public void find_ExistingObject_ReturnStringObject(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        Optional<Mage> m = Optional.of(Mage.builder()
                .name("test")
                .level(12)
                .build());

        when(mr.find(anyString())).thenReturn(m);

        assertThat(mc.find(anyString())).isEqualTo(m.get().toString());
        verify(mr,times(1)).find(anyString());
    }

    @Test
    public void save_NonExistingProperObject_ReturnDone(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        doNothing().when(mr).save(any());

        assertThat(mc.save("Test","2")).isEqualTo("done");
        verify(mr,times(1)).save(any());
    }

    @Test
    public void save_ExistingProperObject_ReturnBadRequest(){

        MageRepository mr=mock(MageRepository.class);
        MageController mc=new MageController(mr);

        doThrow(IllegalArgumentException.class).when(mr).save(any());

        assertThat(mc.save("Test","2")).isEqualTo("bad request");
        verify(mr,times(1)).save(any());
    }
}
