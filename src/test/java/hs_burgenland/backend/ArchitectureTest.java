package hs_burgenland.backend;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "hs_burgenland.backend")
public class ArchitectureTest {

    /**
     * This rule checks that repository classes should only be accessed by classes in the repository or service package.
     */
    @ArchTest
    public static final ArchRule repositoryClassesShouldOnlyBeAccessedByServicesAndRepositories =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().onlyBeAccessed().byAnyPackage("..service..", "..repository..");

    /**
     * This rule checks that controller classes should only be accessed by classes in the service or controller package.
     */
    @ArchTest
    public static final ArchRule controllerClassesShouldOnlyBeAccessedByControllersAndServices =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    /**
     * This rule checks that services classes are in the service package.
     */
    @ArchTest
    public static final ArchRule serviceClassesShouldBeInServicePackage =
            classes().that().haveSimpleNameEndingWith("Service")
                    .should().resideInAPackage("..services..");

    /**
     * This rule checks that repository classes are in the repository package.
     */
    @ArchTest
    public static final ArchRule repositoryClassesShouldBeInRepositoryPackage =
            classes().that().haveSimpleNameEndingWith("Repository")
                    .should().resideInAPackage("..repositories..");

    /**
     * This rule checks that controller classes are in the controller package.
     */
    @ArchTest
    public static final ArchRule controllerClassesShouldBeInControllerPackage =
            classes().that().haveSimpleNameEndingWith("Controller")
                    .should().resideInAPackage("..controller..");
}
