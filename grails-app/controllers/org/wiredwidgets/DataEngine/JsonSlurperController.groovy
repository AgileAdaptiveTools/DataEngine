package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory

class JsonSlurperController extends GenericSlurperController
{
    private static final log = LogFactory.getLog(this)

    // index() in super.index()

    def get()
    {
        log.debug "get() >> ${request?.method} params=${params.sort()}"
        URL url = new URL( params.url )
        Feed feed = JsonFormatHelper.ingest( new InputStreamReader( url.openStream() ), params.idl )
        super.formatOutput( feed )
    }


    def post()
    {
        log.debug "post() >> ${request?.method} params=${params.sort()}"
        org.springframework.web.multipart.commons.CommonsMultipartFile file = params.inputFile
        Feed feed = JsonFormatHelper.ingest( new StringReader( file.getInputStream().getText() ), params.idl )
        super.formatOutput( feed )
    }


    def play()
    {
        log.debug "${actionName}() >> ${request?.method} params=${params.sort()}"

        org.springframework.web.multipart.commons.CommonsMultipartFile file = params.inputFile

        Feed feed = JsonFormatHelper.ingest( new StringReader( file.getInputStream().getText() ), params.idl )

        // if ( file == null )

        println feed

        [origName:file.getOriginalFilename(),
                mimetype:file.contentType,
                filesize:file.getSize(),
                stored:file.getStorageDescription(),
                feed:feed
        ]
    }
}
