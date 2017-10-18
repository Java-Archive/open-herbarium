/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.openherbarium.module.vaadin.generic.bootstrap;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;

@WebServlet(
    urlPatterns = "/*",
    name = "JumpstartServlet",
    displayName = "JumpstartServlet",
    asyncSupported = true)
@VaadinServletConfiguration(ui = OpenHerbariumUI.class, productionMode = false)
public class JumpstartServlet extends DDIVaadinServlet {

  @Override
  public List<String> topLevelPackagesToActivate() {
    return Arrays.asList("org.rapidpm" , "org.openherbarium");
  }
}
