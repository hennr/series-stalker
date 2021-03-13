package name.hennr.series.stalker.record

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyController {
    @Autowired
    private val repository: PersonRepository? = null
    @GetMapping("/record/get")
    fun get(): String {
        val person = Person()
        person.data = "bar"
        repository!!.save(person)
        return repository.findAll()[0]?.data.toString()
    }
}
