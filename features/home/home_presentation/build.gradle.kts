apply {
    from("$rootDir/compose-module.gradle")
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUtil))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.homeDomain))
    "implementation"(project(Modules.viewModel))

    // Paging
    "implementation"(Paging.pagingCompose)

    // Coil
    "implementation"(Coil.coilCompose)
}