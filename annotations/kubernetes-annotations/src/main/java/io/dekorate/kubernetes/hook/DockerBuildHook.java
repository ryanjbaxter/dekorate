/**
 * Copyright 2018 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.dekorate.kubernetes.hook;

import io.dekorate.hook.ProjectHook;
import io.dekorate.kubernetes.config.KubernetesConfig;
import io.dekorate.project.Project;
import io.dekorate.utils.Images;

import java.io.File;

public class DockerBuildHook extends ProjectHook {

  private final File dockerFile;
  private final String image;

  public DockerBuildHook(Project project, KubernetesConfig config ) {
    super(project);
    this.dockerFile = project.getRoot().resolve(config.getDockerFile()).toFile();
    this.image = Images.getImage(config.getRegistry(), config.getGroup(), config.getName(), config.getVersion());
  }

  @Override
  public void init() {

  }

  @Override
  public void warmup() {

  }

  @Override
  public void run() {
    exec("docker", "build", "-f" + dockerFile.getAbsolutePath(), "-t" + image, project.getRoot().toAbsolutePath().toString());
  }
}
