package tests.springwebflux.entity

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.Table

@Table
data class MessageEntity(@Id var id: String? = null,
                         var author: String? = null,
                         var body: String? = null)
