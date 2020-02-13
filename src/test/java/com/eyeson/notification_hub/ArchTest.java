package com.eyeson.notification_hub;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.eyeson.notification_hub");

        noClasses()
            .that()
                .resideInAnyPackage("com.eyeson.notification_hub.service..")
            .or()
                .resideInAnyPackage("com.eyeson.notification_hub.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.eyeson.notification_hub.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
