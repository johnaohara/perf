package perf.diff;

import org.junit.Test;

import java.util.List;

/**
 * Created by wreicher
 */
public class XmlDiffTest {

    @Test
    public void testDeletes(){
        String xml1 =
                "            <subsystem xmlns=\"urn:jboss:domain:datasources:4.0\">\n" +
                "                <datasources>\n" +
                "                    <datasource jndi-name=\"java:jboss/datasources/ExampleDS\" pool-name=\"ExampleDS\" enabled=\"true\" use-java-context=\"true\">\n" +
                "                        <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>\n" +
                "                        <driver>h2</driver>\n" +
                "                        <security>\n" +
                "                            <user-name>sa</user-name>\n" +
                "                            <password>sa</password>\n" +
                "                        </security>\n" +
                "                    </datasource>\n" +
                "                    <datasource jndi-name=\"java:/jdbc/SPECjSupplierDS\" pool-name=\"SPECjSupplierNonXADS\" enabled=\"true\" connectable=\"true\">\n" +
                "                        <connection-url>jdbc:postgresql://benchserver3G2:5433/specdb</connection-url>\n" +
                "                        <driver>postgresql</driver>\n" +
                "                        <connection-property name=\"tcpKeepAlive\">true</connection-property>\n" +
                "                            <connection-property name=\"logLevel\">0</connection-property>\n" +
                "                            <connection-property name=\"disableColumnSanitiser\">true</connection-property>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <pool>\n" +
                "                            <min-pool-size>25</min-pool-size>\n" +
                "                            <max-pool-size>25</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <blocking-timeout-millis>20000</blocking-timeout-millis>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>false</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </datasource>\n" +
                "                    <datasource jndi-name=\"java:/jdbc/SPECjOrderDS\" pool-name=\"SPECjOrderNonXADS\" enabled=\"true\" connectable=\"true\">\n" +
                "                        <connection-url>jdbc:postgresql://benchserver3G1:5432/specdb</connection-url>\n" +
                "                        <driver>postgresql</driver>\n" +
                "                        <connection-property name=\"tcpKeepAlive\">true</connection-property>\n" +
                "                        <connection-property name=\"logLevel\">0</connection-property>\n" +
                "                        <connection-property name=\"disableColumnSanitiser\">true</connection-property>\n" +
                "                         <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <pool>\n" +
                "                            <min-pool-size>29</min-pool-size>\n" +
                "                            <max-pool-size>29</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <blocking-timeout-millis>20000</blocking-timeout-millis>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>false</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </datasource>\n" +
                "                    <datasource jndi-name=\"java:/jdbc/SPECjMfgDS\" pool-name=\"SPECjMfgNonXADS\" enabled=\"true\" connectable=\"true\">\n" +
                "                        <connection-url>jdbc:postgresql://benchserver3G2:5433/specdb</connection-url>\n" +
                "                        <driver>postgresql</driver>\n" +
                "                        <connection-property name=\"tcpKeepAlive\">true</connection-property>\n" +
                "                         <connection-property name=\"logLevel\">0</connection-property>\n" +
                "                        <connection-property name=\"disableColumnSanitiser\">true</connection-property>\n" +
                "                         <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <pool>\n" +
                "                            <min-pool-size>36</min-pool-size>\n" +
                "                            <max-pool-size>36</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <blocking-timeout-millis>20000</blocking-timeout-millis>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>false</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjOrderXADS\" pool-name=\"SPECjOrderDS\" enabled=\"false\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            benchserver3G1\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"logLevel\">\n" +
                "                            0\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>35</min-pool-size>\n" +
                "                            <max-pool-size>35</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                            <fair>false</fair>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjMfgXADS\" pool-name=\"SPECjMfgDS\" use-ccm=\"false\" enabled=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            benchserver3G2\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5433\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>30</min-pool-size>\n" +
                "                            <max-pool-size>30</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                            <fair>false</fair>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <blocking-timeout-millis>20000</blocking-timeout-millis>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjSupplierDS\" pool-name=\"SPECjSupplierXADS\" use-ccm=\"false\" enabled=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            benchserver3G2\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5433\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"logLevel\">\n" +
                "                            0\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>19</min-pool-size>\n" +
                "                            <max-pool-size>19</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                            <fair>false</fair>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjLoaderDS\" pool-name=\"SPECjLoaderDS\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            benchserver3G1\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>1</min-pool-size>\n" +
                "                            <max-pool-size>1</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                            <fair>false</fair>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <drivers>\n" +
                "                        <driver name=\"h2\" module=\"com.h2database.h2\">\n" +
                "                            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>\n" +
                "                        </driver>\n" +
                "                        <driver name=\"postgresql\" module=\"org.postgresql\">\n" +
                "                            <driver-class>org.postgresql.Driver</driver-class>\n" +
                "                        </driver>\n" +
                "                        <driver name=\"postgresql-xa\" module=\"org.postgresql\">\n" +
                "                            <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>\n" +
                "                        </driver>\n" +
                "                    </drivers>\n" +
                "                </datasources>\n" +
                "            </subsystem>\n";
        String xml2 =
                "            <subsystem xmlns=\"urn:jboss:domain:datasources:4.0\">\n" +
                "                <datasources>\n" +
                "                    <datasource jndi-name=\"java:jboss/datasources/ExampleDS\" pool-name=\"ExampleDS\" enabled=\"true\" use-java-context=\"true\">\n" +
                "                        <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>\n" +
                "                        <driver>h2</driver>\n" +
                "                        <security>\n" +
                "                            <user-name>sa</user-name>\n" +
                "                            <password>sa</password>\n" +
                "                        </security>\n" +
                "                    </datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjOrderDS\" pool-name=\"SPECjOrderDS\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            w520\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"logLevel\">\n" +
                "                            0\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>10</min-pool-size>\n" +
                "                            <max-pool-size>75</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjMfgDS\" pool-name=\"SPECjMfgDS\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            w520\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>10</min-pool-size>\n" +
                "                            <max-pool-size>75</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <blocking-timeout-millis>20000</blocking-timeout-millis>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjSupplierDS\" pool-name=\"SPECjSupplierDS\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\" statistics-enabled=\"true\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            w520\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"logLevel\">\n" +
                "                            0\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"disableColumnSanitiser\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>10</min-pool-size>\n" +
                "                            <max-pool-size>40</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <xa-datasource jndi-name=\"java:/jdbc/SPECjLoaderDS\" pool-name=\"SPECjLoaderDS\" use-ccm=\"false\" mcp=\"org.jboss.jca.core.connectionmanager.pool.mcp.SemaphoreConcurrentLinkedDequeManagedConnectionPool\" enlistment-trace=\"false\">\n" +
                "                        <xa-datasource-property name=\"ServerName\">\n" +
                "                            w520\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"PortNumber\">\n" +
                "                            5432\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"DatabaseName\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"User\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"Password\">\n" +
                "                            specdb\n" +
                "                        </xa-datasource-property>\n" +
                "                        <xa-datasource-property name=\"tcpKeepAlive\">\n" +
                "                            true\n" +
                "                        </xa-datasource-property>\n" +
                "                        <driver>postgresql-xa</driver>\n" +
                "                        <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>\n" +
                "                        <xa-pool>\n" +
                "                            <min-pool-size>1</min-pool-size>\n" +
                "                            <max-pool-size>1</max-pool-size>\n" +
                "                            <prefill>true</prefill>\n" +
                "                        </xa-pool>\n" +
                "                        <security>\n" +
                "                            <user-name>specdb</user-name>\n" +
                "                            <password>specdb</password>\n" +
                "                        </security>\n" +
                "                        <validation>\n" +
                "                            <valid-connection-checker class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker\"/>\n" +
                "                            <validate-on-match>false</validate-on-match>\n" +
                "                            <background-validation>true</background-validation>\n" +
                "                            <exception-sorter class-name=\"org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter\"/>\n" +
                "                        </validation>\n" +
                "                        <timeout>\n" +
                "                            <idle-timeout-minutes>120</idle-timeout-minutes>\n" +
                "                        </timeout>\n" +
                "                        <statement>\n" +
                "                            <track-statements>FALSE</track-statements>\n" +
                "                            <prepared-statement-cache-size>64</prepared-statement-cache-size>\n" +
                "                            <share-prepared-statements>true</share-prepared-statements>\n" +
                "                        </statement>\n" +
                "                    </xa-datasource>\n" +
                "                    <drivers>\n" +
                "                        <driver name=\"h2\" module=\"com.h2database.h2\">\n" +
                "                            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>\n" +
                "                        </driver>\n" +
                "                        <driver name=\"postgresql\" module=\"org.postgresql\">\n" +
                "                            <driver-class>org.postgresql.Driver</driver-class>\n" +
                "                        </driver>\n" +
                "                        <driver name=\"postgresql-xa\" module=\"org.postgresql\">\n" +
                "                            <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>\n" +
                "                        </driver>\n" +
                "                    </drivers>\n" +
                "                </datasources>\n" +
                "            </subsystem>";

        XmlDiff diff = new XmlDiff();

        diff.addKeyAttribute("name");
        diff.addKeyAttribute("jndi-name");
        diff.addVersionAttribute("xmlns", 3);
        diff.addKeyAttribute("module");
        diff.addKeyAttribute("category");

        diff.loadFrom("L",xml1);
        diff.loadTo("R",xml2);

        List<Diff> diffs = diff.getDiff();

        diff.printList(diffs);
    }
}
