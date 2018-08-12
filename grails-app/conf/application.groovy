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

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.examinator.security.authentication.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.examinator.security.authentication.UserRole'
grails.plugin.springsecurity.authority.className = 'com.examinator.security.authentication.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [

    [pattern: '/login/auth',                access: ['permitAll']],
    [pattern: '/login/authenticate',        access: ['permitAll']],
    [pattern: '/login/**',                  access: ['permitAll']],

    [pattern: '/',                          access: ['isFullyAuthenticated()']],
	[pattern: '/index',          			access: ['isFullyAuthenticated()']],
	[pattern: '/index.gsp',      			access: ['isFullyAuthenticated()']],

	[pattern: '/aclClass/**',               access: ['ROLE_ADMIN']],
	[pattern: '/aclEntry/**',               access: ['ROLE_ADMIN']],
	[pattern: '/aclObjectIdentity/**',      access: ['ROLE_ADMIN']],
	[pattern: '/aclSid/**',                 access: ['ROLE_ADMIN']],
    [pattern: '/persistentLogin/**',        access: ['ROLE_ADMIN']],
	[pattern: '/register/**',               access: ['ROLE_ADMIN']],
	[pattern: '/registration/**',           access: ['ROLE_ADMIN']],
	[pattern: '/registrationCode/**',       access: ['ROLE_ADMIN']],
	[pattern: '/requestMap/**',             access: ['ROLE_ADMIN']],
	[pattern: '/role/**',                   access: ['ROLE_ADMIN']],
	[pattern: '/securityInfo/**',           access: ['ROLE_ADMIN']],
	[pattern: '/user/**',                   access: ['ROLE_ADMIN']],

	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

