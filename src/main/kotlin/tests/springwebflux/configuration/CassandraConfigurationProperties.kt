package tests.springwebflux.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cassandra")
class CassandraConfigurationProperties {
    lateinit var contactpoints: String
    lateinit var port: String
    lateinit var keyspace: String
}
