package tests.springwebflux.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification

@Configuration
class CassandraConfiguration(private val cassandraConfigurationProperties: CassandraConfigurationProperties)
    : AbstractReactiveCassandraConfiguration() {

    override fun getContactPoints(): String = cassandraConfigurationProperties.contactpoints
    override fun getEntityBasePackages(): Array<String> = arrayOf("tests.springwebflux.entity")
    override fun getKeyspaceCreations(): MutableList<CreateKeyspaceSpecification> = mutableListOf(
            CreateKeyspaceSpecification
                    .createKeyspace(cassandraConfigurationProperties.keyspace)
                    .withSimpleReplication()
                    .ifNotExists())
    override fun getKeyspaceName(): String = cassandraConfigurationProperties.keyspace
    override fun getPort(): Int = cassandraConfigurationProperties.port.toInt()
    override fun getSchemaAction(): SchemaAction = SchemaAction.CREATE_IF_NOT_EXISTS

}
