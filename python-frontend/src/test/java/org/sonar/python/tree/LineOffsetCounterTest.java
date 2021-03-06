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
package org.sonar.python.tree;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineOffsetCounterTest {

  @Test
  public void test() {
    LineOffsetCounter offsetCounter = new LineOffsetCounter("foo");
    assertThat(offsetCounter.findLine(1)).isEqualTo(1);
    assertThat(offsetCounter.findColumn(1,1)).isEqualTo(1);

    offsetCounter = new LineOffsetCounter("foo\nbar");
    assertThat(offsetCounter.findLine(4)).isEqualTo(2);
    assertThat(offsetCounter.findColumn(2,6)).isEqualTo(2);

    offsetCounter = new LineOffsetCounter("foo\rbar");
    assertThat(offsetCounter.findLine(4)).isEqualTo(2);
    assertThat(offsetCounter.findColumn(2,6)).isEqualTo(2);

    offsetCounter = new LineOffsetCounter("foo\n\rbar");
    assertThat(offsetCounter.findLine(4)).isEqualTo(2);
    assertThat(offsetCounter.findColumn(2,6)).isEqualTo(2);

    offsetCounter = new LineOffsetCounter("foo\r\nbar");
    assertThat(offsetCounter.findLine(4)).isEqualTo(1);
    assertThat(offsetCounter.findColumn(2,6)).isEqualTo(1);

    offsetCounter = new LineOffsetCounter("foobar\r");
    assertThat(offsetCounter.findLine(4)).isEqualTo(1);
    assertThat(offsetCounter.findColumn(2,7)).isEqualTo(0);
  }
}
