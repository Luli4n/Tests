package pg.krabel.julian.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pg.krabel.julian.characters.Mage;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class MageRepository {
    
    private Collection<Mage> collection;
    
    public Optional<Mage> find(String name) {
        
        Optional<Mage> toFind = Optional.empty();
        
        for(Mage m:collection)
        {
            if(m.getName().equals(name))
            {
                toFind=Optional.of(m);
                break;
            }
        }
        return toFind;
    }

    public void delete(String name) {
        Mage toDelete = null;

        for(Mage m:collection)
        {
            if(m.getName().equals(name))
            {
                toDelete= m;
                break;
            }
        }

        if(toDelete==null)
        {
            throw new IllegalArgumentException("Bad name request");
        }
        else
        {
            collection.remove(toDelete);
        }
    }
    public void save(Mage mage) {

        if(collection.stream().anyMatch(mage1 -> mage1.getName().equals(mage.getName())))
        {
            throw new IllegalArgumentException("Mage already exists");
        }
        else
        {
            collection.add(mage);
        }
    }
}