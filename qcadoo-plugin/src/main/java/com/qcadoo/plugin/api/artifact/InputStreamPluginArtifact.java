/**
 * ***************************************************************************
 * Copyright (c) 2010 Qcadoo Limited
 * Project: Qcadoo Framework
 * Version: 1.2.0-SNAPSHOT
 *
 * This file is part of Qcadoo.
 *
 * Qcadoo is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.plugin.api.artifact;

import java.io.InputStream;

/**
 * Plugin artifact build on raw input stream.
 * 
 * @since 0.4.0
 */
public class InputStreamPluginArtifact implements PluginArtifact {

    private final String name;

    private final InputStream inputStream;

    /**
     * Create artifact build on raw input stream.
     * 
     * @param name
     *            name of the plugin
     * @param inputStream
     *            input stream
     */
    public InputStreamPluginArtifact(final String name, final InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

}
