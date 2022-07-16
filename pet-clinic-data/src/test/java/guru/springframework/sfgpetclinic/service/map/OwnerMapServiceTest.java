package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OwnerMapServiceTest {

    @InjectMocks
    private OwnerMapService ownerMapService;

    @Mock
    private PetMapService petService;

    @Mock
    private PetTypeMapService petTypeMapService;

    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "smith";

    @BeforeEach
    void setUp() {
        // given
        Owner owner1 = new Owner();
        owner1.setId(OWNER_ID);
        owner1.setLastName(LAST_NAME);

        // when
        ownerMapService.save(owner1);
    }

    @Test
    void findAll() {
        // when
        Set<Owner> owners = ownerMapService.findAll();

        // then
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        // when
        ownerMapService.deleteById(OWNER_ID);

        // then
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        // when
        ownerMapService.delete(ownerMapService.findById(OWNER_ID));

        // then
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        // given
        Long ownerNewId = 2L;
        Owner owner2 = new Owner();
        owner2.setId(ownerNewId);

        // when
        Owner owner = ownerMapService.save(owner2);

        // then
        assertEquals(ownerNewId, owner.getId());
    }

    @Test
    void findById() {
        // when
        Owner owner = ownerMapService.findById(OWNER_ID);

        // then
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName() {
        // when
        Owner owner = ownerMapService.findByLastName(LAST_NAME);

        // then
        assertNotNull(owner);
        assertEquals(LAST_NAME, owner.getLastName());
    }
}