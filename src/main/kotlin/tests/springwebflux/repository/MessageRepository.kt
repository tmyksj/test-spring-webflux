package tests.springwebflux.repository

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import tests.springwebflux.entity.MessageEntity

interface MessageRepository : ReactiveCassandraRepository<MessageEntity, String>
