package com.exorath.simpleapi.api.lib;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Destroyable {
    /**
     * Can be called when the implementation is destroyed (Usually on reload, restart or safe shutdown).
     */
    void destroy();
}
