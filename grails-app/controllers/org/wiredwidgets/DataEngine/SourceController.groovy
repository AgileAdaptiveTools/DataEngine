package org.wiredwidgets.DataEngine

import grails.plugins.rest.client.RestBuilder
import org.apache.commons.io.FilenameUtils

/** controller for data sources */
class SourceController
{
	def index( )
	{
		println "===${controllerName}.index() -> ${params}"
		if ( !params.url )
		{
			// note that it would be preferable to have
			//   /DataEngine/source/http://localhost:8080/DataEngine/product/sample_files_sample_cwl.json
			// instead of
			//	 /DataEngine/source?url=http://localhost:8080/DataEngine/product/sample_files_sample_cwl.json
			// but Grails strips known extensions and makes it very difficult to put back, especially when
			//	the request comes from a browser.  (the solutions on http://stackoverflow.com/questions/4932289/urlmapping-and-file-name-extension
			//	says to use request.format, but because the browser sends the request as "*.*", the resulting extension
			//	becomes .all.
			// so after a lot of testing, it turned out to just be easier to use a query parameter
			[]
		}
		else
		{
			URL url = new URL( params.url )
			println "url=${url}"
			RestBuilder rest = null
			if ( url.host.startsWith( "localhost" ) )
			{
				rest = EndpointAccessService.getRestBuilder()
			}
			else
			{
				println "network.proxy=${Setting.getNetworkProxy()}"
				rest = EndpointAccessService.getRestBuilder( Setting.getNetworkProxy() )
//				rest = new RestBuilder(connectTimeout:1000, readTimeout:20000, proxy:['proxy.company.com':8888])
			}
			def resp = rest.get( url.toExternalForm() )
			def mtype = resp.headers["Content-Type"]	// this returns a list of 1 object
			def mime = "text/plain" 	// default
			if ( mtype )
				mime = mtype[0]

			render ( contentType: mime , text:  resp.text )
//			render ( contentType: "text/plain" , text: resp.text + "\nproperties=${resp.properties}")	// debug
//			render resp.text	// debug
		}
	}
}