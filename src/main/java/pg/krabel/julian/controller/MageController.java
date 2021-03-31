package pg.krabel.julian.controller;

import lombok.AllArgsConstructor;
import pg.krabel.julian.characters.Mage;
import pg.krabel.julian.repository.MageRepository;

import java.util.Optional;

import static java.lang.Integer.parseInt;


@AllArgsConstructor
public class MageController {

    private final MageRepository repository;

    public String find(String name) {
        Optional<Mage> m=repository.find(name);
        return m.isPresent() ? m.get().toString(): "not found";
    }

    public String delete(String name) {
        try {
            repository.delete(name);
        } catch (IllegalArgumentException e) {
            return "not found";
        }
        return "done";
    }

    public String save(String name, String level) {
        try {
            repository.save(Mage.builder()
                    .name(name)
                    .level(parseInt(level))
                    .build());
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
        return "done";
    }
}