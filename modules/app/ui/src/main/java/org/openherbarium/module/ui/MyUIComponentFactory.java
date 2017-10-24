/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.openherbarium.module.ui;

import org.openherbarium.module.api.HasLogger;
import org.openherbarium.module.ui.component.mainview.MainView;
import org.openherbarium.module.vaadin.generic.bootstrap.JumpstartUIComponentFactory;
import org.rapidpm.ddi.DI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;

public class MyUIComponentFactory implements JumpstartUIComponentFactory, HasLogger {

  // @Inject private LoginComponent loginScreen;
  // @Inject private SessionService userService;
  // @Inject private SecurityService securityService;

  @Override
  public Component createComponentToSetAsContent(final VaadinRequest vaadinRequest) {

    // final boolean userPresent = isUserPresent();
    // final boolean remembered = isRemembered();
    // logger().info("remembered = " + remembered);
    // logger().info("userPresent = " + userPresent);
    // if (! (userPresent && remembered)) return DI.activateDI(LoginComponent.class);
    // else return DI.activateDI(MainView.class);

    return DI.activateDI(MainView.class);

  }

  // private boolean isRemembered() {
  // return securityService.isRemembered();
  // }
  //
  // private boolean isUserPresent() {
  // return userService.isUserPresent();
  // }

}
