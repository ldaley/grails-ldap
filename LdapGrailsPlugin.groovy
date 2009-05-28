import gldapo.Gldapo
import grails.ldap.LdapClassArtefactHandler

class LdapGrailsPlugin {

    def version = "0.8.2"
    def dependsOn = [:]

    def author = 'Luke Daley'
    def authorEmail = 'ld@ldaley.com'
    def title = 'Adds easy to use LDAP connectivity'
    def description = "Utilises Gldapo (http://gldapo.codehaus.org) to provide an object oriented LDAP interface"
    def documentation = "http://grails.org/plugin/ldap"
    def artefacts = [LdapClassArtefactHandler]
    def watchedResources = ["file:./grails-app/ldap/**", "file:./grails-app/conf/*Config.groovy"]
    
    def pluginExcludes = [
        "grails-app/conf/Config.groovy",
        "grails-app/utils/LdapServer.groovy",
        "grails-app/ldap/*",
        "grails-app/spring/resources.groovy",
        "test-lib",
        "scripts/_Events.groovy",
        "server-work"
    ]
    
    def doWithSpring = {
        gldapo(Gldapo)
    }

    def mergeLdapClassesIntoConfig(ldapClasses, config) {
        def mergedConfig = (config.size() > 0) ? config.clone() : [:]
        
        if (mergedConfig.schemas == null || mergedConfig.schemas instanceof ConfigObject) mergedConfig.schemas = []
        
        ldapClasses.clazz.each {
            if (mergedConfig.schemas.contains(it) == false) mergedConfig.schemas << it
        }
        
        mergedConfig
    }
    
    def doWithDynamicMethods = { ctx ->
        def config = mergeLdapClassesIntoConfig(application.ldapClasses, application.config.ldap)
        ctx.getBean("gldapo").consumeConfig(config)
    }

    def onChange = { event ->
        def config = mergeLdapClassesIntoConfig(event.application.ldapClasses, event.application.config.ldap)
        event.ctx.getBean("gldapo").resetWithConfig(config)
    }
}
