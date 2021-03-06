/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.smpp.util;

/**
 * SMPP packet sequence numbering scheme interface. Implementations of this
 * interface provide a {@link org.mobicents.protocols.smpp.Session} with a unique number for
 * each call to <code>nextNumber</code>. This number is used as the packet's
 * sequence number in the SMPP header. The default implementation (
 * {@link DefaultSequenceScheme}) counts monotonically from 1 upwards for each
 * number requested. While this is the SMPP specification's recommended
 * behaviour, there is no requirement for 2 sequentially-requested numbers to be
 * numerically sequential.
 * 
 * @version $Id: SequenceNumberScheme.java 452 2009-01-15 16:56:36Z orank $
 */
public interface SequenceNumberScheme {

    /**
     * Constant that can be returned from the <code>peek</code> methods to
     * indicate that the peek operation is not supported.
     */
    long PEEK_UNSUPPORTED = -1;

    /**
     * Get the next number in this sequence's scheme. An implementation of this
     * interface <b>must </b> guard against multi-threaded access to this method
     * to prevent more than one thread getting the same sequence number.
     */
    long nextNumber();

    /**
     * Get the next number in this sequence's scheme without causing it to move
     * to the next-in-sequence. This method returns the number that will be
     * returned by the next call to <code>nextNumber</code> without actually
     * increasing the sequence. Multiple calls to <code>peek</code> will
     * return the same number until a call to <code>nextNumber</code> is made.
     */
    long peek();

    /**
     * Get the nth next number in this sequence's scheme without causing it to
     * move to the next-in-sequence. This method returns the <code>nth</code>
     * next number in the sequence. This is an optional operation. If a sequence
     * numbering scheme does not support this operation, it should always return
     * {@link #PEEK_UNSUPPORTED} to the caller.
     */
    long peek(long nth);

    /**
     * Reset the sequence scheme to the beginning of the sequence.
     */
    void reset();
}

