package tests.springwebflux.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import tests.springwebflux.handler.MessageHandler
import tests.springwebflux.handler.api.MessageApiHandler

@Configuration
class RouterConfiguration(private val messageHandler: MessageHandler,
                          private val messageApiHandler: MessageApiHandler) {

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        "/".nest {
            GET("/", messageHandler::index)
        }

        "/api".nest {
            ("/message" and accept(MediaType.APPLICATION_JSON)).nest {
                GET("/{id}", messageApiHandler::getMessage)
                POST("/", messageApiHandler::postMessage)
            }

            ("/message" and accept(MediaType.TEXT_EVENT_STREAM)).nest {
                GET("/", messageApiHandler::getMessageFlux)
            }
        }
    }

}
