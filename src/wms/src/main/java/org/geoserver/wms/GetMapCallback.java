/* Copyright (c) 2013 OpenPlans - www.openplans.org. All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package org.geoserver.wms;

import org.geotools.map.Layer;

/**
 * Extension point allowing implementors to follow and manipulate the lifecycle of a GetMap request.
 * Possibilities offered:
 * <ul>
 * <li>Gather information about the GetMap request, the layer structure and the results</li>
 * <li>Modify the request</li>
 * <li>Alter or remove the layers</li>
 * <li>Alter the map content before rendering</li>
 * <li>Alter the rendering results</li>
 * </ul>
 * 
 * @author Andrea Aime - GeoSolutions
 */
public interface GetMapCallback {

    /**
     * Marks the beginning of a GetMap request internal processing
     */
    GetMapRequest initRequest(GetMapRequest request);

    /**
     * Called when the WMSMapContent is created (at this point the WMSMapContent is empty) On
     * multidimensional requests against multi-frame output formats (e.g. animated GIF) this method
     * can be called multiple times, once per generated frame
     */
    void initMapContent(WMSMapContent mapContent);

    /**
     * Called before a layer gets added to the map content, allows the layer to be modified. If the
     * returned value is null the layer will not be added to the map content
     */
    Layer beforeLayer(WMSMapContent mapContent, Layer layer);

    /**
     * Inspects and eventually manipulates the WMSMapContent, returning a WMSMapContent that will be
     * used for map rendering. In case of multi-frame output formats this method will be called once
     * per frame.
     */
    WMSMapContent beforeRender(WMSMapContent mapContent);

    /**
     * Called once when the rendering is completed, allows the output WebMap to be inspected,
     * modified or replaced altogether
     */
    WebMap finished(WebMap map);

    /**
     * Called if the GetMap fails for any reason.
     * @param t
     */
    void failed(Throwable t);
}
