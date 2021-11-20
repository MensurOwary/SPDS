/*******************************************************************************
 * Copyright (c) 2018 Fraunhofer IEM, Paderborn, Germany.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *  
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Johannes Spaeth - initial API and implementation
 *******************************************************************************/
package boomerang;

import boomerang.jimple.Statement;
import boomerang.jimple.Val;
import sync.pds.solver.nodes.Node;
import wpds.impl.Weight;

import java.util.Collection;
import java.util.HashSet;

public class WeightedForwardQuery<W extends Weight> extends ForwardQuery {

    private final W weight;

    public WeightedForwardQuery(Statement stmt, Val variable, W weight) {
        super(stmt, variable);
        this.weight = weight;
     }
     
    public W weight() {
        return weight;
    };
}
