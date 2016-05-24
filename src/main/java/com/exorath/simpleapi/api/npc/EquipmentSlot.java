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

package com.exorath.simpleapi.api.npc;

/**
 * Created by Toon Sevrin on 5/22/2016.
 */
public enum EquipmentSlot {
    MAIN_HAND(de.inventivegames.npc.equipment.EquipmentSlot.MAIN_HAND),
    OFF_HAND(de.inventivegames.npc.equipment.EquipmentSlot.OFF_HAND),
    HEAD(de.inventivegames.npc.equipment.EquipmentSlot.HEAD),
    CHEST(de.inventivegames.npc.equipment.EquipmentSlot.CHEST),
    LEGS(de.inventivegames.npc.equipment.EquipmentSlot.LEGS),
    FEET(de.inventivegames.npc.equipment.EquipmentSlot.FEET);

    private de.inventivegames.npc.equipment.EquipmentSlot slot;
    EquipmentSlot(de.inventivegames.npc.equipment.EquipmentSlot slot){
        this.slot = slot;
    }

    protected de.inventivegames.npc.equipment.EquipmentSlot getSlot() {
        return slot;
    }
}
