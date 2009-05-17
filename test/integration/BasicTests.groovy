class BasicTests extends GroovyTestCase {

    def gldapo
    def grailsApplication
    
    void testFoundSchemas() {
        assertEquals(2, grailsApplication.ldapClasses.size())
    }
    
    void testGldapoBean() {
        assertNotNull(gldapo)
        assertEquals(2, gldapo.schemas.size())
        assertEquals(1, gldapo.directories.size())
    }
    
    void testAddPerson() {
        def p = new Person()
        p.cn = "add"
        p.sn = "person"
        p.save()
        
        def l = Person.find { eq("sn", "person") }
        assertNotNull(l)
    }
    
    void testAddGroupAndPeople() {
        def people = (1..10).collect {
            def p = new Person()
            p.cn = "groupperson$it"
            p.sn = "groupperson$it"
            p.save()
            p
        }
        def g = new Group()
        g.cn = "group"
        g.uniqueMember = people.dn
        g.save()
    }
}