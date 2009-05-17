import gldapo.schema.annotation.GldapoNamingAttribute

class Group {

    @GldapoNamingAttribute
    String cn
    
    Set objectClass = ["top", "groupOfUniqueNames"]
    Set uniqueMember

}