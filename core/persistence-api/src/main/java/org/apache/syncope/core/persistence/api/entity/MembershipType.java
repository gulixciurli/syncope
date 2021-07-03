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
package org.apache.syncope.core.persistence.api.entity;

public final class MembershipType implements RelationshipType {

    private static final long serialVersionUID = -2767173479992170853L;

    private static final MembershipType INSTANCE = new MembershipType();

    public static MembershipType getInstance() {
        return INSTANCE;
    }

    private MembershipType() {
    }

    @Override
    public String getKey() {
        return "Membership";
    }

    @Override
    public void setKey(final String key) {
        // cannot be changed
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(final String description) {
        // cannot be changed
    }
}
