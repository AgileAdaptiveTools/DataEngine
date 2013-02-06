package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory

class GenericSlurperController
{
    private static final log = LogFactory.getLog(this)


    def index()
    {
        log.debug "${actionName}() >> ${request?.method} params=${params.sort()}"

        if ( params.size() == 1 )
        {
            // show the help file
        }
        else
        {
            switch ( request?.method )
            {
                case "POST":
                    post()
                    break;
                case "GET":
                default:
                    if ( params.url )
                        get()
                    break;
            }
        }
    }

    protected String formatOutput(Feed feed)
    {
        def out = OutputFormatService.format( feed, params?.format, params?.force_mime_type )
        render text: out[0], contentType: out[1]
    }

}
