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

package org.mobicents.protocols.smpp;

import org.mobicents.protocols.smpp.util.PacketDecoder;
import org.mobicents.protocols.smpp.util.PacketEncoder;

/**
 * Object representing a Short Message Entity's address. An address consists of
 * a Type Of Number, a Numbering Plan Indicator and an address.
 * 
 * @see com.adenki.smpp.util.GSMConstants
 */
public class Address implements java.io.Serializable {
    private static final long serialVersionUID = 2L;
    
    /** Type of number. */
    private int ton = Ton.UNKNOWN;

    /** Numbering plan indicator. */
    private int npi = Npi.UNKNOWN;

    /** The address. */
    private String address = "";

    /**
     * Create a new Address with all nul values. TON will be 0, NPI will be 0
     * and the address field will be blank.
     */
    public Address() {
    }

    /**
     * Create a new Address.
     * 
     * @param ton
     *            The Type Of Number.
     * @param npi
     *            The Numbering Plan Indicator.
     * @param address
     *            The address.
     */
    public Address(int ton, int npi, String address) {
        this.ton = ton;
        this.npi = npi;
        this.address = address;
    }

    /**
     * Get the Type Of Number.
     */
    public int getTON() {
        return ton;
    }

    /**
     * Set the Type of Number.
     */
    public void setTON(int ton) {
        this.ton = ton;
    }

    /**
     * Get the Numbering Plan Indicator.
     */
    public int getNPI() {
        return npi;
    }

    /**
     * Set the Numbering Plan Indicator.
     */
    public void setNPI(int npi) {
        this.npi = npi;
    }

    /**
     * Get the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address.
     */
    public void setAddress(String address) {
        this.address = (address != null) ? address : "";
    }

    /**
     * Get a unique hash code for this address.
     */
    public int hashCode() {
        StringBuilder buf = new StringBuilder();
        buf.append(Integer.toString(ton)).append(':');
        buf.append(Integer.toString(npi)).append(':');
        if (address != null) {
            buf.append(address);
        }
        return buf.hashCode();
    }

    /**
     * Test for equality. Two address objects are equal if their TON, NPI and
     * address fields are equal.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Address) {
            Address a = (Address) obj;
            return (a.ton == ton) && (a.npi == npi) && (a.address.equals(address));
        } else {
            return false;
        }
    }

    /**
     * Get the number of bytes this object would encode to.
     */
    public int getLength() {
        return 3 + address.length();
    }

    /**
     */
    public void writeTo(PacketEncoder encoder) throws java.io.IOException {
        encoder.writeUInt1(ton);
        encoder.writeUInt1(npi);
        encoder.writeCString(address);
    }

    /**
     * TODO: doc
     */
    public void readFrom(PacketDecoder decoder) {
        ton = decoder.readUInt1();
        npi = decoder.readUInt1();
        address = decoder.readCString();
    }

    public String toString() {
        return new StringBuffer(25)
        .append(Integer.toString(ton)).append(':')
        .append(Integer.toString(npi)).append(':')
        .append(address).toString();
    }
}
