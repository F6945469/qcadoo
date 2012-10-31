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
package com.qcadoo.tenant.internal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qcadoo.tenant.api.MultiTenantCallback;
import com.qcadoo.tenant.api.MultiTenantService;
import com.qcadoo.tenant.api.SamplesDataset;
import com.qcadoo.tenant.api.Standalone;

@Service
@Standalone
public class DefaultMultiTenantService implements MultiTenantService {

    @Value("${samplesDataset}")
    private String samplesDataset;

    @Override
    public void doInMultiTenantContext(final MultiTenantCallback callback) {
        callback.invoke();
    }

    @Override
    public void doInMultiTenantContext(final int tenantId, final MultiTenantCallback callback) {
        doInMultiTenantContext(callback);
    }

    @Override
    public int getCurrentTenantId() {
        return 0;
    }

    @Override
    public SamplesDataset getTenantSamplesDataset() {
        return SamplesDataset.parseString(samplesDataset);
    }

}
