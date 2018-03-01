/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.tasks.properties.bean;

import org.gradle.api.Named;
import org.gradle.api.internal.tasks.PropertySpecFactory;
import org.gradle.api.internal.tasks.properties.NodeContext;
import org.gradle.api.internal.tasks.properties.PropertyMetadataStore;
import org.gradle.api.internal.tasks.properties.PropertyVisitor;

import javax.annotation.Nullable;

class IterableBeanNode extends BaseBeanNode<Iterable<?>> {
    public IterableBeanNode(@Nullable String propertyName, Iterable<?> iterable) {
        super(propertyName, iterable);
    }

    private static String determinePropertyName(@Nullable Object input, int count) {
        String prefix = input instanceof Named ? ((Named) input).getName() : "";
        return prefix + "$" + count;
    }

    @Override
    public void visitNode(PropertyVisitor visitor, PropertySpecFactory specFactory, NodeContext<BeanNode> context, PropertyMetadataStore propertyMetadataStore) {
        int count = 0;
        for (Object input : getBean()) {
            String propertyName = determinePropertyName(input, count);
            count++;
            context.addToQueue(createChildNode(propertyName, input, propertyMetadataStore));
        }
    }
}
