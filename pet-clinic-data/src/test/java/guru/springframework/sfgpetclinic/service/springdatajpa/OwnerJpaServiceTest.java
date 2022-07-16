package guru.springframework.sfgpetclinic.service.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @InjectMocks
    private OwnerJpaService ownerJpaService;

    @Mock
    private OwnerRepository ownerRepository;

    private final String LAST_NAME = "Smith";
    private final Long OWNER_ID = 1L;

    @Test
    void findAll() {
        // given
        Set<Owner> returnOwner = new HashSet<>();
        returnOwner.add(new Owner());
        returnOwner.add(new Owner());
        given(ownerRepository.findAll()).willReturn(returnOwner);

        // when
        Set<Owner> owners = ownerJpaService.findAll();

        // then
        assertNotNull(owners);
        assertEquals(2, owners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given
        Owner returnOwner = new Owner();
        returnOwner.setId(OWNER_ID);
        given(ownerRepository.findById(anyLong())).willReturn(Optional.of(returnOwner));

        // when
        Owner owner = ownerJpaService.findById(anyLong());

        // then
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        verify(ownerRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        // given
        Owner returnOwner = new Owner();
        given(ownerRepository.save(any())).willReturn(returnOwner);

        // when
        Owner owner = ownerJpaService.save(any());

        // then
        assertNotNull(owner);
        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        // given

        // when
        ownerJpaService.delete(any());

        // then
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        // given

        // when
        ownerJpaService.deleteById(anyLong());

        // then
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        // given
        Owner returnOwner = new Owner();
        returnOwner.setId(1L);
        returnOwner.setLastName("Smith");
        given(ownerRepository.findByLastName(anyString())).willReturn(returnOwner);

        // when
        Owner owner = ownerJpaService.findByLastName(anyString());

        // then
        assertNotNull(owner);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(anyString());
    }
}