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
 * 
**/


package io.dekorate.examples.component;

import io.dekorate.deps.kubernetes.api.model.KubernetesList;
import io.dekorate.deps.kubernetes.api.model.HasMetadata;
import io.dekorate.component.model.Component;
import io.dekorate.component.model.Link;
import io.dekorate.utils.Serialization;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Issue52Test {

  @Test
  public void shouldContainBuildConfig() {
    KubernetesList list = Serialization.unmarshal(Issue52Test.class.getClassLoader().getResourceAsStream("META-INF/dekorate/component.yml"));
    assertNotNull(list);
    List<HasMetadata> items = list.getItems();
    assertEquals(1, items.size());
    Component component = (Component) items.get(0);
    assertEquals("Component", component.getKind());
    //TODO: This is breaking the release because the git repo is being changed to local, during release
    //assertEquals("https://github.com/dekorateio/dekorate.git", component.getSpec().getBuildConfig().getUri());
    assertEquals("s2i", component.getSpec().getBuildConfig().getType());
    assertEquals("issue-52", component.getSpec().getBuildConfig().getModuleDirName());
    assertNotNull("", component.getSpec().getBuildConfig().getRef());
  }
}
