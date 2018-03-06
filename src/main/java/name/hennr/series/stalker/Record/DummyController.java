package name.hennr.series.stalker.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @Autowired
    private PersonRepository repository;

    @GetMapping("/record/get")
    public String get() {
        Person person = new Person();
        person.setData("bar");
        repository.save(person);
        return repository.findAll().get(0).getData();
    }
}
