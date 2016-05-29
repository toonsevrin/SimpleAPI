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

package com.exorath.simpleapi.api.hologram;

/**
 * Created by Toon Sevrin on 5/27/2016.
 */
public enum TouchAction {
    RIGHT_CLICK(de.inventivegames.hologram.touch.TouchAction.RIGHT_CLICK),
    LEFT_CLICK(de.inventivegames.hologram.touch.TouchAction.LEFT_CLICK),
    UNKNOWN(de.inventivegames.hologram.touch.TouchAction.UNKNOWN);

    private de.inventivegames.hologram.touch.TouchAction action;

    TouchAction(de.inventivegames.hologram.touch.TouchAction action){
        this.action = action;
    }

    protected de.inventivegames.hologram.touch.TouchAction getAction() {
        return action;
    }

}
