package com.thoughtworks.go.docker;

import com.thoughtworks.go.plugin.api.config.Property;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageMaterialConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageMaterialProperty;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import com.thoughtworks.go.plugin.api.response.validation.ValidationResult;

public class DockerMaterialConfiguration implements PackageMaterialConfiguration {

    public static final Property REGISTRY_URL = new PackageMaterialProperty("REGISTRY_URL")
            .with(Property.DISPLAY_NAME, "Registry Url").with(Property.DISPLAY_ORDER, 0);
    public static final Property USERNAME = new PackageMaterialProperty("USERNAME")
            .with(Property.DISPLAY_NAME, "Username").with(Property.DISPLAY_ORDER, 1);
    public static final Property PASSWORD = new PackageMaterialProperty("PASSWORD")
            .with(Property.DISPLAY_NAME, "Password").with(Property.DISPLAY_ORDER, 2);
    public static final Property REPOSITORY_NAME = new Property("REPOSITORY_NAME")
            .with(Property.DISPLAY_NAME, "Repository name");

    @Override
    public RepositoryConfiguration getRepositoryConfiguration() {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        repositoryConfiguration.add(REGISTRY_URL);
        repositoryConfiguration.add(USERNAME);
        repositoryConfiguration.add(PASSWORD);
        return repositoryConfiguration;
    }

    @Override
    public PackageConfiguration getPackageConfiguration() {
        PackageConfiguration packageConfiguration = new PackageConfiguration();
        packageConfiguration.add(REPOSITORY_NAME);
        return packageConfiguration;
    }

    @Override
    public ValidationResult isRepositoryConfigurationValid(RepositoryConfiguration repositoryConfiguration) {
        return new ValidationResult();
    }

    @Override
    public ValidationResult isPackageConfigurationValid(PackageConfiguration packageConfiguration, RepositoryConfiguration repositoryConfiguration) {
        return new ValidationResult();
    }
}
