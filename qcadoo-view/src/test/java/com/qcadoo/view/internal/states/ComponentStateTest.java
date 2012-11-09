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
package com.qcadoo.view.internal.states;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Locale;

import org.json.JSONObject;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.qcadoo.localization.api.TranslationService;
import com.qcadoo.model.api.DataDefinition;
import com.qcadoo.model.internal.ExpressionServiceImpl;
import com.qcadoo.view.api.ComponentState.MessageType;
import com.qcadoo.view.internal.api.InternalComponentState;
import com.qcadoo.view.internal.components.SimpleComponentState;
import com.qcadoo.view.internal.components.form.FormComponentPattern;
import com.qcadoo.view.internal.components.form.FormComponentState;

public class ComponentStateTest {

    @Test
    public void shouldHaveFieldValueAfterInitialize() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        JSONObject json = new JSONObject();
        JSONObject jsonContent = new JSONObject();
        jsonContent.put(AbstractComponentState.JSON_VALUE, "text");
        json.put(AbstractComponentState.JSON_CONTENT, jsonContent);

        // when
        componentState.initialize(json, Locale.ENGLISH);

        // then
        assertEquals("text", componentState.getFieldValue());
    }

    @Test
    public void shouldRenderJsonWithFieldValue() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();
        componentState.setFieldValue("text");
        componentState.initialize(new JSONObject(), Locale.ENGLISH);

        // when
        JSONObject json = componentState.render();

        // then
        assertEquals("text", json.getJSONObject(AbstractComponentState.JSON_CONTENT).getString(AbstractComponentState.JSON_VALUE));
    }

    @Test
    public void shouldRenderJsonWithNullFieldValue() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();
        componentState.setFieldValue(null);
        componentState.initialize(new JSONObject(), Locale.ENGLISH);

        // when
        JSONObject json = componentState.render();

        // then
        assertFalse(json.getJSONObject(AbstractComponentState.JSON_CONTENT).has(AbstractComponentState.JSON_VALUE));
    }

    @Test
    public void shouldNotRenderComponentIfNotRequested() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        // when
        JSONObject json = componentState.render();

        // then
        assertFalse(json.has(AbstractComponentState.JSON_CONTENT));
    }

    @Test
    public void shouldHaveRequestUpdateStateFlag() throws Exception {
        // given
        new ExpressionServiceImpl().init();

        TranslationService translationService = mock(TranslationService.class);
        DataDefinition dataDefinition = mock(DataDefinition.class);
        FormComponentPattern pattern = mock(FormComponentPattern.class);
        given(pattern.getExpressionNew()).willReturn(null);
        given(pattern.getExpressionEdit()).willReturn("2");
        AbstractComponentState componentState = new FormComponentState(pattern);
        componentState.setTranslationService(translationService);
        componentState.setDataDefinition(dataDefinition);
        componentState.setFieldValue(13L);
        componentState.initialize(new JSONObject(ImmutableMap.of("components", new JSONObject())), Locale.ENGLISH);

        // when
        JSONObject json = componentState.render();

        // then
        assertTrue(json.getBoolean(AbstractComponentState.JSON_UPDATE_STATE));
    }

    @Test
    public void shouldNotHaveRequestUpdateStateIfNotValid() throws Exception {
        // given
        TranslationService translationService = mock(TranslationService.class);
        FormComponentPattern pattern = mock(FormComponentPattern.class);
        given(pattern.getExpressionNew()).willReturn(null);
        given(pattern.getExpressionEdit()).willReturn(null);
        AbstractComponentState componentState = new FormComponentState(pattern);
        componentState.setTranslationService(translationService);
        componentState.initialize(new JSONObject(ImmutableMap.of("components", new JSONObject())), Locale.ENGLISH);
        componentState.addMessage("test", MessageType.FAILURE);

        // when
        JSONObject json = componentState.render();

        // then
        assertFalse(json.getBoolean(AbstractComponentState.JSON_UPDATE_STATE));
    }

    @Test
    public void shouldHaveVisibleFlag() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        JSONObject json = new JSONObject();
        JSONObject jsonContent = new JSONObject();
        jsonContent.put(AbstractComponentState.JSON_VALUE, "text");
        json.put(AbstractComponentState.JSON_CONTENT, jsonContent);
        json.put(AbstractComponentState.JSON_VISIBLE, true);

        // when
        componentState.initialize(json, Locale.ENGLISH);

        // then
        assertTrue(componentState.isVisible());
        assertTrue(componentState.render().getBoolean(AbstractComponentState.JSON_VISIBLE));
    }

    @Test
    public void shouldModifyVisibleFlag() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        // when
        componentState.setVisible(false);

        // then
        assertFalse(componentState.isVisible());
        assertFalse(componentState.render().getBoolean(AbstractComponentState.JSON_VISIBLE));
    }

    @Test
    public void shouldHaveEnableFlag() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        JSONObject json = new JSONObject();
        JSONObject jsonContent = new JSONObject();
        jsonContent.put(AbstractComponentState.JSON_VALUE, "text");
        json.put(AbstractComponentState.JSON_CONTENT, jsonContent);
        json.put(AbstractComponentState.JSON_ENABLED, true);

        // when
        componentState.initialize(json, Locale.ENGLISH);

        // then
        assertTrue(componentState.isEnabled());
        assertTrue(componentState.render().getBoolean(AbstractComponentState.JSON_ENABLED));
    }

    @Test
    public void shouldModifyEnableFlag() throws Exception {
        // given
        InternalComponentState componentState = new SimpleComponentState();

        // when
        componentState.setEnabled(false);

        // then
        assertFalse(componentState.isEnabled());
        assertFalse(componentState.render().getBoolean(AbstractComponentState.JSON_ENABLED));
    }

}
