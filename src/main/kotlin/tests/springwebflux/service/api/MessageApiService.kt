package tests.springwebflux.service.api

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tests.springwebflux.entity.MessageEntity

interface MessageApiService {
    fun getMessage(id: String): Mono<MessageEntity>
    fun getMessageFlux(): Flux<MessageEntity>
    fun postMessage(messageEntity: Mono<MessageEntity>): Mono<MessageEntity>
}
