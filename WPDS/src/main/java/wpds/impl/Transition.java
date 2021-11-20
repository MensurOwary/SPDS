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
package wpds.impl;

import pathexpression.Edge;
import wpds.interfaces.Location;
import wpds.interfaces.State;
import wpds.wildcard.Wildcard;

import java.util.Collection;

public class Transition<N extends Location, D extends State> implements Edge<D, N> {
    private final D start;
    private final N label;
    private final D target;
    private int hashCode;

    public Transition(D start, N label, D target) {
        assert start != null;
        assert target != null;
        assert label != null;
        this.start = start;
        this.label = label;
        this.target = target;
        if (label instanceof Wildcard)
            throw new RuntimeException("No wildcards allowed!");
    }

    public void addRelatedVariables(Transition<N, D> transition) {
        final D start = transition.getStart();
        if (start != null) {
            final Collection<?> relatedVariables = ((State<?>) start).getRelatedVariables();
            this.start.setRelatedVariables(relatedVariables);
        }
    }

    public Configuration<N, D> getStartConfig() {
        return new Configuration<N, D>(label, start);
    }

    public D getTarget() {
        return target;
    }

    public D getStart() {
        return start;
    }

    public N getString() {
        return label;
    }

    @Override
    public int hashCode() {
        if (hashCode != 0)
            return hashCode;
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        hashCode = result;
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Transition other = (Transition) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return start + "~" + label + "~>" + target;
    }

    @Override
    public N getLabel() {
        return label;
    }
}
