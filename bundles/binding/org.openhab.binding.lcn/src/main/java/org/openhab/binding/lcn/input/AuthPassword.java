/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.lcn.input;

import java.util.Collection;
import java.util.LinkedList;

import org.openhab.binding.lcn.common.PckParser;
import org.openhab.binding.lcn.connection.Connection;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.items.Item;
import org.openhab.core.types.Command;

/**
 * Password request for LCN-PCHK authentication.
 *
 * @author Tobias J�ttner
 */
public class AuthPassword extends Input {

    /**
     * Tries to parse the given input received from LCN-PCHK.
     * 
     * @param input the input
     * @return list of {@link AuthPassword} (might be empty, but not null}
     */
    static Collection<Input> tryParseInput(String input) {
        LinkedList<Input> ret = new LinkedList<Input>();
        if (input.equals(PckParser.AUTH_PASSWORD)) {
            ret.add(new AuthPassword());
        }
        return ret;
    }

    /**
     * Sends the password.
     * {@inheritDoc}
     */
    @Override
    public void process(Connection conn) {
        conn.queue(conn.getSets().getPassword());
    }

    /** {@inheritDoc} */
    @Override
    public boolean tryVisualization(Input.VisualizationVisitor handler, Connection conn, Command cmd, Item item,
            EventPublisher eventPublisher) {
        // Not used for visualization
        return false;
    }

}
