/*
 * Sonar Java
 * Copyright (C) 2012 SonarSource
 * dev@sonar.codehaus.org
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
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.java.jacoco;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

/**
 * JUnit listener that instructs JaCoCo to create one session per test.
 */
public class JUnitListener extends RunListener {

  private final JacocoController jacoco;

  public JUnitListener() {
    this(new JacocoController());
  }

  JUnitListener(JacocoController jacoco) {
    this.jacoco = jacoco;
  }

  @Override
  public void testStarted(Description description) throws Exception {
    jacoco.onTestStart(getName(description));
  }

  @Override
  public void testFinished(Description description) throws Exception {
    jacoco.onTestFinish(getName(description));
  }

  private static String getName(Description description) {
    return description.getClassName() + " " + description.getMethodName();
  }

}
