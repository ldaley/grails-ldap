package grails.ldap;

import org.codehaus.groovy.grails.commons.*;
import gldapo.schema.annotation.GldapoNamingAttribute;
import java.lang.reflect.Field;

public class LdapClassArtefactHandler extends ArtefactHandlerAdapter {

    public LdapClassArtefactHandler() {
        super("Ldap", GrailsLdapClass.class, DefaultGrailsLdapClass.class, null);
    }

    public GrailsClass newArtefactClass(Class artefactClass) {
        return new DefaultGrailsLdapClass(artefactClass);
    }

    public boolean isArtefactClass(Class clazz) {
        return isLdapClass(clazz);
    }

    public static boolean isLdapClass(Class clazz) {
        if (clazz == null) return false;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(GldapoNamingAttribute.class) != null)
                return true;
        }
        return false;
    }
}