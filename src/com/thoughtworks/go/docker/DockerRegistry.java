package com.thoughtworks.go.docker;

import com.thoughtworks.go.plugin.api.material.packagerepository.PackageConfiguration;
import com.thoughtworks.go.plugin.api.material.packagerepository.RepositoryConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.MessageFormat;

public class DockerRegistry {
    private RepositoryConfiguration repositoryConfiguration;

    public DockerRegistry(RepositoryConfiguration repositoryConfiguration) {
        this.repositoryConfiguration = repositoryConfiguration;
    }

    public DockerTag getLatestTag(PackageConfiguration packageConfiguration) {
        JSONArray jsonTags = jsonForTagsForRepository(packageConfiguration);
        JSONObject latestTag = getLatestTag(jsonTags);
        return new DockerTag("latest", latestTag.getString("layer"));
    }

    private JSONObject getLatestTag(JSONArray tags) {
        for (int i = 0; i < tags.length(); i++) {
            if (tags.getJSONObject(i).getString("name").contentEquals("latest")) {
                return tags.getJSONObject(i);
            }
        }
        return null;
    }

    private JSONArray jsonForTagsForRepository(PackageConfiguration packageConfiguration) {
        DefaultHttpClient client = new DefaultHttpClient();

        try {
            String urlForTags = MessageFormat.format("https://{0}/v1/repositories/{1}/tags",
                    repositoryConfiguration.get("REGISTRY_URL").getValue(),
                    packageConfiguration.get("REPOSITORY_NAME").getValue());
            HttpGet google = new HttpGet(urlForTags);
            HttpResponse response = client.execute(google);
            String jsonString = EntityUtils.toString(response.getEntity());
            return new JSONObject("{\"tags\":" + jsonString + "}").getJSONArray("tags");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
