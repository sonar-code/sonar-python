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

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Token;
import java.util.Arrays;
import java.util.List;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import org.sonar.python.api.tree.PyExpressionTree;
import org.sonar.python.api.tree.PySliceItemTree;
import org.sonar.python.api.tree.PyTreeVisitor;
import org.sonar.python.api.tree.Tree;

public class PySliceItemTreeImpl extends PyTree implements PySliceItemTree {

  private final PyExpressionTree lowerBound;
  private final Token boundSeparator;
  private final PyExpressionTree upperBound;
  private final Token strideSeparator;
  private final PyExpressionTree stride;

  public PySliceItemTreeImpl(
    AstNode node, @Nullable PyExpressionTree lowerBound, Token boundSeparator, @Nullable PyExpressionTree upperBound,
    @Nullable Token strideSeparator, @Nullable PyExpressionTree stride
  ) {
    super(node);
    this.lowerBound = lowerBound;
    this.boundSeparator = boundSeparator;
    this.upperBound = upperBound;
    this.strideSeparator = strideSeparator;
    this.stride = stride;
  }

  @CheckForNull
  @Override
  public PyExpressionTree lowerBound() {
    return lowerBound;
  }

  @Override
  public Token boundSeparator() {
    return boundSeparator;
  }

  @CheckForNull
  @Override
  public PyExpressionTree upperBound() {
    return upperBound;
  }

  @CheckForNull
  @Override
  public Token strideSeparator() {
    return strideSeparator;
  }

  @CheckForNull
  @Override
  public PyExpressionTree stride() {
    return stride;
  }

  @Override
  public void accept(PyTreeVisitor visitor) {
    visitor.visitSliceItem(this);
  }

  @Override
  public List<Tree> children() {
    return Arrays.asList(lowerBound, upperBound, stride);
  }

  @Override
  public Kind getKind() {
    return Kind.SLICE_ITEM;
  }
}