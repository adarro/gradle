/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.nativeplatform.fixtures.app

/**
 * A Swift app with a changed source file
 */
class IncrementalSwiftApp {
    def app = new SwiftApp()
    def alternateApp = new SwiftAlternateApp()

    IncrementalSwiftApp() {
        // Verify some assumptions that the tests make
        assert app.sourceFiles.size() > 1
        assert alternateApp.sourceFiles.size() == app.sourceFiles.size()
        assert alternateApp.sourceFiles.first().content != app.sourceFiles.first().content
        for (int i = 1; i < app.sourceFiles.size(); i++) {
            assert alternateApp.sourceFiles[i].content == app.sourceFiles[i].content
        }
    }
}