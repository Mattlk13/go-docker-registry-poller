package com.thoughtworks.go.docker;

import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageMaterialPoller;
import com.thoughtworks.go.plugin.api.material.packagerepository.PackageRevision;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import com.thoughtworks.go.plugin.api.response.Result;

import java.util.Date;

public class DockerMaterialPoller implements PackageMaterialPoller {
    @Override
    public PackageRevision getLatestRevision(PackageConfiguration packageConfiguration, RepositoryConfiguration repositoryConfiguration) {
        DockerTag tag = new DockerRegistry(repositoryConfiguration).getLatestTag(packageConfiguration);
        return new PackageRevision(tag.getSha(), new Date(), packageConfiguration.get("USERNAME").getValue());
    }

    @Override
    public PackageRevision latestModificationSince(PackageConfiguration packageConfiguration, RepositoryConfiguration repositoryConfiguration, PackageRevision packageRevision) {
        DockerTag tag = new DockerRegistry(repositoryConfiguration).getLatestTag(packageConfiguration);
        if(!tag.getSha().contentEquals(packageRevision.getRevision())) {
            return new PackageRevision(tag.getSha(), new Date(), packageConfiguration.get("USERNAME").getValue());
        }
        return null;
    }

    @Override
    public Result checkConnectionToRepository(RepositoryConfiguration repositoryConfiguration) {
        return new Result().withSuccessMessages("Successfully accessed registry");
    }

    @Override
    public Result checkConnectionToPackage(PackageConfiguration packageConfiguration, RepositoryConfiguration repositoryConfiguration) {
        return new Result().withSuccessMessages("Successfully accessed package");
    }
}
