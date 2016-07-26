/*
 * Copyright (c) 2002-2016 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.enterprise;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.neo4j.kernel.api.bolt.HaltableUserSession;
import org.neo4j.kernel.api.bolt.SessionTracker;

public class StandardSessionTracker implements SessionTracker
{
    private Map<HaltableUserSession,Object> sessions = new ConcurrentHashMap<>();

    @Override
    public void sessionActivated( HaltableUserSession session )
    {
        sessions.put( session, session );
    }

    @Override
    public void sessionHalted( HaltableUserSession session )
    {
        sessions.remove( session );
    }

    @Override
    public Set<HaltableUserSession> getActiveSessions()
    {
        return sessions.keySet().stream().collect( Collectors.toSet() );
    }
}