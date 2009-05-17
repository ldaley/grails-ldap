import gldapo.schema.annotation.GldapoNamingAttribute

class Person {

    Set objectClass = ["top", "person", "organizationalPerson"]

    @GldapoNamingAttribute
    String cn
    
    String sn
}