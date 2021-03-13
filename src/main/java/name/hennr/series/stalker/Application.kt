package name.hennr.series.stalker

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class Application {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(Application::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
            LOG.info("up and running")
        }
    }
}
