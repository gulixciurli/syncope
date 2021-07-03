/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.provisioning.java.data;

import org.apache.syncope.common.lib.log.LoggerTO;
import org.apache.syncope.common.lib.types.AuditLoggerName;
import org.apache.syncope.common.lib.types.LoggerLevel;
import org.apache.syncope.core.persistence.api.entity.Logger;
import org.springframework.stereotype.Component;
import org.apache.syncope.core.provisioning.api.data.LoggerDataBinder;

@Component
public class LoggerDataBinderImpl implements LoggerDataBinder {

    @Override
    public LoggerTO getLoggerTO(final Logger logger) {
        LoggerTO loggerTO = new LoggerTO();
        loggerTO.setKey(logger.getKey());
        loggerTO.setLevel(logger.getLevel());
        return loggerTO;
    }

    @Override
    public LoggerTO getLoggerTO(final AuditLoggerName auditLoggerName) {
        LoggerTO loggerTO = new LoggerTO();
        loggerTO.setKey(auditLoggerName.toLoggerName());
        loggerTO.setLevel(LoggerLevel.DEBUG);
        return loggerTO;
    }
}
