
class LdapGrailsPlugin {
    def version = 0.1
    def author = 'Luke Daley'
    def authorEmail = 'ld@ldaley.com'
    def title = 'Makes accessing LDAP servers quick and easy.'
    def description = '''\
This plugin simplifies the use of LDAP in your application by
making the configuration easy and providing dynamic finder methods
for stress-free searching.
'''
    def documentation = 'http://grails.codehaus.org/LDAP+Plugin'

    def dependsOn = [:]
    
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
        // TODO Implement additions to web.xml (optional)
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
