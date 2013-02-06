package org.wiredwidgets.DataEngine

import grails.converters.JSON

class GenericProcessController
{

    def index()
    {
        log.debug "${actionName}() >> params=${params.sort()}"
        // overrides index going to the scaffolded list, so that the help page can be displayed
    }


    /** response with only a status body
     *  renders all parameters as a json string
     * @param status response status
     * @param remark additional programmer readable comment narrowing down the problem
     * @param idl_uri the path to the resource
     */
    protected void renderRestResponse( int status, String remark, String idl_uri )
    {
        response.setStatus( status )
        def ret = [:]
        ret.status = status
        ret.remark = remark
        if ( idl_uri )
            ret.idl_uri = idl_uri
        render ret as JSON
    }

    /** sucessful response (one of the 200 return codes)
     *  renders json as a prettyprinted json string
     * @param status response status
     * @param json the json object, if null, this method will render "{}" as json
     */
    protected void renderRestResponse( int status, grails.converters.JSON json )
    {
        if ( json )
        {
			response.setStatus( status )
            json.setPrettyPrint( true )
            render json
        }
        else
        {
            // not a 404, since the resource is thought to have been found, but is a null
            renderRestResponse( 500, "Resource is undefined (null)", null )
        }
    }

    /** sucessful response (one of the 200 return codes)
     *  renders json as a prettyprinted json string
     * @param status response status
     * @param json the json object, if null, this method will render "{}" as json
     */
    protected void renderRestResponse( int status, String str )
    {
        if ( str )
        {
			response.setStatus( status )
            render text:str, contentType:"application/json"
        }
        else
        {
            // not a 404, since the resource is thought to have been found, but is a null
            renderRestResponse( 500, "Resource is undefined (null)", null )
        }
    }

    /** sucessful response (one of the 200 return codes)
     *  renders json as a prettyprinted json string
     * @param status response status
     * @param json the json object, if null, this method will render "{}" as json
     */
    protected void renderRestResponse( int status, Feed feed )
    {
        if ( feed )
        {
			response.setStatus( status )
            def out = OutputFormatService.format( feed, params?.format, params?.force_mime_type )
            render text: out[0], contentType: out[1]
        }
        else
        {
            // not a 404, since the resource is thought to have been found, but is a null
            renderRestResponse( 500, "Resource is undefined (null)", null )
        }
    }
}
