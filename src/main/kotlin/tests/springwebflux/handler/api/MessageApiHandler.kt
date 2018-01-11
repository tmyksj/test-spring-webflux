package tests.springwebflux.handler.api

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import tests.springwebflux.entity.MessageEntity
import tests.springwebflux.service.api.MessageApiService

@Component
class MessageApiHandler(private val messageApiService: MessageApiService) {

    fun getMessage(request: ServerRequest): Mono<ServerResponse> {
        val id: String = request.pathVariable("id")
        return messageApiService.getMessage(id)
                .flatMap {
                    ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromObject(it))
                }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun getMessageFlux(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(messageApiService.getMessageFlux())
    }

    fun postMessage(request: ServerRequest): Mono<ServerResponse> {
        val message: Mono<MessageEntity> = request.bodyToMono()
        return messageApiService.postMessage(message)
                .flatMap {
                    ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromObject(it))
                }
    }

}
