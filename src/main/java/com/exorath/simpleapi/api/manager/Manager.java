package com.exorath.simpleapi.api.manager;

import com.exorath.simpleapi.api.lib.Destroyable;

/**
 * Managers can be added to the API's Manager list, that way they can be easily accessed with their class as identifier.
 * If JoinLeave is implemented while the manager is in the API's manager list, these events will be called.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Manager extends Destroyable{
}
