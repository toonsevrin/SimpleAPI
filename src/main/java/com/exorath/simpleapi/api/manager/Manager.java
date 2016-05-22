/*
 *    Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.simpleapi.api.manager;

import com.exorath.simpleapi.api.lib.Destroyable;

/**
 * Managers can be added to the API's Manager list, that way they can be easily accessed with their class as identifier.
 * If JoinLeave is implemented while the manager is in the API's manager list, these events will be called.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Manager extends Destroyable{
}
