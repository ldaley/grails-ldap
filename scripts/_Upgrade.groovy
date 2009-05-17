if (new File("grails-app/ldap").exists() == false) {
    Ant.mkdir(dir:"grails-app/ldap")
}
