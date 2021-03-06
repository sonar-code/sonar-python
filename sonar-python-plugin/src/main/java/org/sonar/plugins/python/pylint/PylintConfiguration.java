/*
 * SonarQube Python Plugin
 * Copyright (C) 2011-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.python.pylint;

import java.io.File;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.ExtensionPoint;
import org.sonar.api.batch.ScannerSide;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.config.Configuration;

@ScannerSide
@ExtensionPoint
public class PylintConfiguration {

  public static final String PYLINT_CONFIG_KEY = "sonar.python.pylint_config";
  public static final String PYLINT_KEY = "sonar.python.pylint";

  private final Configuration conf;

  public PylintConfiguration(Configuration conf) {
    this.conf = conf;
  }

  public String getPylintConfigPath(FileSystem fileSystem) {
    String configPath = conf.get(PylintConfiguration.PYLINT_CONFIG_KEY).orElse("");
    if (StringUtils.isEmpty(configPath)) {
      return null;
    }
    File configFile = new File(configPath);
    if (!configFile.isAbsolute()) {
      File projectRoot = fileSystem.baseDir();
      configFile = new File(projectRoot.getPath(), configPath);
    }
    return configFile.getAbsolutePath();
  }

  public String getPylintPath() {
    return conf.get(PylintConfiguration.PYLINT_KEY).orElse(null);
  }

}
