grails {
    mongodb {
        host = "localhost"
        port = 27017
        username = "examinator-server-app"
        password = "Examinator123"
        databaseName = "examinator"

        // whether to use stateless sessions by default
        stateless = false

        options {
            // The maximum number of connections allowed per host
            connectionsPerHost = 10

            threadsAllowedToBlockForConnectionMultiplier = 5

            // Max wait time of a blocking thread for a connection.
            maxWaitTime = 120000

            // The connect timeout in milliseconds. 0 == infinite
            connectTimeout = 0

            // The socket timeout. 0 == infinite
            socketTimeout = 0

            // Whether or not to have socket keep alive turned on
            socketKeepAlive = false

            // Specifies if the driver should use an SSL connection to Mongo
            sslEnabled = false
        }
    }
}