ldap {
    directories {
        localhost {
            url = "ldap://localhost:10389"
            base = "dc=grails,dc=org"
            userDn = "uid=admin,ou=system"
            password = "secret"
        }
    }
}