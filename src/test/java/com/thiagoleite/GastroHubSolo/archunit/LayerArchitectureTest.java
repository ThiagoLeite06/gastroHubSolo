package com.thiagoleite.GastroHubSolo.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.thiagoleite", importOptions = {
        ImportOption.DoNotIncludeArchives.class,
        ImportOption.DoNotIncludeJars.class,
        ImportOption.DoNotIncludeTests.class
})
public class LayerArchitectureTest {
    static String CORE_LAYER_PACKAGES = "com.thiagoleite.GastroHubSolo.core..";
    static String DATA_LAYER_PACKAGES = "com.thiagoleite.GastroHubSolo.dataprovider..";
    static String API_LAYER_PACKAGES = "com.thiagoleite.GastroHubSolo.entrypoint..";

    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(CORE_LAYER_PACKAGES).definedBy(CORE_LAYER_PACKAGES)
            .layer(DATA_LAYER_PACKAGES).definedBy(DATA_LAYER_PACKAGES)
            .layer(API_LAYER_PACKAGES).definedBy(API_LAYER_PACKAGES)

            .whereLayer(DATA_LAYER_PACKAGES).mayNotBeAccessedByAnyLayer()
            .whereLayer(CORE_LAYER_PACKAGES).mayOnlyBeAccessedByLayers(API_LAYER_PACKAGES, DATA_LAYER_PACKAGES)
            .whereLayer(API_LAYER_PACKAGES).mayNotBeAccessedByAnyLayer();
}
