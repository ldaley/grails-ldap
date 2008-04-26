import gldapo.Gldapo

class LdapGrailsPlugin {

    def version = "0.2.1"
    def dependsOn = [:]

    def author = 'Luke Daley'
    def authorEmail = 'ld@ldaley.com'
    def title = 'Adds easy to use LDAP connectivity'
    def description = "Utilises Gldapo (http://ldaley.com/gldapo) to provide an object oriented LDAP interface"
    def documentation = "http://grails.org/LDAP+Plugin"
    
    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }   

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)      
    }

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional)
    }                                         

    def doWithDynamicMethods = { ctx ->
        if (application.config.containsKey('ldap')) {
            Gldapo.initialize(application.config.ldap)
        } else {
            println "LdapGrailsPlugin: No LDAP config found!"
        }
    }

    def onChange = { event ->
        // TODO Implement code that is executed when this class plugin class is changed  
        // the event contains: event.application and event.applicationContext objects
    }                                                                                  

    def onApplicationChange = { event ->
        // TODO Implement code that is executed when any class in a GrailsApplication changes
        // the event contain: event.source, event.application and event.applicationContext objects
    }
}
