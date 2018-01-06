package tests.springwebflux.repository

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier

@RunWith(SpringRunner::class)
@SpringBootTest
class MessageRepositoryTests {

    @Autowired
    private lateinit var messageRepository: MessageRepository

    @Test
    fun readsMessageTableCorrectly() {
        StepVerifier
                .create(messageRepository.findAll())
                .verifyComplete()
    }

}
