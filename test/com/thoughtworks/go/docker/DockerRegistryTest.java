package com.thoughtworks.go.docker;

import com.thoughtworks.go.plugin.api.config.Property;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.*;

public class DockerRegistryTest {

    @Test
    public void shouldGetLatestTagFromRepository() throws Exception {
        RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration();
        repositoryConfiguration.add(new Property("REGISTRY_URL").withDefault("index.docker.io"));
        PackageConfiguration packageConfiguration = new PackageConfiguration();
        packageConfiguration.add(new Property("REPOSITORY_NAME").withDefault("gtjenkins/oauth2provider"));
        DockerTag latestTag = new DockerRegistry(repositoryConfiguration).getLatestTag(packageConfiguration);
        assertNotNull(latestTag);
    }
}