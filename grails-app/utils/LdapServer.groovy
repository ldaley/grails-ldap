import org.apache.directory.server.core.DefaultDirectoryService
import org.apache.directory.server.ldap.LdapService
import org.apache.directory.server.protocol.shared.SocketAcceptor
import org.apache.directory.shared.ldap.name.LdapDN
import org.apache.directory.server.core.partition.impl.btree.jdbm.JdbmPartition
import org.apache.directory.server.core.partition.impl.btree.jdbm.JdbmIndex
import org.springframework.beans.factory.InitializingBean
import org.apache.directory.shared.ldap.exception.LdapNameNotFoundException

class LdapServer implements InitializingBean {
    
    private directoryService
    private ldapService
    
    void afterPropertiesSet()
    {
        def workDir = new File("server-work")
        if (workDir.exists()) workDir.deleteDir()
        
        directoryService = new DefaultDirectoryService()
                            
        directoryService.changeLog.enabled = false
        def partition = addPartition("grails", "dc=grails,dc=org")
        addIndex(partition, "objectClass", "ou", "uid")
        directoryService.startup()

        try {
            directoryService.adminSession.lookup(partition.suffixDn)
        }
        catch (LdapNameNotFoundException lnnfe) {
            def dn = new LdapDN("dc=grails,dc=org")
            def entry = directoryService.newEntry(dn)
            entry.add("objectClass", "top", "domain", "extensibleObject")
            entry.add("dc", "grails")
            directoryService.adminSession.add(entry)
        }
        
        ldapService = new LdapService()
        ldapService.socketAcceptor = new SocketAcceptor(null)
        ldapService.directoryService = directoryService
        ldapService.ipPort = 10389
        ldapService.start()
    }
        
    def addPartition(partitionId, partitionDn) {
        def partition = new JdbmPartition()
        partition.id = partitionId
        partition.suffix = partitionDn
        directoryService.addPartition(partition)

        partition
    }

    void addIndex(partition, String[] attrs) {
        partition.indexedAttributes = attrs.collect { new JdbmIndex(it) } as Set
    }
}