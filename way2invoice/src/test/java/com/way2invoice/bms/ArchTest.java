package com.way2invoice.bms;

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
            .importPackages("com.way2invoice.bms");

        noClasses()
            .that()
                .resideInAnyPackage("com.way2invoice.bms.service..")
            .or()
                .resideInAnyPackage("com.way2invoice.bms.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.way2invoice.bms.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
