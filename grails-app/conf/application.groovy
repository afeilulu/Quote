

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.hsdp.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.hsdp.UserRole'
grails.plugin.springsecurity.authority.className = 'com.hsdp.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/application/**', access: ['permitAll']],
	[pattern: '/admin/',		     access: ['permitAll']],
	[pattern: '/static/**',       access: ['permitAll']],
	[pattern: '/weChat/**',       access: ['permitAll']],
	[pattern: '/image/**',       access: ['permitAll']],
	[pattern: '/rest/**', access: ['permitAll']],
	[pattern: '/rest/public/**', access: ['permitAll']],
	[pattern: '/api/**', access: ['permitAll']]
]

grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true

grails.plugin.springsecurity.filterChain.chainMap = [
		//Public chain
		[
				pattern: '/rest/public/**',
				filters: 'anonymousAuthenticationFilter,restTokenValidationFilter,restExceptionTranslationFilter,filterInvocationInterceptor'
		],
		//Stateless chain
		[
				pattern: '/rest/**',
				filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
		],
		//Traditional chain
		[
				pattern: '/**',
				filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
		]
]