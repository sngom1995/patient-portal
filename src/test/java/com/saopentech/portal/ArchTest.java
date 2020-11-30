package com.saopentech.portal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.saopentech.portal");

        noClasses()
            .that()
            .resideInAnyPackage("com.saopentech.portal.service..")
            .or()
            .resideInAnyPackage("com.saopentech.portal.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.saopentech.portal.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
