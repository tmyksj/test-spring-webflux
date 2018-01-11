package tests.springwebflux.entity

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table
data class MessageEntity(@Id val id: String = UUID.randomUUID().toString(),
                         var author: String? = null,
                         var body: String? = null)
