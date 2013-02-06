package org.wiredwidgets.DataEngine

import org.apache.commons.logging.LogFactory
import grails.converters.*
import org.codehaus.groovy.grails.web.json.JSONException
import org.codehaus.groovy.grails.web.json.JSONObject
import org.apache.commons.io.IOUtils
import groovy.json.JsonSlurper

class IdlController extends GenericProcessController
{
    private static final log = LogFactory.getLog(this)

    def scaffold = Idl

    /** http GET (see online documentation for details */
    def get()
    {
        log.debug "${actionName}() >> params=${params.sort()}"
//        render "GET -> ${params.sort()}"
        def path = params?.path

        if ( path )
        {
            def obj = Idl.get( path )
            if ( obj )
            {
//                def outmap = [
//                        "title" : obj.title,
//                        "poc" : obj.poc,
//                        "creationDate" : obj.creationDate,
//                        "modificationDate" : obj.modificationDate,
//                        "source" : obj.source,
//                        "source_uri" : obj.source_uri,
//                        "description" : obj.description,
//                        "version" : Idl.kIdlVersion,
//                        "dslv" : obj.getDslvAsMap()
//                ]
//                println "output=${obj.toJson().toString( true ) }"
//                def out = outmap as JSON
                renderRestResponse( 200, obj.toJson() )
            }
            else
            {
                renderRestResponse( 404, "Resource not found", null )
            }
        }
        else
        {
            def out = Idl.list() as JSON
            def objs = Idl.list()
            StringBuffer buf = new StringBuffer()
            int numObjs = objs.size()
            int i = 0
            buf << "["
            objs.each  {
                buf << it.toJson().toString( true )
                if ( i < numObjs - 1 )
                    buf << ","
                i++
//                println it.toJson().toString( true )
            }
            buf << "]"
            println buf
            renderRestResponse( 200, buf.toString() )
        }
    }



    /** http POST
     *  does the equivalent of CREATE, that is, assigns an arbitrary uuid we call idl_uri
     *  if params include a uuid (that is, if you're using it as a PUT), this method will
     *  forward to put()
     *  */
    def post()
    {
        log.debug "${actionName}() >> params=${params.sort()}"
//        if ( params._method )
//        {
//            switch ( params._method?.toUpperCase() )
//            {
//                case "PUT":
//                    println "put"
//                    break;
//                case "DELETE":
//                    println "delete"
//                    break;
//                default:
//                    println "def"
//                    break;
//            }
//        }

        if ( params.path )
        {
            forward action:"put"
        }
        else
        {
            try
            {
//                def jsonBody = request.JSON   // had to replace this because it does not handle hierarchical json,
                                                // which we need for dslv
                StringWriter writer = new StringWriter();
                IOUtils.copy( request.getInputStream(), writer );
                String bodyString = writer.toString();
                log.debug "${actionName}() >> inputstream=${bodyString}"
                def slurper = new JsonSlurper()
                def result = slurper.parseText( bodyString )
//                println "${actionName}() >> title=${result.title}"
//                println "${actionName}() >> dslv=${result.dslv}"

//                def dslvMap = result.dslv // results in a Map!
                String dslvString = result.dslv as JSON
                println "dslvString=${dslvString}"
//                println dslvMap.latlon
                result.dslv = ""    // get rid of this so it can be used to create a new object

                def jsonBody = result
//                log.debug "${actionName}() >> body=${jsonBody}"
                def newObj = new Idl( jsonBody )
                newObj.creationDate = new Date()//.format( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" )
                newObj.modificationDate = newObj.creationDate
                newObj.dslv = dslvString//.substring( 1, dslvString.size() - 1 )    // add it in as a String

                assert newObj.getDslvAsMap() instanceof Map<String,String>

                if ( !newObj.validate() )
                {
                    // client error
                    renderRestResponse( 400, "Required data missing, or syntax/format error", null )
                }
                else
                {
                    if ( newObj.save(flush: true) )
                    {
                        String location = request.requestURL
                        location = location.replaceAll( "/grails/", "/" )
                        location = location.replaceAll( "/post.dispatch", "/" )
                        location = "${location}${newObj.id}"    // note this id is only available after the save()

                        response.setHeader( 'Location', location )
                        renderRestResponse( 201, "Created", location )  // created
                    }
                    else
                    {
                        renderRestResponse( 500, "Unable to save to database; data not saved", null )  // server error
                    }
                }
            }
            catch ( Exception e )
            {
				// client error
                renderRestResponse( 400, "Syntax error:  unable to parse input JSON string:  ${e}", null )
            }

        }
    }

    /** http PUT */
    def put()
    {
//        log.debug "${actionName}() >> params=${params.sort()}"
//        render "PUT -> ${params.sort()}"
        log.debug "${actionName}() >> params=${params.sort()}"
        if ( !params.path )
        {
            // client error
            renderRestResponse( 400, "Missing resource ID (idl_uri) in request, if this is a new resource, use HTTP POST instead of HTTP PUT", null )
        }
        else
        {
            try
            {
                def obj = Idl.get( params.path )
                if ( !obj )
                {
                    // not found
                    renderRestResponse( 404, "Resource with that ID could not be found", null )

                }
                else
                {
                    def jsonBody = request.JSON
                    log.debug "${actionName}() >> body=${jsonBody}"
                    obj.properties = jsonBody
                    obj.modificationDate = new Date()//.format( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" )

                    if ( !obj.validate() )
                    {
                        // client error
                        renderRestResponse( 400, "Required data missing, or syntax/format error", null )
                    }
                    else
                    {
                        if ( obj.save(flush: true) )
                        {
                            String location = request.requestURL
                            location = location.replaceAll( "/grails/", "/" )
                            location = location.replaceAll( "/put.dispatch", "/" )
                            location = "${location}${obj.id}"
                            println obj as JSON

                            response.setHeader( 'Location', location )
                            renderRestResponse( 200, "OK", location )
                        }
                        else
                        {
                            renderRestResponse( 500, "Unable to save to database; data not saved", null )  // server error
    //                      render "POST Error -> ${params.sort()}"
                        }
                    }
                }
            }
            catch ( Exception e )
            {
                // client error
                renderRestResponse( 400, "Syntax error:  unable to parse input JSON string", null )
            }
        }
    }

    /** http DELETE */
    def delete()
    {
        log.debug "${actionName}() >> params=${params.sort()}"
//        render "DELETE -> ${params.sort()}"
        if ( !params.path )
        {
            // client error
            renderRestResponse( 400, "Missing resource ID (idl_uri) in request", null )
        }
        else
        {
            def obj = Idl.get( params.path )
            if ( !obj )
            {
                // not found
                renderRestResponse( 404, "Resource with that ID could not be found", null )

            }
            else
            {
                try
                {
                    obj.delete()//(flush: true)
                    renderRestResponse( 200, "OK", null )
                }
                catch ( Exception e )
                {
                    // client error
                    renderRestResponse( 400, "Syntax error:  ${e}", null )
                }
            }
        }
    }
}