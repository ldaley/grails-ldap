addedTestLib = false

eventCompileStart = { type ->
    if (!addedTestLib) {
        ant.path(id: "grails.compile.classpath") {
            new File("apacheds").eachFile { file ->
                if (file.name.endsWith("jar"))
                    fileset(file: file.absolutePath)
                    classLoader.addURL(file.toURL())
            }
        }
        addedTestLib = true
    }
}