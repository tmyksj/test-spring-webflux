package tests.springwebflux.service.api.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.DirectProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxProcessor
import reactor.core.publisher.Mono
import tests.springwebflux.entity.MessageEntity
import tests.springwebflux.repository.MessageRepository
import tests.springwebflux.service.api.MessageApiService
import javax.annotation.PostConstruct

@Service
class MessageApiServiceImpl(private val messageRepository: MessageRepository) : MessageApiService {

    private lateinit var fluxProcessor: FluxProcessor<MessageEntity, MessageEntity>
    private lateinit var messageFlux: Flux<MessageEntity>

    @PostConstruct
    fun postConstruct() {
        fluxProcessor = DirectProcessor.create()
        messageFlux = Flux.concat(messageRepository.findAll(), fluxProcessor)
    }

    override fun getMessage(id: String): Mono<MessageEntity> {
        return messageRepository.findById(id)
    }

    override fun getMessageFlux(): Flux<MessageEntity> {
        return messageFlux
    }

    override fun postMessage(messageEntity: Mono<MessageEntity>): Mono<MessageEntity> {
        return messageRepository.saveAll(messageEntity).next().map {
            fluxProcessor.onNext(it)
            it
        }
    }

}
