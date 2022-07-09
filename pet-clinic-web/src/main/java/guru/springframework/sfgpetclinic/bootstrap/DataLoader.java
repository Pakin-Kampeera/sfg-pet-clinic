package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.service.OwnerService;
import guru.springframework.sfgpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    @Override
    public void run(String... args) {
        loadData();
    }

    private void loadData() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Michel");
        owner1.setLastName("Winston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Stephen");
        owner2.setLastName("Elv");

        ownerService.save(owner2);

        log.info("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Frank");
        vet1.setLastName("Prop");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Foo");
        vet2.setLastName("Bar");

        vetService.save(vet2);

        log.info("Loaded Vets...");
    }
}
