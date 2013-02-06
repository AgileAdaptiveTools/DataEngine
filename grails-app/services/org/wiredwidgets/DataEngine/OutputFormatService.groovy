package org.wiredwidgets.DataEngine

class OutputFormatService
{
    /** returns an array of 2 strings:
     *  [0] is the string version of feed in the format specified by fmt
     *  [1] is the MIME type
     * @param feed the org.wiredwidgets.DataEngine.Feed object
     * @param fmt the format requested
     * @param force_mime_type the mime type requested (not needed if the default mime type is desired)
     */
    static String[] format( Feed feed, String fmt, String force_mime_type )
    {
//        println "  OutputFormatService.format() fmt=${fmt}, force_mime_type=${force_mime_type}"
        String format = fmt ? fmt : "json"
        String data = ""
        String mime = ""

        // set up defaults
        switch ( format.toLowerCase() )
        {
            case "text":
            case "debug":
                data = feed.toString()
                mime = 'text/plain'
                break;
            case "cwl":
                data = feed.toCwl( true )
                mime = 'application/json'
                break;
            case "json":
            default:
                data = feed.toJson( true )
                mime = 'application/json'
                break;
        }

        // override if specified
        if ( force_mime_type )
            mime = force_mime_type

        return [ data, mime ]
    }
}
