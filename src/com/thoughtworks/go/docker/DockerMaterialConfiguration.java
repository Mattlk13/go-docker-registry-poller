package com.thoughtworks.go.docker;

import com.thoughtworks.go.plugin.api.config.Property;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageMaterialConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import com.thoughtworks.go.plugin.api.response.validation.ValidationResult;

public class DockerMaterialConfiguration implements PackageMaterialConfiguration {
    @Override
    public RepositoryConfiguration getRepositoryConfiguration() {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        repositoryConfiguration.add(new Property("REGISTRY_URL").with(Property.DISPLAY_NAME, "Registry Url").with(Property.DISPLAY_ORDER, 0));
        repositoryConfiguration.add(new Property("USERNAME").with(Property.DISPLAY_NAME, "Username").with(Property.DISPLAY_ORDER, 1));
        repositoryConfiguration.add(new Property("PASSWORD").with(Property.DISPLAY_NAME, "Password").with(Property.DISPLAY_ORDER, 2));
        return repositoryConfiguration;
    }

    @Override
    public PackageConfiguration getPackageConfiguration() {
        PackageConfiguration packageConfiguration = new PackageConfiguration();
        packageConfiguration.add(new Property("REPOSITORY_NAME"));
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
