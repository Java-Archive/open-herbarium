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

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@PreserveOnRefresh
//@Widgetset("org.openherbarium.module.vaadin.generic.bootstrap.VaadinJumpstartWidgetset")
@Title("JumpstartServlet")
@Push
public class JumpstartUI extends UI {

  private static final Logger LOGGER = LoggerFactory.getLogger(JumpstartUI.class);

  @Inject private JumpstartUIComponentFactory jumpstartUIComponentFactory;

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    LOGGER.debug("init - request = " + vaadinRequest);
    LOGGER.debug("init - request getWrappedSession id = " + vaadinRequest.getWrappedSession().getId());
    setContent(jumpstartUIComponentFactory.createComponentToSetAsContent(vaadinRequest));
    setSizeFull();
  }

}
